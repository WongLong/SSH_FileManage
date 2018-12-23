package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.base.BaseService;
import com.entity.Folder;
import com.entity.User;

public interface UserService extends BaseService<User> {
	public static Map<Integer, Map<Integer, Folder>> userMapping = new HashMap<>();
	
	public User query(Integer id);

	public List<User> queryAll();

	public User login(User u);
	
	public String initialization(User u);
}
