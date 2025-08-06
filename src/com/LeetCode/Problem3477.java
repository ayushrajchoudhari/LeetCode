package com.LeetCode;

// 3477. Problem : Fruits Into Baskets II

import java.util.*;
public class Problem3477 {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        if(fruits.length != baskets.length){
            return -1;
        }
        int len = fruits.length;

        int[] arr = new int[len];
        Arrays.fill(arr, 0);
        int num = len;

        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                if(arr[j] == 0 && fruits[i] <= baskets[j]){
                    arr[j] = 1;
                    num--;
                    break;
                }
            }
        }

        return num;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of test cases: ");
        int n = sc.nextInt();

        Problem3477 solution = new Problem3477();

        for(int z=0; z<n; z++){
            System.out.println("Enter number of entries: ");
            int s = sc.nextInt();
            int[] arr1 = new int[s];
            int[] arr2 = new int[s];

            for(int x=0; x<s; x++){
                System.out.println("Enter number of fruit: ");
                arr1[x] = sc.nextInt();
                System.out.println("Enter capacity of basket: ");
                arr2[x] = sc.nextInt();
            }

            System.out.println(solution.numOfUnplacedFruits(arr1, arr2));
        }
    }
}
