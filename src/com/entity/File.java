package com.entity;

import java.util.Date;

public class File {
	private Integer id;
	private String fileName;
	private String fileTheme;
	private String type;
	private int length;
	private String keyword;
	private Integer folderId;
	private Integer createUserId;
	private Date createTime;
	private String updateMessage;

	public File() {
		
	}
	
	public File(String fileName, String fileTheme, String type, String keyword, Integer folderId) {
		this.fileName = fileName;
		this.fileTheme = fileTheme;
		this.type = type;
		this.keyword = keyword;
		this.folderId = folderId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileTheme() {
		return fileTheme;
	}

	public void setFileTheme(String fileTheme) {
		this.fileTheme = fileTheme;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getFolderId() {
		return folderId;
	}

	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateMessage() {
		return updateMessage;
	}

	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}

}
