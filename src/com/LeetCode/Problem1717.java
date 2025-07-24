package com.LeetCode;

// 1717. Problem : Maximum Score From Removing Substring

import java.util.*;
public class Problem1717 {
    public static int maximumGain(String s, int x, int y) {
        int totalScore = 0;

        // Determine which pair has a higher score
        String highPriorityPair = (x > y) ? "ab" : "ba";
        String lowPriorityPair = (x > y) ? "ba" : "ab";
        int highPriorityScore = Math.max(x, y);
        int lowPriorityScore = Math.min(x, y);

        // First pass: Greedily remove all high-priority pairs
        StringBuilder stringAfterFirstPass = new StringBuilder();
        for (char currentChar : s.toCharArray()) {
            // Check if the current character and the last character in our builder form a high-priority pair
            if (currentChar == highPriorityPair.charAt(1) &&
                    stringAfterFirstPass.length() > 0 &&
                    stringAfterFirstPass.charAt(stringAfterFirstPass.length() - 1) == highPriorityPair.charAt(0)) {

                totalScore += highPriorityScore;
                stringAfterFirstPass.deleteCharAt(stringAfterFirstPass.length() - 1); // "Pop" the last character
            } else {
                stringAfterFirstPass.append(currentChar); // "Push" the current character
            }
        }

        // Second pass: Remove all low-priority pairs from the remaining string
        StringBuilder finalString = new StringBuilder();
        for (char currentChar : stringAfterFirstPass.toString().toCharArray()) {
            // Check if the current character and the last character in our new builder form a low-priority pair
            if (currentChar == lowPriorityPair.charAt(1) &&
                    finalString.length() > 0 &&
                    finalString.charAt(finalString.length() - 1) == lowPriorityPair.charAt(0)) {

                totalScore += lowPriorityScore;
                finalString.deleteCharAt(finalString.length() - 1); // "Pop"
            } else {
                finalString.append(currentChar); // "Push"
            }
        }

        return totalScore;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of test cases: ");
        int n = sc.nextInt();

        for(int z=0; z<n; z++){
            System.out.println("Enter the score for x: ");
            int x = sc.nextInt();
            System.out.println("Enter the score for y: ");
            int y = sc.nextInt();
            System.out.println("Enter a string: ");
            System.out.println(maximumGain(sc.next(),x,y));
        }
    }
}
