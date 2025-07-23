package com.LeetCode;

// 1695. Problem : Maximum Erasure Value

import java.util.*;
public class Problem1695 {
    public static int maximumUniqueSubarray(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();

        int l = 0;
        int sum = 0;
        int maxSum = 0;

        for(int r=0; r<nums.length; r++){
            int curr = nums[r];

            while(set.contains(curr)){
                set.remove(nums[l]);
                sum -= nums[l];
                l++;
            }

            set.add(curr);
            sum += curr;
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of test cases: ");
        int n = sc.nextInt();

        for(int z=0; z<n; z++){
            System.out.println("Enter the length of array: ");
            int[] arr = new int[sc.nextInt()];

            for(int x=0; x<arr.length; x++){
                System.out.println("Enter the elemnt "+ (x+1) + " : ");
                arr[x] = sc.nextInt();
            }

            System.out.println(maximumUniqueSubarray(arr));
        }
    }
}
