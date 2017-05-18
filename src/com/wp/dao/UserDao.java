package com.wp.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.ibatis.common.resources.Resources;
import com.wp.bean.UserInfo;
import com.wp.rowMapperInterface.UserMapper;
@Repository
public class UserDao {

	//@Resource
	//private SqlSessionFactory sqlSessionFactory;
	public void addUser(UserInfo user)throws SQLException{
		System.out.println("add user in dao");
		//String sql = "insert into user(name,password) values('1','1')";
		String resource = "/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory factory = null;
		factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = factory.openSession();
		
		sqlSession.insert("user.addUser",user);
		
		
		sqlSession.selectOne("user.selectUser");
		Object obj = sqlSession.selectOne("selectUser");
		UserInfo userInfo = (UserInfo)obj;
		System.out.println(userInfo);
		sqlSession.commit();
		
	}
	public void updateUser(UserInfo user)throws SQLException{
		String sql = "insert into user(name,password) values('2','2')";
		//jdbcTemplate.execute(sql);
		
	}
}
