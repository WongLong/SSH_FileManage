package com.base;

public interface BaseDao<T> {
	public boolean save(T t);

	public boolean delete(T t);

	public boolean update(T t);

}
