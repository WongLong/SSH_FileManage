package com.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BaseDaoImpl<T> implements BaseDao<T> {
	protected SessionFactory factory;
	
	@Override
	public boolean save(T t) {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();
		
		try {
			s.save(t);
			tx.commit();
			
			return true;
		}catch(Exception e) {
			tx.rollback();
		}finally {
			s.close();
		}

		return false;
	}

	@Override
	public boolean delete(T t) {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();
		
		try {
			s.delete(t);
			tx.commit();
			
			return true;
		}catch(Exception e) {
			tx.rollback();
		}finally {
			s.close();
		}
		return false;
	}

	@Override
	public boolean update(T t) {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();
		
		try {
			s.update(t);
			tx.commit();
			
			return true;
		}catch(Exception e) {
			tx.rollback();
		}finally {
			s.close();
		}

		return false;
	}

	public SessionFactory getSessionFactory() {
		return factory;
	}

	public void setSessionFactory(SessionFactory factory) {
		this.factory = factory;
	}
}
