package com.practice.stram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class JavaStreamPractice {

    public static void main(String[] args) {
        int[] nums = {4, 5, 5, 15, 3, 6};
        int target = 10;

        int[] result = twoSumAllFlat(nums, target);

        System.out.println("All indexes flattened: " + Arrays.toString(result));
    }
    
    public static int[] twoSum1(int[] nums, int target) {
      return IntStream.range(0, nums.length)
              .boxed()
              .flatMapToInt(i -> IntStream.range(i + 1, nums.length)
                      .filter(j -> nums[i] + nums[j] == target)
                      .flatMap(j -> IntStream.of(i, j))
              )
              .toArray();
  }

    public static int[] twoSum(int[] nums, int target) {    	
    	Map<Integer, Integer> map = new HashMap<>(); // value -> index

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
    
    public static int[] twoSumAllFlat(int[] nums, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>();  // value -> list of indices
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                for (int idx : map.get(complement)) {
                    result.add(idx);
                    result.add(i);
                }
            }

            // add current index to map
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
