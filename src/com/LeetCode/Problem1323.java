package com.LeetCode;

// 1323. Problem : Maximum 69 Number

import java.util.*;
public class Problem1323 {
    public int maximum69Number (int num) {
        String str = String.valueOf(num);

        String maxStr = str.replaceFirst("6", "9");

        return Integer.parseInt(maxStr);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of test cases: ");
        int n = sc.nextInt();

        Problem1323 solution = new Problem1323();
        for(int z=0; z<n; z++){
            System.out.println("Enter a number: ");
            System.out.println(solution.maximum69Number(sc.nextInt()));
        }
    }
}
