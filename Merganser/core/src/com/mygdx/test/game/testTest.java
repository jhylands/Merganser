package com.mygdx.test.game;


import junit.framework.*;

public class testTest extends TestCase {
	 protected int value1, value2;
	 
	 protected void setUp(){
	      value1=3;
	      value2=3;
	   }

	 
	 public void testAdd(){
	      double result= value1 + value2;
	      assertTrue(result == 6);
	   }

}
