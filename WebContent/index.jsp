<%@ page language="java" import="java.util.*,bit.work.shop.utils.PropertiesUtils" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>许愿墙</title>
</head>
<body>
<%
String appid=PropertiesUtils.getKeyValue("APPID");
String redirect_uri=PropertiesUtils.getKeyValue("REDIRECTURI");
String codeurl=PropertiesUtils.getKeyValue("FORODEURL");

String targeturl=codeurl.replace("APPID", appid);
targeturl=targeturl.replace("REDIRECT_URI", redirect_uri);
targeturl=targeturl.replace("SCOPE", "snsapi_userinfo");
targeturl=targeturl.replace("STATE", "123");
%>

<c:redirect url="<%=targeturl %>"/>

</body>
</html>