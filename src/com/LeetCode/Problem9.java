package com.LeetCode;

// Problem 9 : Palindrome Number

import java.util.*;
public class Problem9 {
    public static boolean isPalindrome(int n) {
        int rN = 0, target = n;

        while(n > 0){
            int temp = n % 10;  //extracts one digit at a time from n
            n = n / 10;     //removes one digit from n
            rN = rN * 10 + temp;  //creates new number
            // rN = rN * 10 + n % 10;
        }

        if(target != rN){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("No. of test cases: ");
        int x = sc.nextInt();
        for(int z=0; z<x; z++){
            System.out.println("Enter a number: ");
            System.out.println(isPalindrome(sc.nextInt()));
        }
    }
}
