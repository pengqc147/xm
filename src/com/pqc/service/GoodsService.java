package com.pqc.service;

import java.util.List;

import com.pqc.dao.GoodsDao;
import com.pqc.entity.Category;
import com.pqc.entity.Goods;
import com.pqc.utils.PageTool;

public class GoodsService {
	private GoodsDao goodsDao = new GoodsDao();
    private CateService cateService = new CateService();
	public boolean insertGoods(Goods goods) {
		int row =goodsDao.insertGoods(goods);
		//System.out.println(row);
		if(row >0) {
			return true;
		}
		return false;
	}

	public int findGoodsCount() {
		
		return goodsDao.findGoodsCount();
	}

	public List<Goods> findAllGoods(PageTool pageTool) {
		List<Goods> goodses = goodsDao.findAllGoods(pageTool);
		for (Goods goods : goodses) {
			Category category = cateService.findCateById(goods.getCid()+"");
			goods.setCategory(category);
		}
		return goodses;
	}

	public Goods findGoodsById(String gid) {
		
		return goodsDao.findGoodsById(gid);
	}

	public boolean updateGoods(Goods goods) {
		int row = goodsDao.updateGoods(goods);
		if(row>0) {
			return true;
		}
		return false;
	}
	public List<Goods> findGoodsByState(int state, int count) {
		return goodsDao.findGoodsByState(state,count);
	}

	public List<Goods> findGoodsByCid(int cid, int count) {
		return goodsDao.findGoodsByCid(cid,count);
	}

	public List<Goods> findGoodsBySearch(String info, int count) {
		return goodsDao.findGoodsBySearch(info,count);
	}
}
	