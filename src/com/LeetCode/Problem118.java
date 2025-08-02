package com.LeetCode;

// 118. Problem : Pascal's Triangle

import java.util.*;
public class Problem118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> arr = new ArrayList<>();

        if(numRows == 0){
            return arr;
        }

        if(numRows > 0){
            arr.add(new ArrayList<Integer>(
                    Arrays.asList(1)
            ));

            for(int i=1; i<numRows; i++){
                arr.add(new ArrayList<Integer>());
                arr.get(i).add(1);

                if(i >= 2){
                    for(int k=0; k<i-1; k++){
                        arr.get(i).add( (arr.get(i-1).get(k)) + (arr.get(i-1).get(k+1)) );
                    }

                }
                arr.get(i).add(1);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Problem118 solution = new Problem118();

        System.out.print("Enter the number of test cases: ");
        int n = sc.nextInt();

        for (int t = 1; t <= n; t++){
            System.out.print("Enter the number of rows: ");
            System.out.println(solution.generate(sc.nextInt()));
        }
    }
}
