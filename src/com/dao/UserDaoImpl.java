package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.base.BaseDaoImpl;
import com.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User>  implements UserDao  {
	@Override
	public User query(Integer id) {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();

		try {
			User user = s.get(User.class, id);
			tx.commit();

			return user;
		} catch (Exception e) {
			tx.rollback();
		} finally {
			s.close();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryAll() {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();

		try {
			String hql = "from " + User.class.getName();
			Query<User> query = s.createQuery(hql);
			tx.commit();

			return query.list();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			s.close();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User login(User u) {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();
		String hql = "from User s where s.name = '" + u.getName() + "'";
		try {

			Query<User> query = s.createQuery(hql);
			tx.commit();

			User user = query.getSingleResult();
			
			return user.getPassword().equals(u.getPassword().trim()) ? user:null;
		} catch (Exception e) {
			tx.rollback();
		} finally {
			s.close();
		}
		
		return null;
	}
}
