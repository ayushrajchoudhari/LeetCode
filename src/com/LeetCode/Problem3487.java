package com.LeetCode;

// 3487. Problem : Maximum Unique Subarray Sum After Deletion

import java.util.*;
public class Problem3487 {
    public static int maxSum(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }

        int sum = 0;
        int flag = 0;

        ArrayList<Integer> arr = new ArrayList<Integer>();

        for(int val : nums){
            if(val >= 0 && !arr.contains(val)){
                arr.add(val);
                flag = 1;
            }
        }

        if(flag == 0){
            for(int val : nums){
                if(val < 0) arr.add(val);
            }

            Collections.sort(arr);
        }

        if(flag == 0){
            return arr.get(arr.size()-1);
        }

        for(int val : arr){
            if(val >= 0) sum += val;
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of test cases: ");
        int n = sc.nextInt();

        for(int z=0; z<n; z++){
            System.out.println("Enter size of array: ");
            int[] arr = new int[sc.nextInt()];

            for(int x=0; x<arr.length; x++){
                System.out.println("Enter element of array ");
                arr[x] = sc.nextInt();
            }

            System.out.println(maxSum(arr));
        }
    }
}
