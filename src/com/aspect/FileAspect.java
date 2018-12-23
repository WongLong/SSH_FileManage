package com.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;

import com.entity.File;
import com.entity.OptRecord;
import com.entity.User;
import com.service.OptRecordService;
import com.service.UserService;

public class FileAspect {
	private UserService userService;
	private OptRecordService optRecordService;
	
	public Object aroundRecord(ProceedingJoinPoint point) throws Throwable {
		File f = (File)point.getArgs()[0];
		String methodName = point.getSignature().getName();
		User u =userService.query(f.getCreateUserId());
		String optMethod = "";
		
		if(methodName.contains("add")) {
			optMethod = "新增文件: " + f.getFileName();
		}else if(methodName.contains("remove")) {
			optMethod = "删除文件: " + f.getFileName();
		}else if(methodName.contains("rename")) {
			optMethod = "更新文件: " + f.getFileName();
		}else if(methodName.contains("upload")) {
			optMethod = "上传文件: " + f.getFileName();
		}else if(methodName.contains("download")) {
			optMethod = "下载文件: " + f.getFileName();
		}
			
		Object returnObj = point.proceed();
		
		OptRecord record = new OptRecord();
		record.setOptTime(new Date());
		record.setOptUserName(u.getName());
		record.setOptMethod(optMethod);
		optRecordService.add(record);

		return returnObj;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public OptRecordService getOptRecordService() {
		return optRecordService;
	}

	public void setOptRecordService(OptRecordService optRecordService) {
		this.optRecordService = optRecordService;
	}
}
