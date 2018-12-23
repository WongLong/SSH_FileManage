package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.base.BaseDaoImpl;
import com.entity.OptRecord;

public class OptRecordDaoImpl extends BaseDaoImpl<OptRecord> implements OptRecordDao {
	@Override
	public OptRecord query(Integer id) {		
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();

		try {
			OptRecord optRecord = s.get(OptRecord.class, id);
			tx.commit();

			return optRecord;
		} catch (Exception e) {
			tx.rollback();
		} finally {
			s.close();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OptRecord> queryAll() {		
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();

		try {
			String hql = "from " + OptRecord.class.getName();
			Query<OptRecord> query = s.createQuery(hql);
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
