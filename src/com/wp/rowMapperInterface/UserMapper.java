package com.wp.rowMapperInterface;

import org.apache.ibatis.annotations.Select;

import com.wp.bean.UserInfo;

public interface UserMapper {

	//@Select(value="select name,password from user where id = #{id}")
	public UserInfo getUser(int id);
}
