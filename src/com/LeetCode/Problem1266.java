package com.LeetCode;

// 1266. Problem : Minimum Time Visiting All Points

public class Problem1266 {

    public static int minTimeToVisitAllPoints(int[][] points) {
        int result = 0;
        int size = points.length;

        for(int i=0; i<size-1; i++){
            int x = 0;
            int y = 0;
            x = Math.abs(points[i+1][0]-points[i][0]);
            y = Math.abs(points[i+1][1]-points[i][1]);

            if(x == y){
                result += Math.abs(x);
            }
            else{
                result += Math.abs(Math.max(x, y)-Math.min(x,y));
                result += Math.abs(Math.min(x,y));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 1}, {3, 4}, {-1, 0}};

        System.out.println(Problem1266.minTimeToVisitAllPoints(arr));
    }
}
