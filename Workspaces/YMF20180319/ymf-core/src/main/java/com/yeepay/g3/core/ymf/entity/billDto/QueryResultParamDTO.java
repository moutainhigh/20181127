package com.yeepay.g3.core.ymf.entity.billDto;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 返回报文实体
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "ORDERINFO")
// 控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = {
        "customerOrderId", "customerNumber", "customerName", "yeepayOrderId", "qrId",
        "trxAmt", "payTime", "payStatus", "externalId", "paySource",
        "balanceType", "bankType", "shopNumber", "shopName", "provinceName"
})
public class QueryResultParamDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -6517666404124836611L;

    /**
     * 商户订单号（缴费单号）
     */
//    private String ORDERNO;
    @NotBlank(message = "商户订单号不能为空")
    private String customerOrderId;
    /**
     * 商户编号
     */
    @NotBlank(message = "商户编号不能为空")
    private String customerNumber;
    /**
     * 商户名称
     */
    private String customerName;
    /**
     * 收银台单号/易宝单号
     */
    @NotBlank(message = "收银台单号不能为空")
    private String yeepayOrderId;
    /**
     * 二维码ID
     */
    @NotBlank(message = "二维码ID不能为空")
    private String qrId;
    /**
     * 交易金额
     */
    @NotBlank(message = "交易金额不能为空")
    private BigDecimal trxAmt;
    /**
     * 支付时间
     */
    @NotBlank(message = "支付时间不能为空")
    private String payTime;//时间状态的类型 都换成 DATE
    /**
     * 支付状态
     */
    @NotBlank(message = "支付状态不能为空")
    private String payStatus;
    /**
     * 业务订单号
     */
    private String externalId;
    /**
     * 支付方式
     */
    @NotBlank(message = "支付方式不能为空")
    private String paySource;
    /**
     * 结算类型
     */
    @NotBlank(message = "结算类型不能为空")
    private String balanceType;
    /**
     * 银行卡类型
     */
    private String bankType;
    /**
     * 网点编号
     */
    private String shopNumber;
    /**
     * 网点名称
     */
    private String shopName;
    /**
     * 所属省份
     */
    private String provinceName;//可能换成province

    public QueryResultParamDTO(){}

    public QueryResultParamDTO(String customerOrderId, String customerNumber, String customerName, String yeepayOrderId, String qrId, BigDecimal trxAmt, String payTime, String payStatus, String balanceType, String externalId, String paySource, String bankType, String shopNumber, String shopName, String provinceName) {
//        this.ORDERNO = ORDERNO;
        this.customerOrderId = customerOrderId;
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.yeepayOrderId = yeepayOrderId;
        this.qrId = qrId;
        this.trxAmt = trxAmt;
        this.payTime = payTime;
        this.payStatus = payStatus;
        this.balanceType = balanceType;
        this.externalId = externalId;
        this.paySource = paySource;
        this.bankType = bankType;
        this.shopNumber = shopNumber;
        this.shopName = shopName;
        this.provinceName = provinceName;
    }

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getYeepayOrderId() {
        return yeepayOrderId;
    }

    public void setYeepayOrderId(String yeepayOrderId) {
        this.yeepayOrderId = yeepayOrderId;
    }

    public String getQrId() {
        return qrId;
    }

    public void setQrId(String qrId) {
        this.qrId = qrId;
    }

    public BigDecimal getTrxAmt() {
        return trxAmt;
    }

    public void setTrxAmt(BigDecimal trxAmt) {
        this.trxAmt = trxAmt;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getPaySource() {
        return paySource;
    }

    public void setPaySource(String paySource) {
        this.paySource = paySource;
    }

    public String getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return "ORDERINFO [" +
                "customerOrderId='" + customerOrderId + '\'' +
                ", customerNumber='" + customerNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", yeepayOrderId='" + yeepayOrderId + '\'' +
                ", qrId='" + qrId + '\'' +
                ", trxAmt='" + trxAmt + '\'' +
                ", payTime='" + payTime + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", externalId='" + externalId + '\'' +
                ", paySource='" + paySource + '\'' +
                ", balanceType=" + balanceType +
                ", bankType='" + bankType + '\'' +
                ", shopNumber='" + shopNumber + '\'' +
                ", shopName='" + shopName + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ']';
    }


}
