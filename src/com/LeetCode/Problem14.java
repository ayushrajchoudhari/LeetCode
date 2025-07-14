package com.LeetCode;

// 14. Problem : Longest Common Prefix

import java.util.*;
public class Problem14 {
    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        String prefix = strs[0];
        for (int i=1; i<strs.length; i++){
            while(strs[i].indexOf(prefix) != 0){
                // reduces prefix character one at a time from last till it becomes common.
                prefix = prefix.substring(0, prefix.length()-1);
            }
        }
        return prefix;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of test cases: ");
        int n = sc.nextInt();

        for (int z=0; z<n; z++){
            System.out.println("Enter the size of array: ");
            String[] strs = new String[sc.nextInt()];
            for (int y=0; y<strs.length; y++){
                System.out.println("Enter a string element: ");
                strs[y] = sc.next();
            }
            System.out.println("Output: ");
            System.out.println(longestCommonPrefix(strs));
        }
    }
}
