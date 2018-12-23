package com.action;

import java.util.List;
import java.util.Map;

import com.entity.OptRecord;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.OptRecordService;

public class OptRecordAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OptRecordService optRecordService;
	private int page;

	public String getHistoryRecord() {
		page = 1;
		Map<String, Object> session = ActionContext.getContext().getSession();
		List<OptRecord> historyList = optRecordService.queryAll();
		session.put("recordList", historyList);
		session.put("totalPage", historyList.size() % 10 > 0 ? historyList.size() / 10 + 1 : historyList.size() / 10);

		return SUCCESS;
	}

	public String lastPage() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		int total = (int) session.get("totalPage");
		page = page - 1 < 1 ? 1 : page - 1;
		page = page > total ? total : page;
		return SUCCESS;
	}

	public String nextPage() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		int total = (int) session.get("totalPage");
		page = page + 1 > total ? total : page + 1;
		page = page < 1 ? 1 : page;
		return SUCCESS;
	}
	
	public String enterPress() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		int total = (int)session.get("totalPage");
		if(page < 0) {
			page = 1;
		}else if(page > total) {
			page = total;
		}
		return SUCCESS;
	}

	public OptRecordService getOptRecordService() {
		return optRecordService;
	}

	public void setOptRecordService(OptRecordService optRecordService) {
		this.optRecordService = optRecordService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
