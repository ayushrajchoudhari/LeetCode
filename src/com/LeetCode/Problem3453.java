package com.LeetCode;

// 3453. Problem : Separate Squares I

public class Problem3453 {
    public static void main(String[] args) {
        Solution3453 sol = new Solution3453();

        // Example 1: Squares that don't overlap horizontally
        int[][] squares1 = {{0, 0, 1}, {2, 2, 1}};
        System.out.printf("Example 1 Output: %.5f\n", sol.separateSquares(squares1));

        // Example 2: Multiple squares
        int[][] squares2 = {{0, 0, 2}, {1, 1, 1}};
        System.out.printf("Example 2 Output: %.5f\n", sol.separateSquares(squares2));
    }
}

class Solution3453 {
    public double separateSquares(int[][] squares) {
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        double totalArea = 0;

        for (int[] sq : squares) {
            double y = sq[1];
            double l = sq[2];
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y + l);
            totalArea += l * l;
        }

        double low = minY;
        double high = maxY;

        // 100 iterations provide precision far beyond the 10^-5 requirement
        for (int i = 0; i < 100; i++) {
            double mid = low + (high - low) / 2.0;
            if (getAreaBelow(squares, mid) < totalArea / 2.0) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private double getAreaBelow(int[][] squares, double lineY) {
        double area = 0;
        for (int[] sq : squares) {
            double y = sq[1];
            double l = sq[2];
            double top = y + l;

            if (lineY <= y) {
                continue;
            } else if (lineY >= top) {
                area += l * l;
            } else {
                area += l * (lineY - y);
            }
        }
        return area;
    }
}
