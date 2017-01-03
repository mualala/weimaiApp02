package com.liancheng.it.entity.user;

import java.io.Serializable;
import java.sql.Timestamp;

public class Coder implements Serializable {
	
	private String id;
	private String code;
	private Timestamp creatime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Timestamp getCreatime() {
		return creatime;
	}
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	
	@Override
	public String toString() {
		return "Coder [id=" + id + ", code=" + code + ", creatime=" + creatime
				+ "]";
	}
	
	
}
