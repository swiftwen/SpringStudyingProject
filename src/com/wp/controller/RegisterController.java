package com.wp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wp.bean.UserInfo;



@Controller
@RequestMapping("/user")
public class RegisterController extends BaseController {
	
	private UserInfo userInfo;
	
	@RequestMapping(value="/register",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView test( UserInfo userInfo,HttpServletRequest request,String test) {
		ModelAndView model = new ModelAndView();
		if(null==userInfo.getName()){
			userInfo = new UserInfo();
			model.setViewName("/page/user/register.jsp");
			return model;
		}else{
			System.out.println(userInfo);
			model.setViewName("/page/user/registerSuc.jsp");
			return model;
		}
		
	}




	public UserInfo getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	

	
}
