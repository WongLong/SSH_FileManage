package com.service;

import java.util.List;

import com.base.BaseService;
import com.entity.Folder;

public interface FolderService extends BaseService<Folder> {
	public Folder query(Integer id);

	public List<Folder> queryAll();

	public boolean rename(Folder folder, String name);
	
	public List<Folder> queryByUserId(Integer userId);

	public List<Folder> queryByFolderId(Integer folderId);
}
