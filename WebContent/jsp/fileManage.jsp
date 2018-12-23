<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文件管理系统</title>
<link rel="stylesheet" href="../css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="../js/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<link rel="stylesheet" href="../css/fileManage.css" type="text/css">
</head>
<body>
	<div id="head">
		<div id="title_container">
			<div class="title_left">文件管理</div>
			<div class="title_right">
				<ul>
					<li><a href="getAllUser.action">查询</a></li>
					<li><a href="./uploadFile.jsp">上传文件</a></li>
					<li><a href="./addFile.jsp">新建文件</a></li>
					<li><a href="./addFolder.jsp">新建文件夹</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div id="center">
		<div id="path_title">当前路径:</div>
		<span id="path_image"></span>
		<div id="path_container">
			<label id="current_path"><%=request.getSession().getAttribute("currentPath")%></label>
		</div>
	</div>

	<div id="main">
		<div id="fileTreeCantainer">
			<div id="fileTreeHead">
				<span id="treeHeadImage"></span>文件管理
			</div>
			<ul id="fileTree" class="ztree"></ul>
		</div>

		<div id="fileContainer">
			<table style="width: 100%; border: 1px solid grey;">
				<thead>
					<tr>
						<th
							style="text-align: center; color: white; background-color: #5fa1d0; border: 1px solid grey; width: 3%"></th>
						<th
							style="text-align: center; color: white; background-color: #5fa1d0; border: 1px solid grey; width: 47%">名称</th>
						<th
							style="text-align: center; color: white; background-color: #5fa1d0; border: 1px solid grey; width: 8%">大小(kb)</th>
						<th
							style="text-align: center; color: white; background-color: #5fa1d0; border: 1px solid grey; width: 21%">最后更新</th>
						<th
							style="text-align: center; color: white; background-color: #5fa1d0; border: 1px solid grey; width: 21%">操作</th>
					</tr>
				</thead>
				<tbody id="table">
					<s:iterator value="#session.folderList">
						<tr style="font-size: 12px; height: 25px;">
							<td style="border: 1px solid grey; width: 3%;"><input
								type="checkbox" name="folderChoose" value='${id }'></td>
							<td
								style="border: 1px solid grey; width: 45%; padding: 0 5px 0 5px"><span
								class="fileIcon folderIcon"></span> <s:property value="name" /></td>
							<td
								style="text-align: right; border: 1px solid grey; width: 8%; padding: 0 5px 0 5px"></td>
							<td
								style="border: 1px solid grey; width: 22%; padding: 0 5px 0 5px"></td>
							<td
								style="text-align: center; border: 1px solid grey; width: 22%"><a
								onclick="renameFolder('${id }', '${name }')">重命名</a></td>
						</tr>
					</s:iterator>

					<s:iterator value="#session.fileList">
						<tr style="font-size: 12px; height: 25px;">
							<td style="border: 1px solid grey; width: 3%;"><input
								type="checkbox" name="fileChoose" value='${id }'></td>
							<s:if test="type == 'txt'">
								<td
									style="border: 1px solid grey; width: 45%; padding: 0 5px 0 5px"><span
									class="fileIcon txtIcon"></span> <s:property value="fileName" /></td>
							</s:if>
							<s:elseif test="type=='pptx'">
								<td
									style="border: 1px solid grey; width: 45%; padding: 0 5px 0 5px"><span
									class="fileIcon pptxIcon"></span> <s:property value="fileName" /></td>
							</s:elseif>
							<s:elseif test="type=='doc'">
								<td
									style="border: 1px solid grey; width: 45%; padding: 0 5px 0 5px"><span
									class="fileIcon docIcon"></span> <s:property value="fileName" /></td>
							</s:elseif>
							<s:elseif test="type=='html'">
								<td
									style="border: 1px solid grey; width: 45%; padding: 0 5px 0 5px"><span
									class="fileIcon htmlIcon"></span> <s:property value="fileName" /></td>
							</s:elseif>
							<s:else>
								<td
									style="border: 1px solid grey; width: 45%; padding: 0 5px 0 5px"><span
									class="fileIcon jpgIcon"></span> <s:property value="fileName" /></td>
							</s:else>
							<td
								style="text-align: right; border: 1px solid grey; width: 8%; padding: 0 5px 0 5px"><s:property
									value="length" /></td>
							<td
								style="border: 1px solid grey; width: 22%; padding: 0 5px 0 5px"><s:property
									value="updateMessage" /></td>
							<td
								style="text-align: center; border: 1px solid grey; width: 22%;"><a
								onclick="renameFile('${id }', '${fileName }')">重命名</a> 
								<s:a action="viewFile?id=%{id}">查看</s:a> 
								<s:a action="downloadFile?downloadFileId=%{id}">下载</s:a></td>
						</tr>
					</s:iterator>

					<tr style="font-size: 12px; height: 25px;">
						<td style="width: 3%;"><input type="checkbox" id="all"></td>
						<td colspan="4"><button style="float: left; margin-left: 2%"
								onclick="deleteChecked()">删除</button>
							<button style="float: right; margin-right: 2%"
								onclick="history()">历史</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div
		style="position: fixed; width: 100%; height: 100%; top: 0; left: 0; background: rgba(0, 0, 0, .6); z-index: 2; display: none"
		id="renameFrame">
		<div
			style="position: fixed; top: 20%; left: 38%; display: inline-block; background-color: white; padding: 2%; z-index: 10; width: 25%">
			<h2
				style="margin: 0 0 6% 0; font-size: 20px; line-height: 100%; font-weight: 200">重命名</h2>
			<form id="renameForm" method="post">
				<div>
					<label style="font-size: 13px; color: #7f7f7f; line-height: 36px">名称：</label>
					<input id="name" type="text" name="name"
						style="font-size: 13px; width: 80%; float: right; outline: 0; line-height: 1em; padding: 9px 5px; border: 1px solid #dbdbdb; color: rgba(0, 0, 0, .87); border-radius: 2px;" />
					<input id="id" type="text" name="id" style="display: none">
				</div>

				<div style="margin-top: 8%; display: inline-block; width: 100%">
					<input type="button" onclick="cancelRename()" value="取消"
						style="float: right; border: 1px solid #e6e6e6; background-image: none; background: 0 0 !important; color: rgba(0, 0, 0, .6); font-weight: 400; border-radius: 2px; text-transform: none; text-shadow: none !important; line-height: 28px; padding: 0 18px; cursor: pointer;">
					<input type="submit" value="确定"
						style="float: right; background: #4386f5; border: 1px solid #4386f5; color: #fff; text-shadow: none; background-image: none; margin-right: 5%; padding: 0 18px; line-height: 28px; text-align: center; border-radius: 2px; font-family: 'Arial, 黑体'; cursor: pointer;">
				</div>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		var setting = {
			data : {
				keep : {
					parent : false,
				},
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPId : null
				}
			},

			view : {
				showLine : false,
			},

			callback : {
				onClick : zTreeOnClick
			}
		}
		var zTreeObj = $.fn.zTree.init($("#fileTree"), setting,
	<%=request.getSession().getAttribute("zNodes")%>
		);

		function zTreeOnClick(event, treeId, treeNode) {
			$.ajax({
				async : false,
				type : "POST",
				url : "clickFolder.action",
				data : {
					"folderId" : treeNode.id
				},
				dataType : "json",
				success : function(data) {
					$("#current_path").text(data.currentPath);
					var table = document.getElementById("table");
					table.innerHTML = "";
					drawFolder(data.folderList, table);
					drawFile(data.fileList, table);
					drawBottom(table);
				},
				error : function() {

				}
			})
		}

		function drawFolder(array, obj) {
			var context = "";
			for (var i = 0; i < array.length; i++) {
				context += "<tr style='font-size: 12px;height: 25px;'><td style='border: 1px solid grey; width: 3%;'><input type='checkbox' name='folderChoose' value='" + array[i].id + "'></td>";
				context += "<td style='border: 1px solid grey; width: 45%; padding: 0 5px 0 5px'><span class='fileIcon folderIcon'></span>"
						+ array[i].name + "</td>";
				context += "<td style='text-align: right; border: 1px solid grey; width: 8%; padding: 0 5px 0 5px'></td>";
				context += "<td style='border: 1px solid grey; width: 22%; padding: 0 5px 0 5px'></td>";
				context += "<td style='text-align: center; border: 1px solid grey; width: 22%'><a onclick=renameFolder('" + array[i].id + "','" + array[i].name + "')>重命名</a</td></tr>";
			}

			obj.innerHTML += context;
		}

		function drawFile(array, obj) {
			var context = "";
			for (var i = 0; i < array.length; i++) {
				context += "<tr style='font-size: 12px;height: 25px;'><td style='border: 1px solid grey; width: 3%;'><input type='checkbox' name='fileChoose' value='" + array[i].id + "'></td>"
				var className = "fileIcon " + array[i].type + "Icon";
				context += "<td style='border: 1px solid grey; width: 45%; padding: 0 5px 0 5px'><span class='" + className + "'></span>"
						+ array[i].fileName + "</td>";
				context += "<td style='text-align: right; border: 1px solid grey; width: 8%; padding: 0 5px 0 5px'>"
						+ array[i].length + "</td>";
				context += "<td style='border: 1px solid grey; width: 22%; padding: 0 5px 0 5px'>"
						+ array[i].updateMessage + "</td>";
				context += "<td style='text-align: center; border: 1px solid grey; width: 22%'><a onclick=renameFile('" + array[i].id + "','" + array[i].fileName + "')>重命名</a> <a href='viewFile.action?id=" + array[i].id + "'>查看</a> <a href='downloadFile.action?downloadFileId="
						+ array[i].id + "'>下载</a></td></tr>";
			}

			obj.innerHTML += context;
		}

		function drawBottom(obj) {
			obj.innerHTML += "<tr style='font-size: 12px;height: 25px;'><td style='width: 3%;'><input type='checkbox' id='all'></td><td colspan='4'><button style='float: left; margin-left: 2%' onclick='deleteChecked()'>删除</button><button style='float: right; margin-right: 2%' onclick='history()'>历史</button></td></tr>";
		}
	</script>
</body>
</html>