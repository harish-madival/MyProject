package com.practice.inheritance;

public class Test3 extends Test2 {
	

	private Test3 (int abc, int dfd, String dd, String adf) {
		super(abc, dfd, dd, adf);
	}
	public static void main(String[] args) {
		Test3 test3= new Test3(1, 1, "", "");
		test3.test2();
	}
}
