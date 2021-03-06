package com.yeepay.g3.facade.ymf.enumtype.trade;

/**
 * 交易响应码
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 16/10/24
 * @Time: 下午5:49
 */
public enum TrxCode {
    /**
     * 成功
     */
    T00("00","成功"),
    /**
     * 系统异常,请稍后重试
     */
    T1000("1000","系统异常,请稍后重试"),
    /**
     * 验证签名失败
     */
    T1001("1001","验证签名失败"),
    /**
     * 二维码不存在或已失效
     */
    T1002("1002","二维码不存在或已失效"),
    /**
     * 该商户已关闭或暂停交易,请稍后重试
     */
    T1003("1003","该商户已关闭或暂停交易,请稍后重试"),
    /**
     * 公众号参数配置错误,请联系易宝支付
     */
    T1004("1004","公众号参数配置错误,请联系易宝支付"),
    /**
     * 网络异常或超时,请稍后重试
     */
    T1005("1005","网络异常或超时,请稍后重试"),
    /**
     * 参数不完整
     */
    T1006("1006","参数不完整"),
    /**
     * 调用COD接口异常,请稍后重试
     */
    T1007("1007","调用COD接口异常,请稍后重试"),
    /**
     * 该订单已支付
     */
    T1008("1008","该订单已支付"),
    /**
     * 该订单超时失效,请重新生成订单号
     */
    T1009("1009","该订单超时失效,请重新生成订单号"),
    /**
     * 此订单下单账号与支付账号不一致，请使用下单微信账号支付
     */
    T1010("1010","此订单下单账号与支付账号不一致，请使用下单微信账号支付"),
    /**
     * 收银台下单异常,请稍后重试
     */
    T1011("1011","收银台下单异常,请稍后重试"),
    /**
     * 商户未开通支付权限，请联系易宝支付
     */
    T1012("1012","商户未开通支付权限，请联系易宝支付"),
    /**
     * 商户商品类别码未配置,请联系易宝支付.
     */
    T1013("1013","商户商品类别码未配置,请联系易宝支付"),
    /**
     * 获取远程服务失败
     */
    T1014("1014","获取远程服务失败"),
    /**
     * 商户配置应用版本异常,请联系客服!
     */
    T1015("1015","商户配置应用版本异常,请联系客服!"),
    /**
     * 类型转换错误
     */
    T1016("1016","类型转换错误"),
    /**
     * 二维码未开通支付版本
     */
    T1017("1017","二维码未开通支付版本"),
    T1018("1018","交易权限不足,请联系客服"),
    T1019("1019","二维码未绑定商户,请联系客服"),
    T1020("1020","采购数量超限,最多采购200个"),
    T1021("1021","未查询到代理商信息!"),
    T1022("1022","二维码已绑定!"),
    T1023("1023","商户未配置,请联系客服"),
    T1024("1024","商户未配置支付方式"),
    T1025("1025","商户未配置收款方式"),
    T1026("1026","业务方已存在"),
    T1027("1027","商户已开通来客产品"),
    T1028("1028","商户应用类型未配置"),
    T1029("1029","使用微信公众号请求"),
    T1030("1030","未配置信息"),
    /**
     * 订单中心下单异常,请稍后重试
     */
    T1031("1031","订单中心下单异常,请稍后重试"),
    /**
     * YOP签名或验签失败
     */
    T1032("1032","YOP签名或验签失败"),
    T1033("1033","客户中心查询商户关系失败"),
    T1034("1034","被扫回调验签不一致"),
    T1035("1035","商户未开通秒到产品"),
    T1036("1036","交易金额小于起结金额"),
    T1037("1037","交易时间未在秒到打款周期内"),
    T1038("1038","银联终端号不可用"),
    T1039("1039","商户未报备"),
    /**
     * 二维码所在网点已暂停交易
     */
    T1040("1040","二维码所在网点已暂停交易"),
    T1041("1041","该交易存在风险，请谨慎付款"),
    T1042("1042",""),
    T1043("1043",""),
    T1044("1044",""),
    T1045("1045",""),
    T1046("1046",""),
    T1047("1047",""),
    T1048("1048",""),
    T1049("1049",""),
    T1050("1050",""),
    T1051("1051",""),
    T1052("1052",""),
    T1053("1053",""),
    T1054("1054",""),
    T1055("1055",""),
    T1056("1056",""),
    T1057("1057",""),
    T1058("1058",""),
    T1059("1059",""),
    T1060("1060",""),
    ;

    private String msg;
    private String code;

    TrxCode(String code, String msg) {
        this.code = code ;
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public String toPromptMsg() {
        return "[" + getCode() + "]" + getMsg() ;
    }

    /**
     * 根据错误码返回枚举值
     * @param code      错误码
     * @return
     */
    public TrxCode parseTrxCode(String code) {
        try {
            return valueOf(code);
        } catch (Exception e) {
            return null ;
        }
    }
}
