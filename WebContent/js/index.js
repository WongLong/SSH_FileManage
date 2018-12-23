function deleteChecked() {
	var fileChoose = document.getElementsByName("fileChoose");
	var folderChoose = document.getElementsByName("folderChoose");

	var filesId = [];
	var foldersId = [];
	for (var i = 0; i < fileChoose.length; i++) {
		if (fileChoose[i].checked) {
			filesId.push(fileChoose[i].value);
		}
	}

	for (var i = 0; i < folderChoose.length; i++) {
		if (folderChoose[i].checked) {
			foldersId.push(folderChoose[i].value);
		}
	}

	$.ajax({
		async: false,
		type: "POST",
		url: "deleteChecked.action",
		data: {
			"filesId": filesId.toString(),
			"foldersId": foldersId.toString()
		},
		dataType: "json",
		success: function(data){
			window.location.href="./fileManage.jsp"; 
		},
		error: function(){
			
		}
	})
}

function history(){
	var form = document.createElement('form');
	form.action = 'history.action';
    form.method = 'post'; 
    $(document.body).append(form);
	form.submit();
}

function lastPage(){
	var form = document.createElement('form');
	var page = document.getElementById("page");
	form.action = 'lastPage.action';
    form.method = 'post'; 
    form.appendChild(page);
    $(document.body).append(form);
	form.submit();
}

function nextPage(){
	var form = document.createElement('form');
	var page = document.getElementById("page");
	form.action = 'nextPage.action';
    form.method = 'post'; 
    form.appendChild(page);
    $(document.body).append(form);
	form.submit();
}

function enterPress(event){
	if(event.keyCode == 13){
		var form = document.createElement('form');
		var page = document.getElementById("page");
		form.action = 'enterPress.action';
	    form.method = 'post'; 
	    form.appendChild(page);
	    $(document.body).append(form);
		form.submit();
	}
}

function renameFolder(id, name){
	document.getElementById("renameFrame").style.display = "block";
	var form = document.getElementById("renameForm");
	form.action = "renameFolder.action";
	document.getElementById("name").value = name;
	document.getElementById("id").value = id;
}

function renameFile(id, name){
	document.getElementById("renameFrame").style.display = "block";
	var form = document.getElementById("renameForm");
	form.action = "renameFile.action";
	document.getElementById("name").value = name.split(".")[0];
	document.getElementById("id").value = id;
}

function cancelRename(){
	document.getElementById("name").value = "";
	document.getElementById("id").value = "";
	document.getElementById("renameFrame").style.display = "none";
}

$(function() {
	$("#all").click(function() {
		var checked = this.checked;
		$("input[name=fileChoose]").each(function() {
			this.checked = checked;
		});

		$("input[name=folderChoose]").each(function() {
			this.checked = checked;
		});
	});

	$("input[name=fileChoose]").click(function() {
		var len1 = $("input[name=fileChoose]:checked").length;
		var len2 = $("input[name=fileChoose]").length;
		var len3 = $("input[name=folderChoose]:checked").length;
		var len4 = $("input[name=folderChoose]").length;
		if (len1 + len3 == len2 + len4) {
			$("#all").get(0).checked = true;
		} else {
			$("#all").get(0).checked = false;
		}
	});

	$("input[name=folderChoose]").click(function() {
		var len1 = $("input[name=fileChoose]:checked").length;
		var len2 = $("input[name=fileChoose]").length;
		var len3 = $("input[name=folderChoose]:checked").length;
		var len4 = $("input[name=folderChoose]").length;
		if (len1 + len3 == len2 + len4) {
			$("#all").get(0).checked = true;
		} else {
			$("#all").get(0).checked = false;
		}
	});
});
