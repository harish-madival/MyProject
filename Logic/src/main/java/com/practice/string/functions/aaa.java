package com.practice.string.functions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class aaa {

	public static void main(String[] args) {
		System.err.println("This is an error message.");
		System.out.println(isHelloWord("Hello Word"));
		int[] testArray = { 3, 9, 50, 15, 99, 7, 98, 65 };
		System.err.println(distClosestNumbers(testArray));
	}

	private static int distClosestNumbers(int[] testArray) {
		int temp = 0;
		List<Integer> sortedList = Arrays.stream(testArray).boxed().sorted(Collections.reverseOrder())
				.collect(Collectors.toList());
		int closedist = 0;
		int temp1 = Integer.MAX_VALUE;
		Integer integer = sortedList.stream().map(aa -> aaaaaaa(temp1, aa, closedist)).findAny().get();
		return integer;
//		 int minDiff = Integer.MAX_VALUE;
//
//	        for (int i = 1; i < sortedList.size(); i++) {
//	            int diff = sortedList.get(i) - sortedList.get(i - 1);
//	            if (diff < minDiff) {
//	                minDiff = diff;
//	            }
//	        }
//		return minDiff;
//		return IntStream.range(1, sortedList.size())
//                .map(i -> sortedList.get(i) - sortedList.get(i - 1))
//                .min()
//                .orElse(Integer.MAX_VALUE);
	}

	private static int aaaaaaa(int temp1, Integer aa, int closedist) {
		int closedist2 = temp1 - aa;
		if (closedist > closedist2) {
			closedist = closedist2;
		}
		temp1 = aa;
		return closedist;
	}

	private static boolean isHelloWord(String str) {
		return Optional.ofNullable(str).filter(string -> string.equals("Hello Word")).isPresent();
	}
}
