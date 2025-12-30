package com.practice.inheritance;

public class Test2 extends Test {

	String adf;
	
	
	public static void main(String[] args) {
		test();
	}
	
	protected Test2(int abc, int dfd, String dd, String adf) {
		super(abc, dfd, dd);
		this.adf = adf;
	}


	private static void test() {
		System.out.println("test");
	}
	
	public void test2() {
		System.out.println("test");
	}
}
