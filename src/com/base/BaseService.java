package com.base;

public interface BaseService<T> {
	public boolean add(T t);

	public boolean remove(T t);

	public boolean update(T t);
}
