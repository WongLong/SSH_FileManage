package com.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.base.BaseDaoImpl;
import com.entity.File;

public class FileDaoImpl extends BaseDaoImpl<File> implements FileDao  {
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<File> searchFile(File file, Date startDate, Date endDate) {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();

		try {
			StringBuilder hql = new StringBuilder();
			hql.append("from ");
			hql.append(File.class.getName());
			hql.append(" where ");
			if(!file.getFileName().trim().equals("")) {
				hql.append(" and fileName = '");
				hql.append(file.getFileName());
				hql.append("'");
			}
			if(!file.getFileTheme().trim().equals("")) {
				hql.append(" and fileTheme = '");
				hql.append(file.getFileTheme());
				hql.append("'");
			}
			if(!file.getKeyword().trim().equals("")) {
				hql.append(" and keyword = '");
				hql.append(file.getKeyword());
				hql.append("'");
			}
			if(file.getCreateUserId() != -1) {
				hql.append(" and createUserId = '");
				hql.append(file.getCreateUserId());
				hql.append("'");
			}
			if(startDate != null) {
				hql.append(" and createTime >= '");
				hql.append(startDate.toLocaleString());
				hql.append("'");
			}
			if(endDate != null) {
				hql.append(" and createTime <= '");
				hql.append(endDate.toLocaleString());
				hql.append("'");
			}
			int index = hql.indexOf("and", 0);
			hql.delete(index, index + 4);
			
			Query<File> query = s.createQuery(hql.toString());
			tx.commit();
			
			return query.list();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}

		return null;
	}

	@Override
	public File query(Integer id) {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();

		try {
			File file = s.get(File.class, id);
			tx.commit();

			return file;
		} catch (Exception e) {
			tx.rollback();
		} finally {
			s.close();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<File> queryAll() {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();

		try {
			String hql = "from " + File.class.getName();
			Query<File> query = s.createQuery(hql);
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
	public List<File> queryByFolderId(Integer folderId){
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();

		try {
			String hql = "from " + File.class.getName() + " where folderId = " + folderId;
			Query<File> query = s.createQuery(hql);
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
