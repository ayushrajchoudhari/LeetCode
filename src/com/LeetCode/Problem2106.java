package com.LeetCode;

// 2106. Problem : Maximum Fruits Harvested After At Most K Steps

import java.util.*;
public class Problem2106 {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        if (n == 0) {
            return 0;
        }

        int left = 0;
        int maxFruits = 0;
        int currentFruits = 0;

        // The 'right' pointer iterates through all possible endpoints of our harvest range.
        for (int right = 0; right < n; right++) {
            // Add fruits from the current 'right' position to our window sum.
            currentFruits += fruits[right][1];

            // This loop shrinks the window from the left if it's no longer reachable within k steps.
            // We need to check if the distance to cover the range [left, right] is > k.
            while (left <= right && getSteps(fruits, startPos, left, right) > k) {
                // If the window is too large, remove the leftmost fruit and slide the window.
                currentFruits -= fruits[left][1];
                left++;
            }

            // After ensuring the window [left, right] is valid, update the maximum fruits found.
            maxFruits = Math.max(maxFruits, currentFruits);
        }

        return maxFruits;
    }

    /**
     * Helper function to calculate the minimum steps to harvest fruits from
     * position fruits[left][0] to fruits[right][0] starting from startPos.
     */
    private int getSteps(int[][] fruits, int startPos, int left, int right) {
        int posLeft = fruits[left][0];
        int posRight = fruits[right][0];

        // Cost to travel from start to posLeft and then cover the whole range to posRight
        int cost1 = (startPos - posLeft) + (posRight - posLeft);

        // Cost to travel from start to posRight and then cover the whole range to posLeft
        int cost2 = (posRight - startPos) + (posRight - posLeft);

        // This handles cases where startPos is outside the [posLeft, posRight] range.
        if (startPos < posLeft) {
            return posRight - startPos;
        }
        if (startPos > posRight) {
            return startPos - posLeft;
        }

        // If startPos is within the [posLeft, posRight] range, we must go both
        // left and right. The cost is to traverse the whole range plus the
        // shorter of the two initial trips (from start to an endpoint).
        return Math.min(cost1, cost2);
    }



/**
 * Main class to handle user input and run the solution.
 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Problem2106 solution = new Problem2106();

        System.out.print("Enter the number of test cases: ");
        int testCases = scanner.nextInt();

        while (testCases-- > 0) {
            // Input for the fruits array
            System.out.print("Enter the number of fruit trees (n): ");
            int n = scanner.nextInt();
            int[][] fruits = new int[n][2];
            System.out.println("Enter the position and amount for each of the " + n + " fruit trees (e.g., 'pos amt'):");
            for (int i = 0; i < n; i++) {
                fruits[i][0] = scanner.nextInt(); // position
                fruits[i][1] = scanner.nextInt(); // amount
            }

            // Input for startPos and k
            System.out.print("Enter the starting position (startPos): ");
            int startPos = scanner.nextInt();
            System.out.print("Enter the maximum steps allowed (k): ");
            int k = scanner.nextInt();

            // Calculate and print the result
            int result = solution.maxTotalFruits(fruits, startPos, k);
            System.out.println("\nMaximum total fruits that can be harvested: " + result);
        }

        scanner.close();
    }
}
