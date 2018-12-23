package com.service;

import java.util.List;

import com.base.BaseService;
import com.entity.OptRecord;

public interface OptRecordService extends BaseService<OptRecord> {
	public OptRecord query(Integer id);
	
	public List<OptRecord> queryAll();
}
