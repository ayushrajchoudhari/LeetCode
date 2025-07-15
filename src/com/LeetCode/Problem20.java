package com.LeetCode;

// 20. Problem : Valid Parenthesis

import java.util.*;
public class Problem20 {
    public static boolean isValid(String s) {
        // empty stack.
        Stack<Character> stack = new Stack<Character>();

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '(') stack.push(')');
            else if(s.charAt(i) == '{') stack.push('}');
            else if(s.charAt(i) == '[') stack.push(']');
            // in case s.charAt(i) = closing bracket -> match it for the top opening bracket. <or>
            // closing brackets are more than opening.
            else if(stack.isEmpty() || stack.pop() != s.charAt(i)) return false;
        }
        // in case opening brackets are more than closing.
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of test cases: ");
        int n = sc.nextInt();

        for(int z=0; z<n; z++){
            System.out.println("Enter a parenthesis string: ");
            System.out.println(isValid(sc.next()));
        }
    }
}
