package com.LeetCode;

// 2044. Problem : Count Number of Maximum Bitwise-OR Subsets

import java.util.*;
public class Problem2044 {
    private int count = 0;
    private int maxOR = 0;

    /**
     * Counts the number of non-empty subsets of nums that have the maximum possible bitwise OR.
     *
     * @param nums An array of integers.
     * @return The number of subsets with the maximum bitwise OR.
     */
    public int countMaxOrSubsets(int[] nums) {
        // 1. Calculate the maximum possible bitwise OR value by ORing all elements.
        for (int num : nums) {
            this.maxOR |= num;
        }

        // 2. Start the backtracking process from the first element (index 0)
        // with an initial OR value of 0.
        backtrack(nums, 0, 0);

        return this.count;
    }

    /**
     * A recursive helper function to explore all subsets.
     *
     * @param nums The input array.
     * @param index The current index in the array to consider.
     * @param currentOR The bitwise OR of the subset built so far.
     */
    private void backtrack(int[] nums, int index, int currentOR) {
        // Base Case: If we have considered all numbers.
        if (index == nums.length) {
            // If the OR of the generated subset equals the maximum possible OR,
            // we've found a valid subset.
            if (currentOR == this.maxOR) {
                this.count++;
            }
            return;
        }

        // --- Recursive Step ---

        // Choice 1: Exclude nums[index].
        // Move to the next element without changing the current OR.
        backtrack(nums, index + 1, currentOR);

        // Choice 2: Include nums[index].
        // Move to the next element and update the OR with the current number.
        backtrack(nums, index + 1, currentOR | nums[index]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of test cases: ");
        int testCases = scanner.nextInt();

        while (testCases-- > 0) {
            System.out.print("\nEnter the size of the array: ");
            int n = scanner.nextInt();
            int[] nums = new int[n];

            for (int i = 0; i < n; i++) {
                System.out.println("Enter the " + i + " elements of the array:");
                nums[i] = scanner.nextInt();
            }

            // Create a new Solution object for each test case to reset state
            Problem2044 solution = new Problem2044();
            int result = solution.countMaxOrSubsets(nums);

            System.out.println("Result: The number of subsets with maximum OR is " + result);
        }

        scanner.close();
    }
}
