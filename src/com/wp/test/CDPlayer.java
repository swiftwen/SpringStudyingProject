package com.wp.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class CDPlayer {

	@Autowired
	private Disc disc;
	
	
	public Disc getDisc() {
		return disc;
	}
	
	public void setDisc(Disc disc) {
		this.disc = disc;
	}
	
	
}
