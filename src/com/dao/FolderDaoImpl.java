package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.base.BaseDaoImpl;
import com.entity.Folder;

public class FolderDaoImpl extends BaseDaoImpl<Folder> implements FolderDao {
	@Override
	public Folder query(Integer id) {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();

		try {
			Folder folder = s.get(Folder.class, id);
			tx.commit();

			return folder;
		} catch (Exception e) {
			tx.rollback();
		} finally {
			s.close();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Folder> queryAll() {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();

		try {
			String hql = "from " + Folder.class.getName();
			Query<Folder> query = s.createQuery(hql);
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
	public List<Folder> queryByUserId(Integer userId) {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();

		try {
			String hql = "from " + Folder.class.getName() + " s where s.createUserId = " + userId;
			Query<Folder> query = s.createQuery(hql);
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
	public List<Folder> queryByFolderId(Integer folderId){
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();

		try {
			String hql = "from " + Folder.class.getName() + " where lastFolderId = " + folderId;
			Query<Folder> query = s.createQuery(hql);
			tx.commit();

			return query.list();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			s.close();
		}

		return null;
	}
}
