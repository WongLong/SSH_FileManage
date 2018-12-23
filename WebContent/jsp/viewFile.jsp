<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看文件</title>
<link rel="stylesheet" href="../css/fileManage.css" type="text/css">
</head>
<body>
	<div id="head">
		<div id="title_container">
			<div class="title_left">查看文件</div>
			<div class="title_right">
				<ul>
					<li><a href="./fileManage.jsp">关闭</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div
		style="position: relative; margin-left: 15%; width: 70%;; background-color: #e6f7ff; margin-top: 15px; border: 1px solid rgba(167, 204, 231, 0.5);">
		<div
			style="position: inherit; width: 50%; background-color: white; margin: 5% 15% 5% 15%; padding: 2% 10% 5% 10%">
			<div class="paramLine">
				<div class="labelLeft">创建者姓名</div>
				<div class="paramRight">
					<input type="text" name="creatorName" readonly="readonly" value="${creatorName }"/>
				</div>
			</div>

			<div class="paramLine">
				<div class="labelLeft">文件名称</div>
				<div class="paramRight">
					<input type="text" name="fileName" readonly="readonly" value="${viewedFile.fileName }"/>
				</div>
			</div>

			<div class="paramLine">
				<div class="labelLeft">文件主题</div>
				<div class="paramRight">
					<input type="text" name="fileTheme" readonly="readonly" value="${viewedFile.fileTheme }">
				</div>
			</div>

			<div class="paramLine">
				<div class="labelLeft">文件类型</div>
				<div class="paramRight">
					<input type="text" name="type" readonly="readonly" value="${viewedFile.type }">
				</div>
			</div>
			
			<div class="paramLine">
				<div class="labelLeft">关键字</div>
				<div class="paramRight">
					<input type="text" name="keyword" readonly="readonly" value="${viewedFile.keyword }">
				</div>
			</div>
			
			<div class="paramLine">
				<div class="labelLeft">文件大小</div>
				<div class="paramRight">
					<input type="text" name="length" readonly="readonly" value="${viewedFile.length }">
				</div>
			</div>
			
			<div class="paramLine">
				<div class="labelLeft">创建时间</div>
				<div class="paramRight">
					<input type="text" name="createTime" readonly="readonly" value="${viewedFile.createTime }">
				</div>
			</div>
			
			<div class="paramLine">
				<div class="labelLeft">最近更新</div>
				<div class="paramRight">
					<input type="text" name="updateMessage" readonly="readonly" value="${viewedFile.updateMessage }">
				</div>
			</div>
		</div>
	</div>
</body>
</html>