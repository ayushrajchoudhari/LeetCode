package com.LeetCode;

// 2419. Problem : Longest Subarray With Maximum Bitwise AND

import java.util.*;
public class Problem2419 {
    /**
     * Finds the length of the longest subarray with the maximum possible bitwise AND.
     * <p>
     * The core logic is that for a subarray's AND to be maximized, it must equal the
     * maximum element in the entire array. This requires all elements in the subarray
     * to be that maximum value.
     *
     * @param nums The input integer array.
     * @return The length of the longest contiguous subarray of the maximum element.
     */
    public int longestSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Initialize tracking variables
        int maxVal = 0;       // Stores the maximum element found so far
        int maxLength = 0;    // Stores the length of the longest subarray of maxVal
        int currentLength = 0;// Stores the length of the current subarray of maxVal

        // A single pass to find the max element and the longest streak simultaneously
        for (int num : nums) {
            if (num > maxVal) {
                // Found a new maximum value.
                maxVal = num;
                maxLength = 1;
                currentLength = 1;
            } else if (num == maxVal) {
                // Continuing a streak of the current maximum value.
                currentLength++;
                maxLength = Math.max(maxLength, currentLength);
            } else { // num < maxVal
                // The streak of the maximum value is broken.
                currentLength = 0;
            }
        }

        return maxLength;
    }

    /**
     * The main method to drive and test the solution.
     * It takes user input for multiple test cases.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Problem2419 solver = new Problem2419();

        System.out.print("Enter the number of test cases: ");
        int testCases = scanner.nextInt();

        while (testCases-- > 0) {
            System.out.print("Enter the size of the array: ");
            int n = scanner.nextInt();

            int[] nums = new int[n];
            System.out.println("Enter the " + n + " elements of the array:");
            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
            }

            // Calculate the result by calling the method
            int result = solver.longestSubarray(nums);

            // Print the final result for the current test case
            System.out.println("Result: The length of the longest subarray is " + result);
        }

        scanner.close();
    }
}
