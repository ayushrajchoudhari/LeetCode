package com.LeetCode;

// 3201. Problem : Find the Maximum Length of Valid Subsequence II

import java.util.Arrays;
import java.util.Scanner;

public class Problem3202 {
    public static int maximumLength(int[] nums, int k) {
        // Set<Integer> unique = new HashSet<>();
        // for(int val : nums){
        //     unique.add(val%k);
        // }
        // List<Integer> arr = new ArrayList<>(unique);
        // Collections.sort(arr);
        // int maxLength = 0;

        // for(int i=0; i<arr.size(); i++){
        //     for(int j=0; j<arr.size(); j++){
        //         int expected = arr.get(i);
        //         int count = 0;

        //         for(int val : nums){
        //             if(val%k == expected){
        //                 count++;
        //                 expected = (expected == arr.get(i)) ? arr.get(j) : arr.get(i);
        //             }
        //         }

        //         maxLength = Math.max(maxLength, count);
        //     }

        // }

        // return maxLength;

        int n = nums.length;
        int[][] dp = new int[n][k];
        for(int[] row : dp){
            Arrays.fill(row, 1);
        }

        int maxLength = 0;
        for(int i=1; i<n; i++){
            for(int j=i-1; j>=0; j--){
                int rem = (nums[i] + nums[j]) % k;
                dp[i][rem] = Math.max(dp[i][rem], dp[j][rem]+1);
                maxLength = Math.max(dp[i][rem], maxLength);
            }
        }

        return maxLength;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of test cases: ");
        int n = sc.nextInt();

        for(int z=0; z<n; z++){
            System.out.println("Enter the value of k: ");
            int k = sc.nextInt();

            System.out.println("Enter the size of array: ");
            int[] arr = new int[sc.nextInt()];
            for(int x=0; x<arr.length; x++){
                System.out.println("Enter the element of array: ");
                arr[x] = sc.nextInt();
            }
            System.out.println(maximumLength(arr, k));
        }
    }
}
