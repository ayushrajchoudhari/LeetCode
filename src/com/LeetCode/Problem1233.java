package com.LeetCode;

// 1233. Problem : Remove Sub-Folders from the Filesystem

import java.util.*;
public class Problem1233 {
    public static boolean checkSubFolder(String fold, String subFold){
        for(int f=0; f<fold.length(); f++){
            if(fold.charAt(f) != subFold.charAt(f)) {
                return false;
            }
        }

        if((subFold.length() > fold.length())){
            if((subFold.length() == fold.length())){
                return false;
            }
            else if(subFold.charAt(fold.length()) != '/'){
                return false;
            }
        }

        return true;
    }

    public static List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        int len = folder.length;

        List<String> arr = new ArrayList<String>();
        arr.add(folder[0]);

        if(len<2){
            arr.add(folder[0]);
            return arr;
        }

        String fold = folder[0];
        for(int i=1; i<len; i++){
            String subFold = folder[i];
            if(!checkSubFolder(fold, subFold)){
                arr.add(folder[i]);
                fold = subFold;
            }
        }

        return arr;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of test cases: ");
        int n = sc.nextInt();

        for(int z=0; z<n; z++){
            System.out.println("Enter the size of array: ");
            String[] arr = new String[sc.nextInt()];

            for(int x=0; x<arr.length; x++){
                System.out.println("Enter the element of array: ");
                arr[x] = sc.next();
            }
            System.out.println(removeSubfolders(arr));
        }
    }
}
