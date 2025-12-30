package com.practice.scanner;

import java.util.Scanner;

public class TestScanner {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("pls enter integer");
		int ab = sc.nextInt();
		System.out.println("Entered value is: "+ab);
		System.out.println("pls enter string");
		String abString = sc.next();
		System.out.println("Entered value is: "+abString);
	}
}
