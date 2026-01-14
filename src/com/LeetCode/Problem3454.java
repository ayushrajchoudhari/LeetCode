package com.LeetCode;

import java.util.*;

// 3454. Problem : Separate Squares II

public class Problem3454 {
    public static void main(String[] args) {
        Solution3454 sol = new Solution3454();

        // Example Test Case: Two squares
        // Square 1: x=0, y=0, side=2
        // Square 2: x=1, y=1, side=2
        int[][] squares = {
                {0, 0, 2},
                {1, 1, 2}
        };

        double result = sol.separateSquares(squares);
        System.out.printf("The y-coordinate that splits the area is: %.5f%n", result);
    }
}

class Solution3454 {
    // Event represents a horizontal edge of a square
    class Event implements Comparable<Event> {
        int y, x1, x2, type;
        Event(int y, int x1, int x2, int type) {
            this.y = y;
            this.x1 = x1;
            this.x2 = x2;
            this.type = type;
        }
        public int compareTo(Event other) {
            if (this.y != other.y) return Integer.compare(this.y, other.y);
            return Integer.compare(other.type, this.type); // Process additions before subtractions
        }
    }

    // Segment Tree Node to track union length of active x-intervals
    class Node {
        int count;   // Number of squares covering this segment
        long length; // Total union length covered within this segment
    }

    Node[] tree;
    int[] coords;

    public double separateSquares(int[][] squares) {
        List<Event> events = new ArrayList<>();
        TreeSet<Integer> xCoords = new TreeSet<>();

        for (int[] s : squares) {
            int x = s[0], y = s[1], l = s[2];
            events.add(new Event(y, x, x + l, 1));      // Bottom edge (add)
            events.add(new Event(y + l, x, x + l, -1)); // Top edge (remove)
            xCoords.add(x);
            xCoords.add(x + l);
        }
        Collections.sort(events);

        // Discretize X-coordinates for the segment tree
        coords = new int[xCoords.size()];
        int idx = 0;
        for (int x : xCoords) coords[idx++] = x;

        int m = coords.length;
        tree = new Node[4 * m];
        for (int i = 0; i < 4 * m; i++) tree[i] = new Node();

        // Pass 1: Find total union area
        double totalArea = runScanline(events, -1).totalArea;

        // Pass 2: Find the y-level where area reaches totalArea / 2
        return runScanline(events, totalArea / 2.0).yResult;
    }

    private Result runScanline(List<Event> events, double targetHalf) {
        double currentArea = 0;
        int m = coords.length;

        // Re-initialize tree
        for (int i = 0; i < 4 * m; i++) {
            tree[i].count = 0;
            tree[i].length = 0;
        }

        for (int i = 0; i < events.size(); i++) {
            Event e = events.get(i);
            if (i > 0) {
                long activeWidth = tree[1].length;
                double areaDelta = (double) activeWidth * (e.y - events.get(i - 1).y);

                // If we are looking for the midpoint and this step crosses it
                if (targetHalf >= 0 && currentArea + areaDelta >= targetHalf - 1e-9) {
                    double neededArea = targetHalf - currentArea;
                    return new Result(events.get(i - 1).y + (neededArea / activeWidth), 0);
                }
                currentArea += areaDelta;
            }
            update(1, 0, m - 2, e.x1, e.x2, e.type);
        }
        return new Result(0, currentArea);
    }

    private void update(int node, int start, int end, int l, int r, int val) {
        if (coords[start] >= r || coords[end + 1] <= l) return;

        if (coords[start] >= l && coords[end + 1] <= r) {
            tree[node].count += val;
        } else {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, l, r, val);
            update(node * 2 + 1, mid + 1, end, l, r, val);
        }

        if (tree[node].count > 0) {
            tree[node].length = coords[end + 1] - coords[start];
        } else if (start != end) {
            tree[node].length = tree[node * 2].length + tree[node * 2 + 1].length;
        } else {
            tree[node].length = 0;
        }
    }

    class Result {
        double yResult;
        double totalArea;
        Result(double y, double area) { this.yResult = y; this.totalArea = area; }
    }
}