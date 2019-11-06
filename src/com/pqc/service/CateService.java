package com.pqc.service;

import java.util.List;

import com.pqc.dao.CateDao;
import com.pqc.entity.Category;
import com.pqc.utils.PageTool;

public class CateService {

	private CateDao cateDao = new CateDao();
	public int selectCateCount() {
		return cateDao.selectCateCount();
	}
	public List<Category> findAllCate(PageTool pageTool) {
		return cateDao.findAllCate(pageTool);
	}
	public boolean insertCate(Category category) {
		int row = cateDao.insertCate(category);
		if(row>0) {
			return true;
		}
		return false;
	}
	public Category findCateById(String cid) {
		return cateDao.findCateById(cid);
	}
	public boolean updateCate(Category category) {
		int row = cateDao.updateCate(category);
		if(row>0) {
			return true;
		}
		return false;
	}
	public List<Category> findAllCate() {
		return cateDao.findAllCate();
	}
	public boolean deleteCate(String ids) {
		int row = cateDao.deleteCate(ids);
		if(row>0) {
			return true;
		}
		return false;
	}
	public List<Category> findAllCate(int count) {
		return cateDao.findAllCate(count);
	}
	

}
