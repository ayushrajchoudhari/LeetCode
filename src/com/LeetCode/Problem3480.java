package com.LeetCode;

// 3480. Problem : Maximize Subarrays After Removing One Conflicting Pair

import java.util.*;
public class Problem3480 {

    /**
     * Helper class for Fenwick Tree (Binary Indexed Tree).
     * This data structure is used for the efficient O(N log N) solution.
     */
    static class FenwickTree {
        private final long[] tree;
        private final int size;

        public FenwickTree(int n) {
            // Values can go up to n, so size n+1 is needed for 0-based indexing.
            // We use another +1 for convenience in the 1-based BIT implementation.
            this.size = n + 2;
            this.tree = new long[this.size];
        }

        public void update(int index, long delta) {
            index++; // Adjust to 1-based index
            while (index < size) {
                tree[index] += delta;
                index += index & (-index);
            }
        }

        public long query(int index) {
            if (index < 0) return 0;
            index++; // Adjust to 1-based index
            long sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & (-index);
            }
            return sum;
        }
    }

    /**
     * Calculates the maximum number of valid subarrays after removing one conflicting pair.
     *
     * @param n The size of the array nums (from 1 to n).
     * @param conflictingPairs A 2D array of conflicting pairs.
     * @return The maximum number of valid subarrays.
     */
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        // --- Step 1: Preprocessing ---
        Map<Integer, List<Integer>> pairsByMinIdx = new HashMap<>();
        for (int[] pair : conflictingPairs) {
            int u = pair[0] - 1;
            int v = pair[1] - 1;
            int minIdx = Math.min(u, v);
            int maxIdx = Math.max(u, v);
            pairsByMinIdx.computeIfAbsent(minIdx, k -> new ArrayList<>()).add(maxIdx);
        }

        for (List<Integer> maxes : pairsByMinIdx.values()) {
            Collections.sort(maxes);
        }

        int[] max_reach_all = new int[n];
        int[] max_reach_second = new int[n];
        Arrays.fill(max_reach_all, n);
        Arrays.fill(max_reach_second, n);

        for (int i = 0; i < n; i++) {
            if (pairsByMinIdx.containsKey(i)) {
                List<Integer> maxes = pairsByMinIdx.get(i);
                max_reach_all[i] = maxes.get(0);
                if (maxes.size() > 1) {
                    max_reach_second[i] = maxes.get(1);
                }
            }
        }

        int[] limit_all = new int[n + 1];
        limit_all[n] = n;
        for (int i = n - 1; i >= 0; i--) {
            limit_all[i] = Math.min(limit_all[i + 1], max_reach_all[i]);
        }

        long base_count = 0;
        for (int i = 0; i < n; i++) {
            base_count += (limit_all[i] - i);
        }

        long max_increase = 0;

        // --- Step 2 & 3: Find Max Increase using Monotonic Stack and Fenwick Trees ---
        List<int[]> stack = new ArrayList<>(); // Stores (index, value)
        FenwickTree bit_len = new FenwickTree(n);
        FenwickTree bit_prod = new FenwickTree(n);

        for (int k = 0; k < n; k++) {
            if (k > 0) {
                int R_k_minus_1 = max_reach_all[k - 1];
                long prev_j = stack.isEmpty() ? -1 : stack.get(stack.size() - 1)[0];

                while (!stack.isEmpty() && stack.get(stack.size() - 1)[1] >= R_k_minus_1) {
                    int[] top = stack.remove(stack.size() - 1);
                    int j_p = top[0];
                    int v_p = top[1];
                    prev_j = stack.isEmpty() ? -1 : stack.get(stack.size() - 1)[0];
                    long l_p = j_p - prev_j;

                    bit_len.update(v_p, -l_p);
                    bit_prod.update(v_p, -l_p * v_p);
                }

                long l_new = (k - 1) - prev_j;
                bit_len.update(R_k_minus_1, l_new);
                bit_prod.update(R_k_minus_1, l_new * R_k_minus_1);
                stack.add(new int[]{k - 1, R_k_minus_1});
            }

            if (max_reach_all[k] == n) continue;

            long c_all = limit_all[k];
            long c_new = Math.min(max_reach_second[k], limit_all[k + 1]);

            if (c_new <= c_all) continue;

            long t_c_new = calculateT(c_new, n, bit_len, bit_prod);
            long t_c_all = calculateT(c_all, n, bit_len, bit_prod);

            long sum_k = t_c_new - t_c_all;
            long current_increase = (c_new - c_all) + sum_k;
            max_increase = Math.max(max_increase, current_increase);
        }

        return base_count + max_increase;
    }

    /**
     * Helper to calculate the sum of min(RMQ(i, k-1), C) for i from 0 to k-1.
     * This is the core of the O(N log N) calculation.
     */
    private long calculateT(long C, int n, FenwickTree bit_len, FenwickTree bit_prod) {
        long total_len = bit_len.query(n);
        long len_lt_C = bit_len.query((int) C - 1);
        long prod_lt_C = bit_prod.query((int) C - 1);
        long len_ge_C = total_len - len_lt_C;
        return C * len_ge_C + prod_lt_C;
    }

    /**
     * The main method to run the program and handle test cases.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of test cases:");
        int t = 0;
        try {
            t = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
            return;
        }

        while (t-- > 0) {
            try {
                System.out.println("\n--- New Test Case ---");
                System.out.print("Enter n: ");
                int n = scanner.nextInt();

                System.out.print("Enter the number of conflicting pairs: ");
                int m = scanner.nextInt();
                int[][] conflictingPairs = new int[m][2];

                System.out.println("Enter " + m + " conflicting pairs, one pair per line (e.g., 'a b'):");
                for (int i = 0; i < m; i++) {
                    conflictingPairs[i][0] = scanner.nextInt();
                    conflictingPairs[i][1] = scanner.nextInt();
                }

                Problem3480 sol = new Problem3480();
                long result = sol.maxSubarrays(n, conflictingPairs);
                System.out.println("\nMaximum number of valid subarrays: " + result);

            } catch (InputMismatchException e) {
                System.out.println("Invalid input format. Please try again for this test case.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        scanner.close();
    }
}
