<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上传文件</title>
<link rel="stylesheet" href="../css/fileManage.css" type="text/css">
</head>
<body>
	<div id="head">
		<div id="title_container">
			<div class="title_left">上传文件</div>
			<div class="title_right">
				<ul>
					<li><a href="./fileManage.jsp">关闭</a></li>
					<li><a onclick="uploadFile()">上传</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div
		style="position: relative; margin-left: 15%; width: 70%;; background-color: #e6f7ff; margin-top: 15px; border: 1px solid rgba(167, 204, 231, 0.5);">
		<div
			style="position: inherit; width: 50%; background-color: white; margin: 5% 15% 5% 15%; padding: 2% 10% 5% 10%">
			<form action="uploadFile.action" id="uploadFileForm" method="post" enctype="multipart/form-data">
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
					<div class="labelLeft">文件信息</div>
					<div class="paramRight">
						<s:file name="uploadFile"></s:file>
					</div>
				</div>

				<div class="paramLine">
					<div class="labelLeft">所属文件夹</div>
					<div class="paramRight"
						style="border: 1px solid rgba(167, 204, 231, 0.5);">
						<label><%=request.getSession().getAttribute("currentPath")%></label>
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
		function uploadFile() {
			var form = document.getElementById('uploadFileForm');
			form.submit();
		}
	</script>
</body>
</html>