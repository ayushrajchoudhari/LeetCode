package com.LeetCode;

// 904. Problem : Fruit Into Baskets

import java.util.*;
public class Problem904 {
    /**
     * Calculates the maximum number of fruits that can be collected.
     *
     * @param fruits An array of integers representing the type of fruit on each tree.
     * @return The maximum number of fruits you can collect from a contiguous subarray.
     */
    public int totalFruit(int[] fruits) {
        // Edge case: if there are no trees, you can't pick any fruit.
        if (fruits == null || fruits.length == 0) {
            return 0;
        }

        int maxFruits = 0;
        int windowStart = 0;
        // This map will store the frequency of each fruit type in the current window.
        Map<Integer, Integer> fruitFrequencyMap = new HashMap<>();

        // Iterate through the trees, expanding the window from the right.
        for (int windowEnd = 0; windowEnd < fruits.length; windowEnd++) {
            // Add the current fruit to our window.
            int rightFruit = fruits[windowEnd];
            fruitFrequencyMap.put(rightFruit, fruitFrequencyMap.getOrDefault(rightFruit, 0) + 1);

            // The window is invalid if we have more than 2 types of fruit.
            // Shrink the window from the left until it's valid again.
            while (fruitFrequencyMap.size() > 2) {
                int leftFruit = fruits[windowStart];

                // Decrement the count of the fruit that is leaving the window.
                fruitFrequencyMap.put(leftFruit, fruitFrequencyMap.get(leftFruit) - 1);

                // If the fruit count becomes 0, remove it from the map completely.
                if (fruitFrequencyMap.get(leftFruit) == 0) {
                    fruitFrequencyMap.remove(leftFruit);
                }

                // Move the window's start forward.
                windowStart++;
            }

            // The current window is valid (has <= 2 fruit types).
            // Update our maximum if the current window is the largest we've seen.
            int currentWindowSize = windowEnd - windowStart + 1;
            maxFruits = Math.max(maxFruits, currentWindowSize);
        }

        return maxFruits;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Problem904 solution = new Problem904();

        System.out.print("Enter the number of test cases: ");
        int testCases = scanner.nextInt();

        while (testCases-- > 0) {
            // 1. Get the size of the fruit array
            System.out.print("Enter the number of fruits in the array: ");
            int n = scanner.nextInt();

            // 2. Create and populate the array
            int[] fruits = new int[n];
            System.out.println("Enter the " + n + " fruit types (space-separated):");
            for (int i = 0; i < n; i++) {
                fruits[i] = scanner.nextInt();
            }

            // 3. Call the solution method and get the result
            int result = solution.totalFruit(fruits);

            // 4. Print the result for the current test case
            System.out.println("Result -> Maximum number of fruits: " + result);
        }

        scanner.close();
    }
}
