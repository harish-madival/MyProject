package com.practice.constructor;

public class TestConstructor {
	
	int a;
	int b;
	static int fg;
	public TestConstructor() {
		super();
		System.out.println("default");
	}

	public TestConstructor(int a, int b) {
		this();
		this.a = a;
		this.b = b;
		System.out.println("parametr");
	}

	public static void main(String[] args) {
		new TestConstructor(fg, fg);
	}
}
