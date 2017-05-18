package com.wp.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

/**
 * @author wenpeng
 * @date 创建时间： 2017年1月16日 下午1:54:19
 * @description 
 */
//@Entity
//@Table(name="user")
@Alias("user")
public class UserInfo {

	//@Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    //@Column(name = "id", nullable = false)
	private int id;
	

	private String name;
	

	private String password;


	

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
