package com.dao;

import java.util.List;

import com.base.BaseDao;
import com.entity.User;

public interface UserDao extends BaseDao<User> {
	public User query(Integer id);

	public List<User> queryAll();

	public User login(User u);
}
