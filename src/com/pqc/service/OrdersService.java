package com.pqc.service;



import com.pqc.dao.OrdersDao;
import com.pqc.entity.Orders;


public class OrdersService {

	private OrdersDao ordersDao = new OrdersDao();
	public boolean createOrders(Orders orders) {
		int row = ordersDao.createOrders(orders);
		if(row >0) {
			return true;
		}
		return false;
	}
	public boolean changeState(String ordersNumber) {
		int row = ordersDao.changeState(ordersNumber);
		if(row >0) {
			return true;
		}
		return false;
	}

}
