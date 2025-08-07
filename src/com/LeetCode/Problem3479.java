package com.LeetCode;

// 3479. Problem : Fruits Into Baskets III

import java.util.*;
public class Problem3479 {
    /**
     * An array to represent the segment tree. Each node stores the max capacity
     * in its corresponding range of baskets.
     */
    private int[] tree;

    /**
     * A copy of the baskets array to reference capacities.
     */
    private int[] originalBaskets;

    /**
     * The total number of baskets (and fruits).
     */
    private int n;

    /**
     * Main method to calculate the number of unplaced fruits.
     *
     * @param fruits  An array of fruit quantities.
     * @param baskets An array of basket capacities.
     * @return The number of fruit types that could not be placed.
     */
    public int unplacedFruits(int[] fruits, int[] baskets) {
        this.n = baskets.length;
        if (this.n == 0) {
            return 0;
        }
        this.originalBaskets = baskets;
        // A segment tree typically requires up to 4n space.
        this.tree = new int[4 * n];

        // Build the segment tree from the initial basket capacities.
        build(1, 0, n - 1);

        int unplacedCount = 0;
        for (int fruit : fruits) {
            // Find the leftmost available basket that can hold the current fruit.
            int basketIndex = findLeftmostBasket(1, 0, n - 1, fruit);

            if (basketIndex == -1) {
                // No suitable basket was found.
                unplacedCount++;
            } else {
                // A basket was found, so "use" it by updating the tree.
                useBasket(1, 0, n - 1, basketIndex);
            }
        }
        return unplacedCount;
    }

    /**
     * Recursively builds the segment tree.
     *
     * @param node  The index of the current node in the `tree` array.
     * @param start The starting index of the baskets range for this node.
     * @param end   The ending index of the baskets range for this node.
     */
    private void build(int node, int start, int end) {
        if (start == end) {
            // Leaf node: stores the capacity of a single basket.
            tree[node] = originalBaskets[start];
        } else {
            int mid = start + (end - start) / 2;
            // Recurse for left and right children.
            build(2 * node, start, mid);
            build(2 * node + 1, mid + 1, end);
            // Internal node: stores the maximum capacity of its children.
            tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
        }
    }

    /**
     * Marks a basket as used by setting its capacity to -1 in the segment tree.
     *
     * @param node  The index of the current node.
     * @param start The start of the current range.
     * @param end   The end of the current range.
     * @param idx   The index of the basket to update.
     */
    private void useBasket(int node, int start, int end, int idx) {
        if (start == end) {
            // Found the leaf node for the basket, mark it as used.
            tree[node] = -1;
        } else {
            int mid = start + (end - start) / 2;
            if (idx <= mid) {
                // Update is in the left child.
                useBasket(2 * node, start, mid, idx);
            } else {
                // Update is in the right child.
                useBasket(2 * node + 1, mid + 1, end, idx);
            }
            // Recalculate the max capacity for the current node.
            tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
        }
    }

    /**
     * Queries the segment tree for the leftmost basket with capacity >= fruitVal.
     *
     * @param node     The index of the current node.
     * @param start    The start of the current range.
     * @param end      The end of the current range.
     * @param fruitVal The required minimum capacity.
     * @return The index of the found basket, or -1 if none exists.
     */
    private int findLeftmostBasket(int node, int start, int end, int fruitVal) {
        // If the max capacity in this entire range is insufficient, no solution here.
        if (tree[node] < fruitVal) {
            return -1;
        }

        // If we are at a leaf node, we have found a valid basket.
        if (start == end) {
            return start;
        }

        int mid = start + (end - start) / 2;
        int result = -1;

        // **Crucial Step**: Prioritize searching the left child to find the "leftmost" index.
        // Check if a potential solution exists in the left subtree.
        if (tree[2 * node] >= fruitVal) {
            result = findLeftmostBasket(2 * node, start, mid, fruitVal);
        }

        // If a valid basket was found in the left subtree, it's the leftmost possible.
        if (result != -1) {
            return result;
        }

        // Otherwise, if the left subtree had no solution, check the right subtree.
        return findLeftmostBasket(2 * node + 1, mid + 1, end, fruitVal);
    }

/**
 * The Main class handles user input and runs the Solution.
 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of test cases: ");
        int testCases = scanner.nextInt();

        for (int t = 1; t <= testCases; t++) {
            System.out.println("\n--- Test Case " + t + " ---");
            System.out.print("Enter the number of fruits/baskets (n): ");
            int n = scanner.nextInt();

            int[] fruits = new int[n];
            int[] baskets = new int[n];

            System.out.print("Enter the quantities for " + n + " fruits: ");
            for (int i = 0; i < n; i++) {
                fruits[i] = scanner.nextInt();
            }

            System.out.print("Enter the capacities for " + n + " baskets: ");
            for (int i = 0; i < n; i++) {
                baskets[i] = scanner.nextInt();
            }

            Problem3479 solution = new Problem3479();
            int result = solution.unplacedFruits(fruits, baskets);

            System.out.println("Result for Test Case " + t + ": " + result);
        }

        scanner.close();
    }
}
