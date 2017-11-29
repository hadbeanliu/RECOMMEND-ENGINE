<%@page import="java.util.Date"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
String serverName = request.getServerName();
String baseUrl = serverName != null && serverName.startsWith("192.168.") ? "" : "";
%><html>
<body>
<h2>欢迎使用推荐引擎</h2>

<a href="user/novalidate/reg.htm">注册界面</a><br/><br/>

</body>
</html>
