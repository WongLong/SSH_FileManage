<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.entity.OptRecord" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>历史记录</title>
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<link rel="stylesheet" href="../css/fileManage.css" type="text/css">
</head>
<body>
	<div id="head">
		<div id="title_container">
			<div class="title_left">历史记录</div>
			<div class="title_right">
				<ul>
					<li><a href="./fileManage.jsp">关闭</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	<div style="width: 100%; height: auto; margin: 1% 10% 1% 10%;">
		<table style="width: 80%; border: 1px solid grey;">
			<thead>
				<tr>
					<th
						style="text-align: center; color: white; background-color: #5fa1d0; border: 1px solid grey; width: 33%">用户</th>
					<th
						style="text-align: center; color: white; background-color: #5fa1d0; border: 1px solid grey; width: 33%">时间</th>
					<th
						style="text-align: center; color: white; background-color: #5fa1d0; border: 1px solid grey; width: 33%">操作</th>
				</tr>
			</thead>
			<tbody id="table">
				<s:if test="#session.recordList.size() == 0">
					<tr style="font-size: 12px; height: 25px;">
						<td colspan="3" style="border: 1px solid grey; padding: 0 5px 0 5px; font-size: 70px; font-weight: 800; text-align: center">空</td>
					</tr>
				</s:if>
				<s:else>
					<% 
						List<OptRecord> historyList = (List<OptRecord>)request.getSession().getAttribute("recordList");
						int currentPage = (int)request.getAttribute("page");
						int totalPage = (int)request.getSession().getAttribute("totalPage");
					%>
					
					<% for(int i = (currentPage - 1) * 10; i < (currentPage - 1) * 10 + 10; i++) {%>
						<% if(i == historyList.size()) break;%>
						<tr style="font-size: 12px; height: 25px;">
							<td style="border: 1px solid grey; width: 33%; padding: 4px 5px 4px 5px; font-size: 15px; color: #508ace"><%=historyList.get(i).getOptUserName() %></td>
							<td style="border: 1px solid grey; width: 33%; padding: 4px 5px 4px 5px; font-size: 15px; color: #508ace"><%=historyList.get(i).getOptTime() %></td>
							<td style="border: 1px solid grey; width: 33%; padding: 4px 5px 4px 5px; font-size: 15px; color: #508ace"><%=historyList.get(i).getOptMethod() %></td>
						</tr>
					<% } %>
					
					<tr style="font-size: 12px; height: 25px;">
						<td colspan="3"><button style="float: left; color: #024394" onclick="lastPage()">上一页</button><input type="number" style="width: 40px; margin-left: 42%; color: #024394" id="page" name="page" value="${page }" onkeypress="enterPress(event)"><label style="color: #024394">/共<%=totalPage %>页</label><button style="float: right; color: #024394" onclick="nextPage()">下一页</button></td>
					</tr>
				</s:else>
			</tbody>
		</table>
	</div>
</body>
</html>