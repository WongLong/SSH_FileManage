package com.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.entity.File;
import com.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.FileService;
import com.service.UserService;

public class FileAction extends ActionSupport implements ModelDriven<File> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.io.File uploadFile;
	private String uploadFileFileName;
	private String uploadFileContentType;

	private File file;
	private FileService fileService;
	private UserService userService;

	private String downFileName;
	private Integer downloadFileId;

	private Date startDate;
	private Date endDate;

	private String name;

	@SuppressWarnings("deprecation")
	public String addFile() {
		if (file.getFileName().trim().equals("")) {
			ServletActionContext.getRequest().setAttribute("error", "文件名不能为空！");
			return ERROR;
		}

		Map<String, Object> session = ActionContext.getContext().getSession();
		Integer folderId = (Integer) session.get("folderId");
		User u = (User) session.get("loginedUser");
		file.setCreateTime(new java.util.Date());
		file.setCreateUserId(u.getId());
		file.setFolderId(folderId);
		file.setLength(0);
		file.setUpdateMessage(file.getCreateTime().toLocaleString() + "    " + u.getName());
		file.setFileName(file.getFileName() + "." + file.getType());

		if (!fileService.add(file)) {
			ServletActionContext.getRequest().setAttribute("error", "当前文件夹下该文件已存在！");
			return ERROR;
		}

		if (folderId != null) {
			session.put("fileList", fileService.queryByFolderId(folderId));
		}
		return SUCCESS;
	}

	public String renameFile() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer id = (Integer) request.getAttribute("id");

		File file = fileService.query(id);
		fileService.rename(file, name);

		Map<String, Object> session = ActionContext.getContext().getSession();
		Integer folderId = (Integer) session.get("folderId");
		session.put("fileList", fileService.queryByFolderId(folderId));
		return SUCCESS;
	}

	public String searchFile() {
		String keyword = file.getKeyword();
		String fileName = file.getFileName();
		String fileTheme = file.getFileTheme();
		Integer createUserId = file.getCreateUserId();

		if (fileName.trim().equals("") && fileTheme.trim().equals("") && keyword.trim().equals("") && createUserId == -1
				&& startDate == null && endDate == null) {
			ServletActionContext.getRequest().setAttribute("error", "请输入查询信息！");

			return INPUT;
		} else if (startDate != null && endDate != null) {
			if (startDate.compareTo(endDate) > 0) {
				ServletActionContext.getRequest().setAttribute("error", "开始时间不能超过结束时间！");

				return INPUT;
			}
		}

		Map<String, Object> session = ActionContext.getContext().getSession();
		User u = (User) session.get("loginedUser");
		List<File> searchedFile = fileService.searchFile(file, startDate, endDate);
		session.put("fileList", searchedFile);
		session.remove("folderList");
		session.remove("folderId");
		session.put("currentPath", u.getName() + "/");

		return SUCCESS;
	}

	public String viewFile() {
		File viewedFile = fileService.query(file.getId());
		User u = userService.query(viewedFile.getCreateUserId());
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("viewedFile", viewedFile);
		request.setAttribute("creatorName", u.getName());
		
		return SUCCESS;
	}

	@SuppressWarnings("deprecation")
	public String uploadFile() throws IOException {
		if (uploadFile == null) {
			ServletActionContext.getRequest().setAttribute("error", "请选择文件！");
			return ERROR;
		}

		file.setFileName(uploadFileFileName);
		file.setType(
				uploadFileFileName.substring(uploadFileFileName.lastIndexOf(".") + 1, uploadFileFileName.length()));
		file.setLength((int) uploadFile.length() / 1024);

		Map<String, Object> session = ActionContext.getContext().getSession();
		Integer folderId = (Integer) session.get("folderId");
		User u = (User) session.get("loginedUser");
		file.setCreateTime(new java.util.Date());
		file.setCreateUserId(u.getId());
		file.setFolderId(folderId);
		file.setUpdateMessage(file.getCreateTime().toLocaleString() + "    " + u.getName());

		if (!fileService.uploadFile(file, uploadFile)) {
			ServletActionContext.getRequest().setAttribute("error", "当前文件夹下该文件已存在！");
			return ERROR;
		}

		if (folderId != null) {
			session.put("fileList", fileService.queryByFolderId(folderId));
		}

		return SUCCESS;
	}

	public InputStream getDownloadFile() throws IOException {
		File downloadFile = fileService.query(downloadFileId);
		this.downFileName = new String(downloadFile.getFileName().getBytes(), "ISO8859-1");
		return fileService.downloadFile(downloadFile);
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public java.io.File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(java.io.File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	public Integer getDownloadFileId() {
		return downloadFileId;
	}

	public void setDownloadFileId(Integer downloadFileId) {
		this.downloadFileId = downloadFileId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public File getModel() {
		return file;
	}

}
