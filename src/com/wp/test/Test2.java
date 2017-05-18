package com.wp.test;

import org.springframework.stereotype.Component;

@Component
public class Test2 {

	int a;
	public Test2(int a){
		this.a = a;
	}
	public Test2(){
		a = 1;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	
	
}
