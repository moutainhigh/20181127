package com.yeepay.g3.ymf.hessian.controller;

import com.yeepay.g3.core.ymf.biz.TradeYMFbizService;
import com.yeepay.g3.core.ymf.biz.cod.CodBiz;
import com.yeepay.g3.core.ymf.biz.liker.LikerBizService;
import com.yeepay.g3.core.ymf.biz.remit.InvokeRemoteRemitService;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.business.Business;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.service.BusinessService;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.ymf.dto.opr.OprPayCallbackRequestDTO;
import com.yeepay.g3.facade.ymf.dto.opr.OprSettleCallbackRequestDTO;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.common.CardType;
import com.yeepay.g3.facade.ymf.enumtype.trade.*;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.log.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;

import static com.yeepay.g3.core.ymf.utils.dateutils.DateUtil.getStrToDate;

/**
 * 订单处理器公网回调处理器
 * Created by dongxulu on 17/1/19.
 */
@Controller
@RequestMapping("/opr")
public class OprCallbackController {
    private static Logger logger = LoggerFactory.getLogger(OprCallbackController.class);
    private static final String FALSE="FALSE";
    private static final String SUCCESS="SUCCESS";
    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private TradeYMFbizService tradeYMFbizService;
    @Autowired
    private LikerBizService likerBizService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private CodBiz codBiz;
    @Autowired
    private InvokeRemoteRemitService invokeRemoteRemitService;

    /**
     * 支付回调
     * @param requestDTO 回调请求
     * @return
     */
    @RequestMapping("/payCallback")
    @ResponseBody
    public String oprPayCallback(OprPayCallbackRequestDTO requestDTO){
        if(null == requestDTO){
            logger.error("oprPayCallback requestParam is null!");
            return FALSE;
        }
        logger.info("####oprPayCallback requestParm:"+JSONUtils.toJsonString(requestDTO));
        String status = requestDTO.getStatus();
        if("SUCCESS".equals(status)){
            String customerNo = requestDTO.getMerchantNo();
            Customer customer = customerService.findByCustomerNumber(customerNo);
            String requestId = requestDTO.getOrderId();
            String yeepayOrderId = requestDTO.getUniqueOrderNo();
            Payment payment;
            try {
                payment = paymentService.findByYeepayOrderId(customerNo,yeepayOrderId);
            } catch (Exception e) {
                logger.error(" findByYeepayOrderId exception yeepayOrderId:"+yeepayOrderId,e);
                return FALSE;
            }
            if(null == payment){
                logger.error(" no payment yeepayOrderId:"+yeepayOrderId);
                return FALSE;
            }
            Order order = orderService.findById(payment.getOrderId());
            order.setCompleteTime(new Date());
            order.setOrderStatus(OrderStatus.SUCCESS);
            BigDecimal realAmount ;
            if(null!=requestDTO.getPayAmount()){
                realAmount = BigDecimal.valueOf(Double.valueOf(requestDTO.getPayAmount()));
                order.setRealAmt(realAmount);
                payment.setRealAmt(realAmount);
            }
            payment.setBankOrderId(requestDTO.getBankOrderId());
            if(!StringUtil.isEmpty(requestDTO.getPaySuccessDate())){
                Date payTime = getStrToDate(requestDTO.getPaySuccessDate(), Constants.OPR_DATE_TEMPLATE);
                payment.setPayTime(payTime);
                payment.setChannelPayTime(payTime);
            }else{
                payment.setPayTime(new Date());
                payment.setChannelPayTime(new Date());
            }
            payment.setPayStatus(PaymentStatus.SUCCESS);
            if(BalanceType.S0.equals(customer.getBalanceProduct())){
                order.setBalanceType(BalanceType.S0);
            }
            //微信WECHAT,支付宝ALIPAY,分期支付CFL_BT
            String cardType = requestDTO.getCardType();
            payment.setPaySource(PaySource.valueOf(requestDTO.getPlatformType()));
            payment.setBankType(requestDTO.getBankId());
            if(StringUtils.isNotEmpty(cardType)){
                try {
                    payment.setCardType(CardType.getCardType(cardType));
                } catch (Exception e) {
//                    此处抓一下异常 打印日志 不做处理 可能会因为类型不一样导致异常
                   logger.error("setCardType exception:cardType--->:"+cardType);
                }
            }
            try {
                tradeYMFbizService.updateOrderAndPayment(order,payment);
//                查询商户业务方  如果是laike 通知给app服务端,非来客 直接通知行业应用
                Business business = businessService.getBusinessById(customer.getBusinessId());
                if(Status.ACTIVE.equals(business.getStatus())&&Constants.LAIKE.equals(business.getBizCode())){
                    //通知来客支付结果
                    likerBizService.doNotify(payment,customer, order);
                }else{
                    // 行业版需要通知,改为全部交易都通知COD
                    codBiz.orderPayNotify(payment,customer, order);
                    logger.info("------ Cod通知成功,externalId:" + order.getExternalId() + ",customerOrderId:"
                            + order.getCustomerOrderId());
                }
                logger.info("oprPayCallback success!");
                return SUCCESS;
            } catch (YmfException e) {
                logger.error("completeOrderAndPayment exception yeepayOrderId :"+yeepayOrderId,e);
            }  catch (Throwable throwable) {
                logger.error(" Throwable yeepayOrderId :"+yeepayOrderId,throwable);
            }
        }
        return FALSE;
    }

    /**
     * 结算回调
     * @param requestDTO 回调请求
     * @return
     */
    @RequestMapping("/settleCallback")
    @ResponseBody
    public String oprSettleCallback(OprSettleCallbackRequestDTO requestDTO){
        if(null == requestDTO){
            logger.error("oprSettleCallback requestParam is null!");
            return FALSE;
        }
        logger.info("####oprSettleCallback requestParm:"+ JSONUtils.toJsonString(requestDTO));
        String status = requestDTO.getStatus();
        if("SUCCESS".equals(status)) {
            String customerNo = requestDTO.getMerchantNo();
            Customer customer = customerService.findByCustomerNumber(customerNo);
            String yeepayOrderId = requestDTO.getUniqueOrderNo();
            Payment payment;
            String merchantFee = requestDTO.getMerchantFee();
            try {
                payment = paymentService.findByYeepayOrderId(customerNo,yeepayOrderId);
            } catch (Exception e) {
                logger.error(" oprSettleCallback findByYeepayOrderId exception yeepayOrderId: "+yeepayOrderId,e);
                return FALSE;
            }
            if(null == payment){
                logger.error(" no payment yeepayOrderId:"+yeepayOrderId);
                return FALSE;
            }
            Order order = orderService.findById(payment.getOrderId());
            BigDecimal fee = BigDecimal.valueOf(Double.valueOf(merchantFee));
            order.setFee(fee);
            payment.setFee(fee);
            payment.setSettleStatus(SettleStatus.SETTLED);
            if(StringUtil.isNotEmpty(requestDTO.getSuccessDate())){
                Date setlleTime = DateUtil.getStrToDate(requestDTO.getSuccessDate(), Constants.OPR_DATE_TEMPLATE);
                payment.setSettleTime(setlleTime);
            }else{
                payment.setSettleTime(new Date());
            }
            try {
                tradeYMFbizService.updateOrderAndPayment(order,payment);
                invokeRemoteRemitService.doRemittance(order,payment);
                logger.info("oprSettleCallback success! yeepayOrderId:"+yeepayOrderId);
                return SUCCESS;
            } catch (YmfException e) {
                logger.error("completeOrderAndPayment exception yeepayOrderId :"+yeepayOrderId,e);
            } catch (YmfTrxException e) {
                logger.error(" YmfTrxException yeepayOrderId :"+yeepayOrderId+" customerOrderID:"+order.getCustomerOrderId(),e);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        return FALSE;
    }

}
