package com.pqc.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pqc.entity.Orders;


public class OrdersDao {

	QueryRunner qRunner = new QueryRunner(new ComboPooledDataSource());  
	public int createOrders(Orders orders) {
		int row = 0;
		try {
			row = qRunner.update("insert into orders  values (?,?,?,?,?,?,0)",orders.getOrdersNumber(),orders.getUid(),orders.getSumPrice(),orders.getGoodsCount(),orders.getOrdersName(),orders.getCreate_time());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public int changeState(String ordersNumber) {
		int row = 0;
		try {
			row = qRunner.update("update orders set state =1 where ordersNumber = ?",ordersNumber);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

}
