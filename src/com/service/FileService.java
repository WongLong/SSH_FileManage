package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.base.BaseService;
import com.entity.File;

public interface FileService extends BaseService<File> {
	public boolean uploadFile(File f, java.io.File javaFile) throws IOException;
	
	public InputStream downloadFile(File f) throws IOException;
	
	public List<File> searchFile(File file, Date startDate, Date endDate);
	
	public boolean rename(File file, String name);

	public File query(Integer id);

	public List<File> queryAll();
	
	public List<File> queryByFolderId(Integer folderId);
}
