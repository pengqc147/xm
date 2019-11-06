package com.pqc.service;

import java.util.List;

import com.pqc.dao.TrolleyDao;
import com.pqc.entity.Goods;
import com.pqc.entity.Trolley;
import com.pqc.entity.User;

public class TrolleyService {

	private TrolleyDao trolleyDao = new TrolleyDao();
	private GoodsService goodsService = new GoodsService();
	/*
 	现在参数trolley中只有uid、gid有值
 	主键tid自增长无需考虑
 	订单编号orders_number 默认为null也无需考虑
 	但是商品数量number需要在此赋值
   */
    /*
 	当前添加的商品对应的购物车对象是否存在，是我们需要考虑的问题
 	如果存在，我们只需要累加number商品数量即可	sql执行修改update操作	返回true
 	如果不存在，将number赋值为1	sql执行insert插入操作	返回false
    */
    //查询数据库，查看当前商品对应的购物车记录是否存在
	public  boolean addTrolley(Trolley trolley) {
		Trolley t = trolleyDao.comfirmIsExist(trolley);
		/*System.out.println(t);
		System.out.println("=="+trolley.getUid());
		System.out.println("--"+trolley.getGid());
		*/
		if(t==null) {
			//执行添加方法
			trolley.setNumber(1);
			trolleyDao.addTrolley(trolley);
			return false;
		}else {
			//执行修改方法
			t.setNumber(t.getNumber()+1);
			trolleyDao.updateTrolley(t);
			return true;
		}
		
	}
	public int selectTrolleyCount(Integer uid) {
		return trolleyDao.selectTrolleyCount(uid);
	}
	public List<Trolley> findAllTrolley(User user) {
		List<Trolley> trolleys = trolleyDao.findAllTrolley(user);
		/*此处得到购物车对象后，要为虚拟属性user goods赋值
		 * 用于在购物车的展示页面使用
		*/
		for (Trolley trolley : trolleys) {
			trolley.setUser(user);
			//获取当前购物车对象绑定的商品对象
			Goods goods = goodsService.findGoodsById(trolley.getGid()+"");
			trolley.setGoods(goods);
		}
		return trolleys;
	}
	public boolean addOrDeleteNumber(String tid, String number) {
		int row = trolleyDao.addOrDeleteNumber(tid,number);
		if(row >0) {
			return true;
		}
		return false;
	}
	public boolean deleteTrolley(String tid) {
		int row = trolleyDao.deleteTrolley(tid);
		if(row >0) {
			return true;
		}
		return false;
	}
	public boolean changeTrolley(String ordersNumber, Integer uid) {
		int row = trolleyDao.changeTrolley(ordersNumber,uid);
		if(row >0) {
			return true;
		}
		return false;
	}
	
}
