package com.LeetCode;

// 3136. Problem : Valid Word

import java.util.*;
public class Problem3136 {
    public static boolean isVowel(String str, char ch){
        for(char c : str.toCharArray()){
            if(c == ch) return true;
        }
        return false;
    }

    public static boolean isConsonant(String str, char ch){
        for(char c : str.toCharArray()){
            if(c == ch) return true;
        }
        return false;
    }

    public static boolean isNumber(String str, char ch){
        for(char c : str.toCharArray()){
            if(c == ch) return true;
        }
        return false;
    }

    public static boolean isValid(String word) {
        int numFlag = 0;
        String num = "0123456789";

        int vowelFlag = 0;
        String vowel = "aeiouAEIOU";

        int consonantFlag = 0;
        String consonant = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";

        if(word.length() < 3) return false;
        int specialFlag = 0;

        for(char c : word.toCharArray()){

            if(isVowel(vowel, c)){
                vowelFlag = 1;
            }

            else if(isConsonant(consonant, c)){
                consonantFlag = 1;
            }

            else if(isNumber(num, c)) {
                numFlag = 0;
            }

            else if(!isNumber(num, c)){
                specialFlag = 1;
            }

            else specialFlag = 1;
        }

        if(numFlag == 1 || specialFlag == 1) return false;
        else if(vowelFlag == 1 && consonantFlag == 1){
            return true;
        } else return false;

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of test cases: ");
        int n = sc.nextInt();

        for(int z=0; z<n; z++){
            System.out.println("Enter a word: ");
            System.out.println(isValid(sc.next()));
        }
    }
}
