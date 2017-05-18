package com.wp.service;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wp.bean.UserInfo;
import com.wp.dao.UserDao;

/**
 * @author wenpeng
 * @date 创建时间： 2017年5月9日 上午9:10:47
 * @description 
 */
@Service
public class UserService {

	@Resource
	UserDao userDao;
	public void addUser(UserInfo user)throws SQLException{
		System.out.println("add user in service");
		userDao.addUser(user);
	}
	
	public void updateUser(UserInfo user)throws SQLException{
		System.out.println("update user in service");
		userDao.updateUser(user);
	}
	public void testAfterReturing(UserInfo user)throws SQLException{
		System.out.println("returing in service");
	}
	public void testAfterThrowing(UserInfo user)throws SQLException{
		System.out.println("throwing in service");
		//throw new SQLException();
	}
}
