package com.LeetCode;

// 1957. Problem : Delete Characters to Make Fancy String

import java.util.*;
public class Problem1957 {
    public static String makeFancyString(String s) {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<s.length(); i++){
            if(sb.length() < 2){
                sb.append(s.charAt(i));
            }
            else if(s.charAt(i) != s.charAt(i-1) || s.charAt(i) != s.charAt(i-2)){
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of test cases: ");
        int n = sc.nextInt();

        for(int i=0; i<n; i++){
            System.out.println("Enter a string: ");
            System.out.println(makeFancyString(sc.next()));
        }
    }
}
