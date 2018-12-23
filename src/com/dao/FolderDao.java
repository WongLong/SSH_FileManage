package com.dao;

import java.util.List;

import com.base.BaseDao;
import com.entity.Folder;

public interface FolderDao extends BaseDao<Folder> {
	public Folder query(Integer id);

	public List<Folder> queryAll();
	
	public List<Folder> queryByUserId(Integer userId);
	
	public List<Folder> queryByFolderId(Integer folderId);
}
