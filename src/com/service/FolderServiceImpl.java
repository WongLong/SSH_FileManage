package com.service;

import java.util.List;

import com.base.BaseServiceImpl;
import com.dao.FolderDao;
import com.entity.File;
import com.entity.Folder;
import com.entity.User;

public class FolderServiceImpl extends BaseServiceImpl<Folder> implements FolderService {
	private UserService userService;
	private FileService fileService;

	@Override
	public boolean add(Folder folder) {
		if (createFolder(folder)) {
			return super.add(folder);
		}

		return false;
	}

	@Override
	public boolean remove(Folder folder) {
		List<File> listFile = fileService.queryByFolderId(folder.getId());
		List<Folder> listFolder = this.queryByFolderId(folder.getId());
		for (File file : listFile) {
			fileService.remove(file);
		}
		for (Folder f : listFolder) {
			this.remove(f);
		}

		removeFolder(folder);
		return super.remove(folder);
	}

	@Override
	public boolean rename(Folder folder, String name) {
		if (!folder.getName().equals(name)) {
			java.io.File javaFile = new java.io.File(getFolderPath(folder));
			folder.setName(name);
			if (javaFile.renameTo(new java.io.File(getFolderPath(folder)))) {
				return super.update(folder);
			}
		}

		return false;
	}

	@Override
	public Folder query(Integer id) {
		return ((FolderDao) dao).query(id);
	}

	@Override
	public List<Folder> queryAll() {
		return ((FolderDao) dao).queryAll();
	}

	@Override
	public List<Folder> queryByUserId(Integer userId) {
		return ((FolderDao) dao).queryByUserId(userId);
	}

	@Override
	public List<Folder> queryByFolderId(Integer folderId) {
		return ((FolderDao) dao).queryByFolderId(folderId);
	}

	private boolean createFolder(Folder folder) {
		String folderPath = getFolderPath(folder);
		java.io.File javaFile = new java.io.File(folderPath);
		if (!javaFile.exists()) {
			return javaFile.mkdir();
		}

		return false;
	}

	private boolean removeFolder(Folder folder) {
		String folderPath = getFolderPath(folder);
		java.io.File javaFile = new java.io.File(folderPath);

		if (javaFile.exists()) {
			return javaFile.delete();
		}

		return false;
	}

	private String getFolderPath(Folder folder) {
		Integer lastFolderId = folder.getLastFolderId();
		StringBuilder path = new StringBuilder();
		path.append(folder.getName());
		path.insert(0, "\\");

		while (lastFolderId != null) {
			Folder lastFolder = query(lastFolderId);
			path.insert(0, lastFolder.getName());
			path.insert(0, "\\");
			lastFolderId = lastFolder.getLastFolderId();
		}

		User u = userService.query(folder.getCreateUserId());
		path.insert(0, u.getName());
		path.insert(0, "d:\\File Manage\\");

		return path.toString();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
}
