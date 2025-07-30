package com.LeetCode;

// 2411. Problem : Smallest Subarrays With Maximum Bitwise OR

import java.util.*;
public class Problem2411 {
    public static int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        // last[b] will store the most recent index `i` where bit `b` was set.
        // We check up to 30 bits as nums[i] <= 10^9 < 2^30.
        int[] last = new int[30];

        // Iterate from right to left.
        for (int i = n - 1; i >= 0; i--) {
            // 1. Update the last seen index for each bit set in nums[i].
            for (int b = 0; b < 30; b++) {
                if (((nums[i] >> b) & 1) == 1) {
                    last[b] = i;
                }
            }

            // 2. Find the farthest index we need to reach.
            // To get the maximal OR, the subarray must include the 'last'
            // seen position of every bit available in the suffix nums[i...n-1].
            int farthestIndex = i;
            for (int b = 0; b < 30; b++) {
                farthestIndex = Math.max(farthestIndex, last[b]);
            }

            // 3. The length of this smallest subarray is the distance to that farthest index.
            ans[i] = farthestIndex - i + 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of test cases: ");
        int n = sc.nextInt();

        for(int z=0; z<n; z++){
            System.out.println("Enter the size of array: ");
            int[] arr = new int[sc.nextInt()];
//            int[] arr = {1,0,2,1,3};

            for(int x=0; x<arr.length; x++){
                System.out.println("Enter element of array: ");
                arr[x] = sc.nextInt();
            }

            System.out.println(Arrays.toString(smallestSubarrays(arr)));
        }
    }
}
