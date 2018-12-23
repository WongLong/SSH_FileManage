package com.base;

public class BaseServiceImpl<T> implements BaseService<T> {
	protected BaseDao<T> dao; 
	
	@Override
	public boolean add(T t) {
		return dao.save(t);
	}

	@Override
	public boolean remove(T t) {
		return dao.delete(t);
	}

	@Override
	public boolean update(T t) {
		return dao.update(t);
	}

	public BaseDao<T> getDao() {
		return dao;
	}

	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}
}
