package com.liancheng.it.entity.user;

import java.io.Serializable;
import java.sql.Timestamp;

public class ShoppingAddress implements Serializable {

	private static final long serialVersionUID = -5107889661467189521L;
	
	private int addr_id;
	private String user_id;
	private String name;
	private String phone;
	private String area;
	private String address;
	private Timestamp addr_creatime;
	
	/**
	 * @return the addr_id
	 */
	public int getAddr_id() {
		return addr_id;
	}
	/**
	 * @param addr_id the addr_id to set
	 */
	public void setAddr_id(int addr_id) {
		this.addr_id = addr_id;
	}
	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the addr_creatime
	 */
	public Timestamp getAddr_creatime() {
		return addr_creatime;
	}
	/**
	 * @param addr_creatime the addr_creatime to set
	 */
	public void setAddr_creatime(Timestamp addr_creatime) {
		this.addr_creatime = addr_creatime;
	}
	
	
}
