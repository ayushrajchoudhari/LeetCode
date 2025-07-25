package com.LeetCode;

// 2322. Problem : Minimum Score After Removal of a Tree

import java.util.*;
public class Problem2322 {
    /**
     * Calculates the minimum possible score after removing two edges from a tree.
     * This is a static implementation.
     *
     * @param nums  An array of integer values for each node.
     * @param edges A 2D array representing the tree edges.
     * @return The minimum possible score.
     */
    public static int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Helper arrays
        int[] subtreeXor = new int[n];
        int[] startTime = new int[n];
        int[] endTime = new int[n];

        // Use a single-element array to pass 'time' by reference to the DFS.
        int[] time = {0};

        // Calculate total XOR sum
        int totalXor = 0;
        for (int num : nums) {
            totalXor ^= num;
        }

        // Step 1: Perform DFS for pre-computation.
        dfs(0, -1, adj, nums, subtreeXor, startTime, endTime, time);

        int minScore = Integer.MAX_VALUE;

        // Step 2: Iterate through all pairs of nodes (i, j) to find the minimum score.
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int xor1, xor2, xor3;

                if (isAncestor(i, j, startTime, endTime)) {
                    // Case 1: Node i is an ancestor of node j.
                    xor1 = subtreeXor[j];
                    xor2 = subtreeXor[i] ^ subtreeXor[j];
                    xor3 = totalXor ^ subtreeXor[i];
                } else if (isAncestor(j, i, startTime, endTime)) {
                    // Case 2: Node j is an ancestor of node i.
                    xor1 = subtreeXor[i];
                    xor2 = subtreeXor[j] ^ subtreeXor[i];
                    xor3 = totalXor ^ subtreeXor[j];
                } else {
                    // Case 3: Nodes i and j are in disjoint subtrees.
                    xor1 = subtreeXor[i];
                    xor2 = subtreeXor[j];
                    xor3 = totalXor ^ subtreeXor[i] ^ subtreeXor[j];
                }

                // Calculate and update the minimum score.
                int maxVal = Math.max(xor1, Math.max(xor2, xor3));
                int minVal = Math.min(xor1, Math.min(xor2, xor3));
                minScore = Math.min(minScore, maxVal - minVal);
            }
        }

        return minScore;
    }

    /**
     * Performs static DFS to calculate subtree XOR sums and Euler tour timings.
     */
    private static int dfs(int u, int p, List<List<Integer>> adj, int[] nums,
                           int[] subtreeXor, int[] startTime, int[] endTime, int[] time) {
        startTime[u] = time[0]++;
        int currentXor = nums[u];
        for (int v : adj.get(u)) {
            if (v != p) {
                currentXor ^= dfs(v, u, adj, nums, subtreeXor, startTime, endTime, time);
            }
        }
        subtreeXor[u] = currentXor;
        endTime[u] = time[0]++;
        return currentXor;
    }

    /**
     * Statically checks if node u is an ancestor of node v.
     */
    private static boolean isAncestor(int u, int v, int[] startTime, int[] endTime) {
        return startTime[u] < startTime[v] && endTime[u] > endTime[v];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of test cases: ");
        int n = sc.nextInt();

        for (int z=0; z<n; z++){
            System.out.println("Enter length of nums array: ");
            int[] nums = new int[sc.nextInt()];
            for(int x=0; x<nums.length; x++){
                System.out.println("Enter a value: ");
                nums[x] = sc.nextInt();
            }

            System.out.println("Enter number of edges: ");
            int[][] edges = new int[sc.nextInt()][2];
            for(int x=0; x<edges.length; x++){
                System.out.println("Enter the edge: ");
                edges[x][0] = sc.nextInt();
                edges[x][1] = sc.nextInt();
            }

            System.out.println(minimumScore(nums, edges));
        }
    }
}
