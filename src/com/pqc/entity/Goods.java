package com.pqc.entity;

import java.util.Date;

/*
 * 
	`gid` INT PRIMARY KEY AUTO_INCREMENT,
	`cid` INT,
	`gname` VARCHAR(50) NOT NULL,
	`color` VARCHAR(50),
	`size` VARCHAR(50),
	`price` DOUBLE NOT NULL,
	`description` VARCHAR(500),
	`full_description` VARCHAR(1000),
	`pic` VARCHAR(200),
	`state` INT DEFAULT 0,
	`version` VARCHAR(50),
	`product_date` DATETIME,
*/
public class Goods {
	private Integer gid;
	private Integer cid;
	//这条只做存储功能，不会插入数据，也不会在构造方法中传值
	private Category category;
	private String gname;
	private String color;
	private String size;
	private Double price;
	private String description;
	private String full_description;
	private String pic;
	private Integer state;
	private String version;
	private Date product_date;
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFull_description() {
		return full_description;
	}
	public void setFull_description(String full_description) {
		this.full_description = full_description;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getProduct_date() {
		return product_date;
	}
	public void setProduct_date(Date product_date) {
		this.product_date = product_date;
	}
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Goods(Integer gid, Integer cid, String gname, String color, String size, Double price, String description,
			String full_description, String pic, Integer state, String version, Date product_date) {
		super();
		this.gid = gid;
		this.cid = cid;
		this.gname = gname;
		this.color = color;
		this.size = size;
		this.price = price;
		this.description = description;
		this.full_description = full_description;
		this.pic = pic;
		this.state = state;
		this.version = version;
		this.product_date = product_date;
	}
	public Goods(String gname, String color, String size, Double price, String description, String full_description,
			String pic, Integer state, String version, Date product_date) {
		super();
		this.gname = gname;
		this.color = color;
		this.size = size;
		this.price = price;
		this.description = description;
		this.full_description = full_description;
		this.pic = pic;
		this.state = state;
		this.version = version;
		this.product_date = product_date;
	}
	
	public Goods(Integer cid, String gname, String color, String size, Double price, String description,
			String full_description, String pic, Integer state, String version, Date product_date) {
		super();
		this.cid = cid;
		this.gname = gname;
		this.color = color;
		this.size = size;
		this.price = price;
		this.description = description;
		this.full_description = full_description;
		this.pic = pic;
		this.state = state;
		this.version = version;
		this.product_date = product_date;
	}
	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", cid=" + cid + ", gname=" + gname + ", color=" + color + ", size=" + size
				+ ", price=" + price + ", description=" + description + ", full_description=" + full_description
				+ ", pic=" + pic + ", state=" + state + ", version=" + version + ", product_date=" + product_date + "]";
	}
	
}





