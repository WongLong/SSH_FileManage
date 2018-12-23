package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.File;
import com.entity.Folder;
import com.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.FileService;
import com.service.FolderService;
import com.service.UserService;

public class DeleteAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> json = new HashMap<>();
	private FileService fileService;
	private FolderService folderService;
	private UserService userService;
	private String filesId;
	private String foldersId; 
	
	public String deleteChecked() {	
		Map<String, Object> session = ActionContext.getContext().getSession();
		boolean flag = false;
		
		if(!filesId.equals("")) {
			String[] files = filesId.split(",");
			for(String fileId: files) {
				fileService.remove(fileService.query(Integer.parseInt(fileId)));
			}
			flag = true;
		}
		
		if(!foldersId.equals("")) {
			String[] folders = foldersId.split(",");
			for(String folderId: folders) {
				folderService.remove(folderService.query(Integer.parseInt(folderId)));
			}
			flag = true;
			
			User u = (User) session.get("loginedUser");
			session.put("zNodes", userService.initialization(u));
		}
		
		if(!flag) {
			return SUCCESS;
		}

		Integer lastFolderId = (Integer)session.get("folderId");
		List<File> fileList = fileService.queryByFolderId(lastFolderId);
		List<Folder> folderList = folderService.queryByFolderId(lastFolderId);
		session.put("fileList", fileList);
		session.put("folderList", folderList);
		return "json";
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public FolderService getFolderService() {
		return folderService;
	}

	public void setFolderService(FolderService folderService) {
		this.folderService = folderService;
	}

	public String getFilesId() {
		return filesId;
	}

	public void setFilesId(String filesId) {
		this.filesId = filesId;
	}

	public String getFoldersId() {
		return foldersId;
	}

	public void setFoldersId(String foldersId) {
		this.foldersId = foldersId;
	}

	public Map<String, Object> getJson() {
		return json;
	}

	public void setJson(Map<String, Object> json) {
		this.json = json;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
