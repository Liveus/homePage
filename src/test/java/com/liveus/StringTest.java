package com.liveus;

import java.util.List;

public class StringTest {
    public static void main(String[] args){
/*        String[][] aa = new String[][]{{"1","a"},{"2","b"},{"3","c"}};
        String[] a = {"1","a"};
        System.out.println(a.equals(aa[0]));*/


/*        String ab = "a"+"b";
        System.out.println(ab.hashCode());

        String c = "ab";*/


/*        String s1 = "a";
        String s2 = s1 + "b";
        String s3 = "a" + "b";
        System.out.println(s2 == "ab");
        System.out.println(s3 == "ab");

        String s = "a" + "b" + "c" + "d";
        System.out.println(s == "abcd");*/
        int[][] points = new int[][]{{0,0},{0,1},{1,0},{0,2},{2,0}};
        System.out.println(largestTriangleArea(points));
    }

    public static double largestTriangleArea(int[][] points) {
        int maxArea = 0;
        for(int i = 0;i<points.length;i++){
            for(int j = 0;j<points.length;j++){
                for(int k =0;k<points.length;k++){
                    int area =
                            Math.abs(points[i][0]*points[j][1]-
                                    points[j][0]*points[k][1]+
                                    points[k][0]*points[i][1]-
                                    points[i][0]*points[k][1]+
                                    points[j][0]*points[i][1]-
                                    points[k][0]*points[j][1]);
                    if(area==8){
                        System.out.println(i);
                        System.out.println(j);
                        System.out.println(k);
                    }
                    maxArea = area/2>maxArea?area/2:maxArea;
                }
            }
        }
        return maxArea;
    }
}
