package com.LeetCode;

// 3201. Problem : Find the Maximum Length of Valid Subsequence I

import java.util.*;
public class Problem3201 {
    public static int evenSeq(int[] arr){
        int score = 0;
        for(int i=0; i<arr.length; i++){
            if(arr[i]%2 == 0) score++;
        }
        return score;
    }

    public static int oddSeq(int[] arr){
        int score = 0;
        for(int i=0; i<arr.length; i++){
            if(arr[i]%2 != 0) score++;
        }
        return score;
    }

    public static int oddEvenSeq(int[] arr){
        int score = 1;
        int pt1 = 0;
        int pt2 = 1;
        int f1 = 1;
        int f2 = 0;


        while(pt2<arr.length){
            if(arr[pt1] % 2 == f1){
                if(arr[pt2] % 2 == f2){
                    score++;
                    pt1 = pt2;
                    pt2++;

                    int temp = f1;
                    f1 = f2;
                    f2 = temp;
                }
                else pt2++;
            }
            else {
                pt1++;
                pt2++;
            }
        }

        return score;
    }

    public static int evenOddSeq(int[] arr){
        int score = 1;
        int pt1 = 0;
        int pt2 = 1;
        int f1 = 0;
        int f2 = 1;

        while(pt2<arr.length){
            if(arr[pt1]%2 == f1){
                if(arr[pt2]%2 == f2){
                    score++;
                    pt1 = pt2;
                    pt2++;

                    int temp = f1;
                    f1 = f2;
                    f2 = temp;
                }
                else pt2++;
            }
            else {
                pt1++;
                pt2++;
            }
        }

        return score;
    }

    public static int maximumLength(int[] nums) {
        int score = 0;
        score = Math.max(Math.max(evenSeq(nums), oddSeq(nums)) , Math.max(oddEvenSeq(nums), evenOddSeq(nums)));
        return score;

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
            System.out.println(maximumLength(arr));
        }
    }
}
