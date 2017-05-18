package com.wp.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Test {

	@Pointcut("execution( * com.wp.test.Disc.*(int)) && args(num)")
	public void commonPointCut(int num){
		
	}
	@Before("commonPointCut(num)")
	public void inTest1(int num){
		System.out.println(num);
		System.out.println("inTest1");
	}
	
	
	public void inMethod(ProceedingJoinPoint pj){
		System.out.println("in");
		try {
			pj.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("out");
	}
	
}
