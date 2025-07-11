package com.LeetCode;

// Problem 1: Two Sum

import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Problem1 {
    public static int[] TwoSum(int[] nums, int target) {

        for(int i=0; i<nums.length-1;i++) {
            for(int j=i+1; j<nums.length; j++) {
                if(nums[i]+nums[j] == target){
                    return new int[] {i,j};
                }
            }
        }
        return new int[] {};
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Size of array : ");

        int size = sc.nextInt();
        int[] arr = new int[size+1];

        for(int z=0; z<arr.length; z++){
            System.out.println("Enter the elements of array: ");
            arr[z] = sc.nextInt();
        }
        System.out.println("Enter target number: ");
        int target = sc.nextInt();

        System.out.println(Arrays.toString(TwoSum(arr, target)));

    }
}
