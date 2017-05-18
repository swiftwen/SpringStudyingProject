package com.wp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import com.wp.bean.UserInfo;

//@Aspect
public class MyAspect {

	//@Before("execution(* com.wp.service.UserService.addUser(..))")
	public void in(){
		System.out.println("in");
	}
	
	//@After("execution(* com.wp.service.UserService.addUser(..))")
	public void out(){
		System.out.println("out");
	}
	
	public void around(ProceedingJoinPoint p)throws Throwable{
		
		Object[] objs = p.getArgs();
		UserInfo u = (UserInfo)objs[0];
		System.out.println(u);
		System.out.println("around");
		p.proceed();
	}
	public void afterReturing(){
		System.out.println("after returing");
	}
	public void afterThrowing(){
		System.out.println("after throwing");
	}
}
