package com.LeetCode;

// 2210. Problem : Count Hills and Valleys in an Array

import java.util.*;
public class Problem2210 {
    public static int countHillValley(int[] nums) {
        // Step 1: Filter the array to remove consecutive duplicates.
        // This simplifies the problem to finding peaks and troughs in an array
        // where no two adjacent elements are the same.
        List<Integer> uniqueNums = new ArrayList<>();
        if (nums.length == 0) {
            return 0;
        }

        uniqueNums.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                uniqueNums.add(nums[i]);
            }
        }

        // We need at least 3 unique numbers to form a hill or a valley.
        if (uniqueNums.size() < 3) {
            return 0;
        }

        int count = 0;
        // Step 2: Iterate through the filtered list to find hills and valleys.
        // We look at each element (except the first and last) and check its neighbors.
        for (int i = 1; i < uniqueNums.size() - 1; i++) {
            int left = uniqueNums.get(i - 1);
            int current = uniqueNums.get(i);
            int right = uniqueNums.get(i + 1);

            // Check for a hill: current is greater than both neighbors.
            boolean isHill = current > left && current > right;
            // Check for a valley: current is smaller than both neighbors.
            boolean isValley = current < left && current < right;

            if (isHill || isValley) {
                count++;
            }
        }

        return count;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of test cases: ");
        int n = sc.nextInt();

        for(int z=0; z<n; z++){
            System.out.println("Enter size of array: ");
            int[] arr = new int[sc.nextInt()];

            for(int x=0; x<arr.length; x++){
                System.out.println("Enter array element: ");
                arr[x] = sc.nextInt();
            }

            System.out.println(countHillValley(arr));
        }
    }
}
