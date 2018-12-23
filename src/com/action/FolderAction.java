package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import com.entity.File;
import com.entity.Folder;
import com.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.FileService;
import com.service.FolderService;
import com.service.UserService;

public class FolderAction extends ActionSupport implements ModelDriven<Folder> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> json = new HashMap<>();
	private Folder folder;

	private FileService fileService;
	private FolderService folderService;
	private UserService userService;
	private Integer folderId;

	public String clickFolder() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		List<File> fileList = fileService.queryByFolderId(folderId);
		List<Folder> folderList = folderService.queryByFolderId(folderId);
		json.put("fileList", fileList);
		json.put("folderList", folderList);

		StringBuilder currentPath = new StringBuilder();
		User u = (User) session.get("loginedUser");
		Map<Integer, Folder> folderMapping = UserService.userMapping.get(u.getId());

		Integer tempId = folderId;
		while (tempId != null) {
			Folder folder = folderMapping.get(tempId);
			currentPath.insert(0, "/");
			currentPath.insert(0, folder.getName());
			tempId = folder.getLastFolderId();
		}
		currentPath.insert(0, "/");
		currentPath.insert(0, u.getName());

		json.put("currentPath", currentPath.toString());
		session.put("fileList", fileList);
		session.put("folderList", folderList);
		session.put("folderId", folderId);
		session.put("currentPath", currentPath.toString());
		return "json";
	}

	public String addFolder() {
		if (folder.getName().trim().equals("")) {
			ServletActionContext.getRequest().setAttribute("error", "文件夹名不能为空！");
			return ERROR;
		}

		Map<String, Object> session = ActionContext.getContext().getSession();
		User u = (User) session.get("loginedUser");
		folder.setCreateUserId(u.getId());
		folder.setLastFolderId(folderId);

		if(!folderService.add(folder)) {
			ServletActionContext.getRequest().setAttribute("error", "当前文件夹下该文件夹已存在！");
			return ERROR;
		}
		if (folderId != null) {
			session.put("folderList", folderService.queryByFolderId(folderId));
		}
		session.put("zNodes", userService.initialization(u));
		return SUCCESS;
	}

	public String renameFolder() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = (String)request.getAttribute("name");
		Integer id = (Integer)request.getAttribute("id");
		
		Folder folder = folderService.query(id);
		folderService.rename(folder, name);

		Map<String, Object> session = ActionContext.getContext().getSession();
		Integer folderId = (Integer)session.get("folderId");
		User u = (User) session.get("loginedUser");
		session.put("folderList", folderService.queryByFolderId(folderId));
		session.put("zNodes", userService.initialization(u));
		return SUCCESS;
	}
	
	public Integer getFolderId() {
		return folderId;
	}

	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
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

	public Map<String, Object> getJson() {
		return json;
	}

	public void setJson(Map<String, Object> json) {
		this.json = json;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Folder getModel() {
		return folder;
	}
}
