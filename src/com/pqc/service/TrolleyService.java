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
 	���ڲ���trolley��ֻ��uid��gid��ֵ
 	����tid���������迼��
 	�������orders_number Ĭ��ΪnullҲ���迼��
 	������Ʒ����number��Ҫ�ڴ˸�ֵ
   */
    /*
 	��ǰ��ӵ���Ʒ��Ӧ�Ĺ��ﳵ�����Ƿ���ڣ���������Ҫ���ǵ�����
 	������ڣ�����ֻ��Ҫ�ۼ�number��Ʒ��������	sqlִ���޸�update����	����true
 	��������ڣ���number��ֵΪ1	sqlִ��insert�������	����false
    */
    //��ѯ���ݿ⣬�鿴��ǰ��Ʒ��Ӧ�Ĺ��ﳵ��¼�Ƿ����
	public  boolean addTrolley(Trolley trolley) {
		Trolley t = trolleyDao.comfirmIsExist(trolley);
		/*System.out.println(t);
		System.out.println("=="+trolley.getUid());
		System.out.println("--"+trolley.getGid());
		*/
		if(t==null) {
			//ִ����ӷ���
			trolley.setNumber(1);
			trolleyDao.addTrolley(trolley);
			return false;
		}else {
			//ִ���޸ķ���
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
		/*�˴��õ����ﳵ�����ҪΪ��������user goods��ֵ
		 * �����ڹ��ﳵ��չʾҳ��ʹ��
		*/
		for (Trolley trolley : trolleys) {
			trolley.setUser(user);
			//��ȡ��ǰ���ﳵ����󶨵���Ʒ����
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
