package com.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;

import com.entity.Folder;
import com.entity.OptRecord;
import com.entity.User;
import com.service.OptRecordService;
import com.service.UserService;

public class FolderAspect {
	private UserService userService;
	private OptRecordService optRecordService;
	
	public Object aroundRecord(ProceedingJoinPoint point) throws Throwable {
		Folder folder = (Folder)point.getArgs()[0];
		String methodName = point.getSignature().getName();
		User u =userService.query(folder.getCreateUserId());
		String optMethod = "";
		
		if(methodName.contains("add")) {
			optMethod = "新增文件夹: " + folder.getName();
		}else if(methodName.contains("remove")) {
			optMethod = "删除文件夹: " + folder.getName();
		}else{
			optMethod = "更新文件夹: " + folder.getName();
		}
		
		OptRecord record = new OptRecord();
		record.setOptTime(new Date());
		record.setOptUserName(u.getName());
		record.setOptMethod(optMethod);
		optRecordService.add(record);
		
		Object returnObj = point.proceed();
		
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
