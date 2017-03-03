package com.liancheng.it.entity.shop;

import java.io.Serializable;
import java.sql.Timestamp;

public class Shop implements Serializable {

	private static final long serialVersionUID = -5213950112149165469L;

	private int shop_id;
	private String user_id;
	private String shop_name;
	private String shop_type;
	private String shop_profile;
	private String shop_pics;
	private String shop_describe;
	private String shop_address;
	private String contacts_name;
	private String contacts_phone;
	private Timestamp shop_creatime;
	
	/**
	 * @return the shop_id
	 */
	public int getShop_id() {
		return shop_id;
	}
	/**
	 * @param shop_id the shop_id to set
	 */
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
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
	 * @return the shop_name
	 */
	public String getShop_name() {
		return shop_name;
	}
	/**
	 * @param shop_name the shop_name to set
	 */
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	/**
	 * @return the shop_type
	 */
	public String getShop_type() {
		return shop_type;
	}
	/**
	 * @param shop_type the shop_type to set
	 */
	public void setShop_type(String shop_type) {
		this.shop_type = shop_type;
	}
	/**
	 * @return the shop_profile
	 */
	public String getShop_profile() {
		return shop_profile;
	}
	/**
	 * @param shop_profile the shop_profile to set
	 */
	public void setShop_profile(String shop_profile) {
		this.shop_profile = shop_profile;
	}
	/**
	 * @return the shop_pics
	 */
	public String getShop_pics() {
		return shop_pics;
	}
	/**
	 * @param shop_pics the shop_pics to set
	 */
	public void setShop_pics(String shop_pics) {
		this.shop_pics = shop_pics;
	}
	/**
	 * @return the shop_describe
	 */
	public String getShop_describe() {
		return shop_describe;
	}
	/**
	 * @param shop_describe the shop_describe to set
	 */
	public void setShop_describe(String shop_describe) {
		this.shop_describe = shop_describe;
	}
	/**
	 * @return the shop_address
	 */
	public String getShop_address() {
		return shop_address;
	}
	/**
	 * @param shop_address the shop_address to set
	 */
	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}
	/**
	 * @return the contacts_name
	 */
	public String getContacts_name() {
		return contacts_name;
	}
	/**
	 * @param contacts_name the contacts_name to set
	 */
	public void setContacts_name(String contacts_name) {
		this.contacts_name = contacts_name;
	}
	/**
	 * @return the contacts_phone
	 */
	public String getContacts_phone() {
		return contacts_phone;
	}
	/**
	 * @param contacts_phone the contacts_phone to set
	 */
	public void setContacts_phone(String contacts_phone) {
		this.contacts_phone = contacts_phone;
	}
	/**
	 * @return the shop_creatime
	 */
	public Timestamp getShop_creatime() {
		return shop_creatime;
	}
	/**
	 * @param shop_creatime the shop_creatime to set
	 */
	public void setShop_creatime(Timestamp shop_creatime) {
		this.shop_creatime = shop_creatime;
	}
	
}
