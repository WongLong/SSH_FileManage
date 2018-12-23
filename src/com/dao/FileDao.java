package com.dao;

import java.util.Date;
import java.util.List;

import com.base.BaseDao;
import com.entity.File;

public interface FileDao extends BaseDao<File> {
	public List<File> searchFile(File file, Date startDate, Date endDate);
	
	public File query(Integer id);
	
	public List<File> queryAll();
	
	public List<File> queryByFolderId(Integer folderId);
}
