package com.pqc.entity;
/*CREATE TABLE trolley (
	tid INT PRIMARY KEY AUTO_INCREMENT,#����
	uid INT,#�����û���id
	gid INT,#������Ʒ��id
	number INT,#����Ʒ������
	orders_number VARCHAR(100) DEFAULT NULL#�������
);
*/
public class Trolley {

	private Integer tid;//����
	private Integer uid;//�û�id
	private User user;//�������������ݲ���Ҫ�洢���ݿ⣬ֻ��Ϊ����ʾ����
	private Integer gid;//��Ʒid
	private Goods goods;//�������������ݲ���Ҫ�洢���ݿ⣬ֻ��Ϊ����ʾ����
	private Integer number;//����Ʒ������
	private String orders_number;//�������
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getOrders_number() {
		return orders_number;
	}
	public void setOrders_number(String orders_number) {
		this.orders_number = orders_number;
	}
	
	public Trolley(Integer uid, Integer gid) {
		super();
		this.uid = uid;
		this.gid = gid;
	}
	public Trolley() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Trolley [tid=" + tid + ", uid=" + uid + ", user=" + user + ", gid=" + gid + ", goods=" + goods
				+ ", number=" + number + ", orders_number=" + orders_number + "]";
	}
	
}