package com.LeetCode;

// 898. Problem : Bitwise ORs of Subarrays

import java.util.*;
public class Problem898 {
    /**
     * Calculates the number of unique values of bitwise ORs of all contiguous subarrays.
     * @param arr The input array of integers.
     * @return The number of unique bitwise ORs.
     */
    public int subarrayBitwiseORs(int[] arr) {
        // This set stores all unique bitwise OR values found globally.
        Set<Integer> results = new HashSet<>();

        // This set stores the bitwise ORs of all subarrays ending at the previous element.
        Set<Integer> currentOrs = new HashSet<>();

        // Iterate through each element of the array.
        for (int x : arr) {
            // This set will store the bitwise ORs of all subarrays ending at the current element `x`.
            Set<Integer> nextOrs = new HashSet<>();

            // A subarray with just `x` has an OR value of `x`.
            nextOrs.add(x);

            // Extend all previous subarrays (whose OR values are in `currentOrs`) with `x`.
            for (int y : currentOrs) {
                nextOrs.add(y | x);
            }

            // Add all newly found OR values to our final results set.
            results.addAll(nextOrs);

            // The `nextOrs` set becomes the `currentOrs` for the next iteration.
            currentOrs = nextOrs;
        }

        return results.size();
    }

    /**
     * The main method to run the solution with user-provided test cases.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Problem898 solution = new Problem898();

        System.out.print("Enter the number of test cases: ");
        int testCases = scanner.nextInt();

        for (int t = 1; t <= testCases; t++) {
            System.out.println("\n--- Test Case " + t + " ---");
            System.out.print("Enter the size of the array: ");
            int n = scanner.nextInt();

            int[] arr = new int[n];
            System.out.println("Enter the " + n + " elements of the array:");
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            int result = solution.subarrayBitwiseORs(arr);
            System.out.println("Result: The number of unique bitwise ORs is " + result);
        }

        scanner.close();
    }
}
