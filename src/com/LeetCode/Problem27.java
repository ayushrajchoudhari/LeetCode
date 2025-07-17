package com.LeetCode;

// 27. Problem : Remove Element

import java.util.*;
public class Problem27 {
    public static int removeElement(int[] nums, int val) {
        if(nums.length == 0) return 0;

        int k = 0;
        for(int num : nums){
            if(num != val){
                nums[k] = num;
                k++;
            }
        }

        return k;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of test cases: ");
        int n = sc.nextInt();

        for(int z=0; z<n; z++){
            System.out.println("Enter the value of val: ");
            int val = sc.nextInt();

            System.out.println("Enter the size of array: ");
            int[] arr = new int[sc.nextInt()];
            for(int x=0; x<arr.length; x++){
                System.out.println("Enter the element of array: ");
                arr[x] = sc.nextInt();
            }
            System.out.println(removeElement(arr, val));
        }
    }
}
