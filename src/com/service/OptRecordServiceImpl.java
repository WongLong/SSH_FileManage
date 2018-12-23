package com.service;

import java.util.List;

import com.base.BaseServiceImpl;
import com.dao.OptRecordDao;
import com.entity.OptRecord;

public class OptRecordServiceImpl extends BaseServiceImpl<OptRecord> implements OptRecordService{
	@Override
	public OptRecord query(Integer id) {		
		return ((OptRecordDao) dao).query(id);
	}

	@Override
	public List<OptRecord> queryAll() {		
		return ((OptRecordDao) dao).queryAll();
	}

}
