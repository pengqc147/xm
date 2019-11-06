package com.pqc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pqc.entity.Goods;
import com.pqc.utils.PageTool;

public class GoodsDao {
    QueryRunner  qRunner = new QueryRunner(new ComboPooledDataSource());
	public int insertGoods(Goods goods) {
		int row = 0;
		try {
			row = qRunner.update("insert into goods values (null,?,?,?,?,?,?,?,?,?,?,?)",
					goods.getCid(),goods.getGname(),goods.getColor(),goods.getSize(),
					goods.getPrice(),goods.getDescription(),goods.getFull_description(),
					goods.getPic(),goods.getState(),goods.getVersion(),goods.getProduct_date());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public int findGoodsCount() {
		int count = 0;
		try {
			long c = (long)qRunner.query("select count(*) from goods", new ScalarHandler());
			count = (int)c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return count;
	}
	public List<Goods> findAllGoods(PageTool pageTool) {
		List<Goods> goodses = null;
		try {
			goodses = qRunner.query("select * from goods limit ?,?", new BeanListHandler<Goods>(Goods.class),pageTool.getStartIndex(),pageTool.getPageCount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodses;
	}
	public Goods findGoodsById(String gid) {
		Goods goods =null;
		try {
			goods = qRunner.query("select * from goods where gid = ?",new BeanHandler<Goods>(Goods.class),gid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
	}
	public int updateGoods(Goods goods) {
		int row = 0;
		try {
			row = qRunner.update("update goods set cid =?,gname = ?, color =? ,size = ?, price = ?, description = ?, full_description = ?,pic = ?,state = ?, version = ?,product_date = ? where gid =? ",
					goods.getCid(),goods.getGname(),goods.getColor(),goods.getSize(),
					goods.getPrice(),goods.getDescription(),goods.getFull_description(),
					goods.getPic(),goods.getState(),goods.getVersion(),goods.getProduct_date(),goods.getGid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	public List<Goods> findGoodsByState(int state, int count) {
		List<Goods> goods = null;
		try {
			goods = qRunner.query("select * from goods where state =? order by gid desc limit ?", new BeanListHandler<Goods>(Goods.class),state,count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
	}
	public List<Goods> findGoodsByCid(int cid, int count) {
		List<Goods> goods = null;
		try {
			goods = qRunner.query("select * from goods where cid = ? order by gid desc limit ?",new BeanListHandler<Goods>(Goods.class),cid,count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
	}
	public List<Goods> findGoodsBySearch(String info, int count) {
		List<Goods> goods = null;
		StringBuilder sb = new StringBuilder("select * from goods where ");
		if(!info.contains("#")) {
			sb.append("gname like '%"+info+"%' ");
		}else {
			//∞¥’’#∑÷∏Óinfo
			String[] str = info.split("#");
			for (int i = 0; i < str.length; i++) {
				if(i == 0) {
					sb.append("gname like '%"+str[i]+"%' ");
				}else {
					sb.append("or gname like '%"+str[i]+"%' ");
				}
			}
		}
		sb.append("limit "+count);
		try {
			goods = qRunner.query(sb.toString(), new BeanListHandler<Goods>(Goods.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
	}
}




