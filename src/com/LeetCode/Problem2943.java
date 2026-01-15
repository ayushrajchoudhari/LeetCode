package com.LeetCode;

import java.util.Arrays;

// 2943. Problem : Maximize Area of Square Hole in Grid

public class Problem2943 {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        // Find the longest consecutive sequence in both arrays
        int maxH = getMaxConsecutive(hBars);
        int maxV = getMaxConsecutive(vBars);

        // The side of the square is limited by the smaller gap
        int side = Math.min(maxH + 1, maxV + 1);

        return side * side;
    }

    private int getMaxConsecutive(int[] bars) {
        Arrays.sort(bars);
        int maxLen = 1;
        int currentLen = 1;

        for (int i = 1; i < bars.length; i++) {
            if (bars[i] == bars[i - 1] + 1) {
                currentLen++;
            } else {
                currentLen = 1;
            }
            maxLen = Math.max(maxLen, currentLen);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Problem2943 sol = new Problem2943();

        // Example Case:
        // n = 2, m = 1
        // hBars = [2, 3], vBars = [2]
        // Horizontal: removing bars 2 and 3 creates a gap of 3.
        // Vertical: removing bar 2 creates a gap of 2.
        // Min(3, 2) = 2. Square area = 2 * 2 = 4.

        int n1 = 2, m1 = 1;
        int[] hBars1 = {2, 3};
        int[] vBars1 = {2};

        System.out.println("Test Case 1 Output: " + sol.maximizeSquareHoleArea(n1, m1, hBars1, vBars1));
        // Expected: 4

        // Additional Case:
        int n2 = 1, m2 = 1;
        int[] hBars2 = {2};
        int[] vBars2 = {2};

        System.out.println("Test Case 2 Output: " + sol.maximizeSquareHoleArea(n2, m2, hBars2, vBars2));
        // Expected: 4 (Both have a gap of 2, so 2*2=4)
    }
}