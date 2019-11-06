package com.pqc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pqc.entity.Trolley;
import com.pqc.entity.User;

public class TrolleyDao {

	private QueryRunner qRunner = new QueryRunner(new ComboPooledDataSource());
	public  Trolley comfirmIsExist(Trolley trolley) {
		Trolley t = null;
		try {
			t = qRunner.query("select * from trolley where uid =? and gid= ? and orders_number is null",
					new BeanHandler<Trolley>(Trolley.class),trolley.getUid(),trolley.getGid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	public void addTrolley(Trolley trolley) {
		try {
			qRunner.update("insert into trolley (uid,gid,number) values (?,?,?)",
					trolley.getUid(),trolley.getGid(),trolley.getNumber());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateTrolley(Trolley trolley) {
		try {
			qRunner.update("update trolley set number = ? where tid = ?",trolley.getNumber(),trolley.getTid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int selectTrolleyCount(Integer uid) {
		int count= 0;
		try {
			long c = (long)qRunner.query("select count(*) from trolley where uid = ? and orders_number is null", new ScalarHandler(),uid);
			count =(int)c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public List<Trolley> findAllTrolley(User user) {
		List<Trolley> trolleys = null;
		try {
			trolleys = qRunner.query("select * from trolley where uid = ? and orders_number is null", new BeanListHandler<Trolley>(Trolley.class),user.getUid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trolleys;
	}
	public int addOrDeleteNumber(String tid, String number) {
		int row = 0;
		try {
			row = qRunner.update("update trolley set number = ? where tid = ?",number,tid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public int deleteTrolley(String tid) {
		int row = 0;
		try {
			row = qRunner.update("delete from trolley where tid =?",tid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public int changeTrolley(String ordersNumber, Integer uid) {
		int row = 0;
		try {
			row = qRunner.update("update trolley set orders_number = ? where uid = ? and orders_number is null",ordersNumber,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	
}
