package com.wp.test;

public class Disc {

	private String info;
	private String author;
	public Disc(String info,String author){
		this.info = info;
		this.author = author;
	}
	public Disc(){
		this.info = "default info";
		this.author = "default author";
	}
	
	public void show(){
		System.out.println("author = "+author+",info = "+info);
	}
	public void test1(int num){
		System.out.println("in test1() "+"the num = "+num);
	}
	public void test2(){
		System.out.println("in test2()");
	}
	public void test3(){
		System.out.println("in test3()");
	}
	public void test4(){
		System.out.println("in test4()");
	}
	
}
