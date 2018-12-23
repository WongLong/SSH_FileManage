<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.entity.User"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查找文件</title>
<link rel="stylesheet" href="../css/fileManage.css" type="text/css">
</head>
<body>
	<div id="head">
		<div id="title_container">
			<div class="title_left">查找文件</div>
			<div class="title_right">
				<ul>
					<li><a href="./fileManage.jsp">关闭</a></li>
					<li><a onclick="searchFile()">查询</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div
		style="position: relative; margin-left: 15%; width: 70%;; background-color: #e6f7ff; margin-top: 15px; border: 1px solid rgba(167, 204, 231, 0.5);">
		<div
			style="position: inherit; width: 50%; background-color: white; margin: 5% 15% 5% 15%; padding: 2% 10% 5% 10%">
			<form action="searchFile.action" id="searchFileForm" method="post">
				<div class="paramLine">
					<div class="labelLeft">文件名称</div>
					<div class="paramRight">
						<input type="text" name="fileName" />
					</div>
				</div>

				<div class="paramLine">
					<div class="labelLeft">文件主题</div>
					<div class="paramRight">
						<input type="text" name="fileTheme" />
					</div>
				</div>

				<div class="paramLine">
					<div class="labelLeft">关键字</div>
					<div class="paramRight">
						<input type="text" name="keyword">
					</div>
				</div>

				<div class="paramLine">
					<div class="labelLeft">创建者</div>
					<div class="paramRight">
						<s:select name="createUserId" list="#session.userList" headerValue="请选择创建者" listValue="name" headerKey="-1" listKey="id" theme="simple" style=" width: 100%; color: #024394; font-family: STZhongsong;  border: 1px solid rgba(167, 204, 231, 0.5); padding: 1% 2% 1% 2%">
						</s:select>
					</div>
				</div>
				
				<div class="paramLine" style="height: 70px">
					<div class="labelLeft">创建日期</div>
					<div class="paramRight">
						<input type="Date" name="startDate"> 至 <input type="Date" name="endDate">
					</div>
				</div>
			</form>
			<div
				style="color: red; text-align: center; width: 100%; margin-top: 10px">
				<%
					if (request.getAttribute("error") != null) {
				%>
				<%=request.getAttribute("error")%>
				<%
					}
				%>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function searchFile(){
			var form = document.getElementById('searchFileForm');
			form.submit();
		}
	</script>
</body>
</html>