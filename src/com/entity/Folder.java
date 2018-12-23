package com.entity;

public class Folder {
	private Integer id;
	private String name;
	private String keyword;
	private Integer lastFolderId;
	private Integer createUserId;

	public Folder() {
		
	}
	
	public Folder(String name, String keyword, Integer lastFolderId, Integer createUserId) {
		this.name = name;
		this.keyword = keyword;
		this.lastFolderId = lastFolderId;
		this.createUserId = createUserId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getLastFolderId() {
		return lastFolderId;
	}

	public void setLastFolderId(Integer lastFolderId) {
		this.lastFolderId = lastFolderId;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	
}
