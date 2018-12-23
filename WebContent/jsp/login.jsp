<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件管理系统</title>
</head>
<body>
	<s:form theme="simple" action="login" method="post">
		用户名:<s:textfield label="用户名" name="name"></s:textfield>
		<br />
		密&nbsp;&nbsp;&nbsp;码:<s:password label="密码" name="password"></s:password>
		<s:submit label="提交" value="提交"></s:submit>
	</s:form>
	<div style="color: red">
		<%
			if (request.getAttribute("error") != null) {
		%>
		<%=request.getAttribute("error")%>
		<%
			}
		%>
	</div>
</body>
</html>