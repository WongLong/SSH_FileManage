package com.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.base.BaseServiceImpl;
import com.dao.FileDao;
import com.entity.File;
import com.entity.Folder;
import com.entity.User;

public class FileServiceImpl extends BaseServiceImpl<File> implements FileService {
	private FolderService folderService;
	private UserService userService;

	@Override
	public boolean add(File f) {
		if (createFile(f)) {
			return super.add(f);
		}
		return false;
	}

	@Override
	public boolean remove(File f) {
		if (removeFile(f)) {
			return super.remove(f);
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean rename(File file, String name) {
		String newName = name + "." + file.getType();
		if (!file.getFileName().equals(newName)) {
			java.io.File javaFile = new java.io.File(getFilePath(file), file.getFileName());
			file.setFileName(newName);
			if(javaFile.renameTo(new java.io.File(getFilePath(file), file.getFileName()))) {
				file.setUpdateMessage(new Date().toLocaleString() + "    " + userService.query(file.getCreateUserId()).getName());
				return super.update(file);
			}	
		}

		return false;
	}

	@Override
	public boolean uploadFile(File f, java.io.File javaFile) throws IOException {
		String path = this.getFilePath(f);
		java.io.File savedFile = new java.io.File(path, f.getFileName());

		if (!savedFile.exists()) {
			InputStream is = new FileInputStream(javaFile);
			OutputStream os = new FileOutputStream(savedFile);
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = is.read(buffer)) != -1) {
				os.write(buffer, 0, count);
			}

			is.close();
			os.close();

			javaFile.delete();
			return super.add(f);
		}
		return false;
	}

	@Override
	public InputStream downloadFile(File f) throws IOException {
		java.io.File downloadFile = new java.io.File(getFilePath(f), f.getFileName());
		return new FileInputStream(downloadFile);
	}

	@Override
	public List<File> searchFile(File file, Date startDate, Date endDate) {
		return ((FileDao) dao).searchFile(file, startDate, endDate);
	}

	@Override
	public File query(Integer id) {
		return ((FileDao) dao).query(id);
	}

	@Override
	public List<File> queryAll() {
		return ((FileDao) dao).queryAll();
	}

	@Override
	public List<File> queryByFolderId(Integer folderId) {
		return ((FileDao) dao).queryByFolderId(folderId);
	}

	private boolean createFile(File f) {
		String filePath = getFilePath(f);
		java.io.File javaFile = new java.io.File(filePath.toString(), f.getFileName());
		if (!javaFile.exists()) {
			try {
				return javaFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	private boolean removeFile(File f) {
		String filePath = getFilePath(f);
		java.io.File javaFile = new java.io.File(filePath.toString(), f.getFileName());

		if (javaFile.exists()) {
			return javaFile.delete();
		}

		return false;
	}

	private String getFilePath(File f) {
		Integer folderId = f.getFolderId();
		StringBuilder path = new StringBuilder();

		while (folderId != null) {
			Folder folder = folderService.query(folderId);
			path.insert(0, folder.getName());
			path.insert(0, "\\");
			folderId = folder.getLastFolderId();
		}

		User u = userService.query(f.getCreateUserId());
		path.insert(0, u.getName());
		path.insert(0, "d:\\File Manage\\");

		return path.toString();
	}

	public FolderService getFolderService() {
		return folderService;
	}

	public void setFolderService(FolderService folderService) {
		this.folderService = folderService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
