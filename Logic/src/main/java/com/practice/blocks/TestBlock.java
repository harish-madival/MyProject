package com.practice.blocks;

public class TestBlock {
	
	static {
		System.out.println("static block");
	}
	
	{
		System.out.println("non static block");
	}

	public static void main(String [] args) {
		System.out.println("Main method");
		{
			System.out.println("local block");
		}
		TestBlock tbb = new TestBlock();
	}
}
