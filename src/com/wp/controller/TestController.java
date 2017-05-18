package com.wp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wp.bean.UserInfo;
import com.wp.service.UserService;



@Controller
@RequestMapping("/abc")
@Transactional
public class TestController extends BaseController {
	


	
/*	@Resource
	private RedisTemplate redisTemplate;*/
	
	//@Resource
	//private SessionFactory sessionFactory;
	
	@Resource
	private UserService userService;


	@RequestMapping(value="/test",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView test(HttpServletRequest req,String name,String password)throws Exception {
		
		/*DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions("com/wp/controller/beanDefinition.xml");
		TestService service = (TestService)beanFactory.getBean("testService");
		service.say();*/
		UserInfo user = new UserInfo();
		user.setName(name);
		user.setPassword(password);
		userService.addUser(user);
		
		
		/*userService.addUser(null);
		UserInfo user = new UserInfo();
		user.setName("wenpeng");
		user.setPassword("password");
		userService.updateUser(user);
		userService.testAfterReturing(null);
		userService.testAfterThrowing(null);*/
		
		
	  /*  redisTemplate.opsForList().rightPush("a", "1");
	    redisTemplate.opsForList().rightPush("a", "2");
	    redisTemplate.opsForList().rightPush("a", "3");
	    
	    Object obj = redisTemplate.opsForList().rightPop("a");
	    System.out.println(obj);
	    Object obj1 = redisTemplate.opsForList().leftPop("a");
	    System.out.println(obj1);
	    Object obj2 = redisTemplate.opsForList().rightPop("a");
	    System.out.println(obj2);*/
		
		/*Session session = sessionFactory.getCurrentSession();
		UserInfo user = new UserInfo();
		user.setName("a");
		user.setPassword("a");
		session.save(user);*/
		//return "/index.jsp";	
		
		ModelAndView model = new ModelAndView();
		model.addObject("name","wenpeng");
		model.setViewName("/index.jsp");
		Map map = model.getModel();
		map.put("time", new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()));
		map.put("name","wenpeng");
		map.put("age",23);
		return model;
		
	}
	
}


