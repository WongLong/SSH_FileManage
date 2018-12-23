package com.entity;

import java.util.Date;

public class OptRecord {
	private Integer id;
	private String optUserName;
	private Date optTime;
	private String optMethod;

	public OptRecord() {
		
	}
	
	public OptRecord(String optUserName, Date optTime, String optMethod) {
		this.optUserName = optUserName;
		this.optTime = optTime;
		this.optMethod = optMethod;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public String getOptMethod() {
		return optMethod;
	}

	public void setOptMethod(String optMethod) {
		this.optMethod = optMethod;
	}

	public String getOptUserName() {
		return optUserName;
	}

	public void setOptUserName(String optUserName) {
		this.optUserName = optUserName;
	}
}
