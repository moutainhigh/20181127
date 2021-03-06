<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<%--<jsp:useBean id="customer" scope="request" type="com.yeepay.g3.core.ymf.entity.customer.Customer"/>--%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>二维码详情</title>
    <script language="JavaScript">
        function downLoadImage(ftpUrl){
            window.location="${pageContext.request.contextPath}/download/downloadPic?ftpUrl="+ftpUrl;
        }
    </script>
</head>
<body>
    <div class="search_tit">
          <h2 class="fw">二维码详情</h2>
     </div>
     <div class="clearer"></div>
    <div style="margin-left: 10px;margin-top: 20px;">
        <table border="1" style="width:98%">
            <tr>
                <td colspan="4">
                    <label class="text_tit" style="font-size: 16px;">基本信息</label>
                </td>
            </tr>
            <tr>
                <td width="14%">
                    <label class="text_tit" >商户编号</label>
                </td>
                <td width="14%">
                    <label class="text_tit">${customer.customerNumber}</label>
                </td>
                <td width="24%">
                    <label class="text_tit" >商户名称</label>
                </td>
                <td width="44%">
                <label class="text_tit"> ${customer.customerName}</label></td>
            </tr>
            <tr>
                <td width="14%">
                    <label class="text_tit" >应用类型</label>
                </td>
                <td width="14%"><label class="text_tit">
                    <dic:write type="AppType" code="${customer.appType}"/></label>
                </td>
                <td width="24%">
                    <label  class="text_tit" >二维码ID</label>
                </td>
                <td width="44%">
                    <label class="text_tit" style="width: 35%;">${qrCode.qrId}</label>
                </td>

            </tr>
        </table>
    </div>
    <div style="margin-left: 10px;margin-top: 20px;">
        <table border="1" style="width:98%">
            <tr>
                <td colspan="4">
                    <label class="text_tit" style="font-size: 16px;">二维码样式</label>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div>
                    <img src="${qrCode.ftpUrl}">
                    </div>
                    <div style="margin-top: -31px;margin-left: 500px;">
                    <input type="button" value="下载" onclick="downLoadImage('${qrCode.ftpUrl}');" class="btn_sure fw" >
                     </div>
                </td>

            </tr>
        </table>
    </div>

</body>
</html>
