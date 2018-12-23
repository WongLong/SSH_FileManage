package com.dao;

import java.util.List;

import com.base.BaseDao;
import com.entity.OptRecord;

public interface OptRecordDao extends BaseDao<OptRecord> {
	public OptRecord query(Integer id);

	public List<OptRecord> queryAll();
}
