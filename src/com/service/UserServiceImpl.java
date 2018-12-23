package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.base.BaseServiceImpl;
import com.dao.UserDao;
import com.entity.Folder;
import com.entity.User;

public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	private FolderService folderService;

	@Override
	public User query(Integer id) {
		return ((UserDao) dao).query(id);
	}

	@Override
	public List<User> queryAll() {
		return ((UserDao) dao).queryAll();
	}

	@Override
	public User login(User u) {
		return ((UserDao) dao).login(u);
	}

	@Override
	public String initialization(User u) {
		StringBuilder path = new StringBuilder();
		path.append("d:\\File Manage\\");
		path.append(u.getName());

		java.io.File javaFile = new java.io.File(path.toString());
		if (!javaFile.exists()) {
			javaFile.mkdir();
		}

		List<Folder> folders = folderService.queryByUserId(u.getId());

		return getZNodes(folders, u);
	}

	private String getZNodes(List<Folder> folders, User u) {
		Map<Integer, Folder> mapping = new HashMap<>();
		StringBuilder sb = new StringBuilder();

		if (folders.size() != 0) {
			sb.append("[");
			for (Folder folder : folders) {
				sb.append("{id: ");
				sb.append(folder.getId());
				sb.append(", pId: ");
				sb.append(folder.getLastFolderId());
				sb.append(", name: '");
				sb.append(folder.getName());
				sb.append("', isParent: ");
				sb.append(true);
				sb.append("},");

				mapping.put(folder.getId(), folder);
			}

			sb.setCharAt(sb.length() - 1, ']');
			userMapping.put(u.getId(), mapping);
		}

		return sb.toString();
	}

	public FolderService getFolderService() {
		return folderService;
	}

	public void setFolderService(FolderService folderService) {
		this.folderService = folderService;
	}
}
