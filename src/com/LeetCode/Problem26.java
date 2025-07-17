package com.LeetCode;

// 26. Problem : Remove Duplicates from sorted Array

import java.util.*;
public class Problem26 {
    public static int removeDuplicates(int[] nums) {
        if(nums.length < 0) return 0;

        int n = nums.length;
        int pt1 = 1;

        for(int i=1; i<n; i++){
            if(nums[i] != nums[pt1-1]){
                nums[pt1] = nums[i];
                pt1++;
            }

        }

        return pt1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of test cases: ");
        int n = sc.nextInt();

        for(int z=0; z<n; z++){
            System.out.println("Enter the size of array: ");
            int[] arr = new int[sc.nextInt()];
            for(int x=0; x<arr.length; x++){
                System.out.println("Enter the element of array: ");
                arr[x] = sc.nextInt();
            }
            System.out.println(removeDuplicates(arr));
        }
    }
}
