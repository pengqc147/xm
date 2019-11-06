package com.pqc.entity;
/*CREATE TABLE orders(
	ordersNumber VARCHAR(100) PRIMARY KEY,
	uid INT NOT NULL,
	sumPrice DOUBLE NOT NULL,
	goodsCount INT NOT NULL,
	ordersName VARCHAR(30) NOT NULL,
	create_time DATETIME NOT NULL,
	state INT DEFAULT 0
);
*/

import java.util.Date;
import java.util.List;

public class Orders {
	private String ordersNumber;//�������
	private Integer uid;
	private User user;//������󣬲���������ݿ⣬ֻ��Ϊ�˷���ʹ��
	
	private Double sumPrice;
	private Integer goodsCount;
	private String ordersName;
	private Date create_time;
	//0��ʾδ֧��  1��ʾ��֧��   2�ѷ��� 3������  4���ջ�
	private Integer state;
	//����Ʒ�������
	private List<Trolley> trolleys;
	public String getOrdersNumber() {
		return ordersNumber;
	}
	public void setOrdersNumber(String ordersNumber) {
		this.ordersNumber = ordersNumber;
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
	public Double getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	public Integer getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}
	public String getOrdersName() {
		return ordersName;
	}
	public void setOrdersName(String ordersName) {
		this.ordersName = ordersName;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public List<Trolley> getTrolleys() {
		return trolleys;
	}
	public void setTrolleys(List<Trolley> trolleys) {
		this.trolleys = trolleys;
	}
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Orders(String ordersNumber, Integer uid, Double sumPrice, Integer goodsCount, String ordersName,
			Date create_time) {
		super();
		this.ordersNumber = ordersNumber;
		this.uid = uid;
		this.sumPrice = sumPrice;
		this.goodsCount = goodsCount;
		this.ordersName = ordersName;
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "Orders [ordersNumber=" + ordersNumber + ", uid=" + uid + ", user=" + user + ", sumPrice=" + sumPrice
				+ ", goodsCount=" + goodsCount + ", ordersName=" + ordersName + ", create_time=" + create_time
				+ ", state=" + state + ", trolleys=" + trolleys + "]";
	}
	

}




