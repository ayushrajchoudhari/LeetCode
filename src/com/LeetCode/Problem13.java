package com.LeetCode;

// 13. Problem : Roman to Integer

import java.util.*;
public class Problem13 {
    private static int number(char c){
        if(c == 'I' || c == 'i'){
            return 1;
        } else if(c == 'V' || c == 'v'){
            return 5;
        } else if(c == 'X' || c == 'x'){
            return 10;
        } else if(c == 'L' || c == 'l'){
            return 50;
        } else if(c == 'C' || c == 'c'){
            return 100;
        } else if(c == 'D' || c == 'd'){
            return 500;
        } else if(c == 'M' || c == 'm'){
            return 1000;
        } else{
            return 0;
        }
    }

    public static int romanToInt(String s) {

        int current = number(s.charAt(0));
        int sum = 0;

        for(int i=1; i<s.length(); i++){
            int next = number(s.charAt(i));

            if(current < next){
                sum = sum - current;
            } else {
                sum = sum + current;
            }

            current = next;
        }

        sum = sum + current;
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of test cases: ");
        int n = sc.nextInt();

        for(int z=0; z<n; z++){
            System.out.println("Enter a Roman number: ");
            System.out.println(romanToInt(sc.next()));
        }
    }
}
