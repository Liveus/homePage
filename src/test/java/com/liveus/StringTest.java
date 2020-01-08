package com.liveus;

public class StringTest {
    public static void main(String[] args){
/*        String[][] aa = new String[][]{{"1","a"},{"2","b"},{"3","c"}};
        String[] a = {"1","a"};
        System.out.println(a.equals(aa[0]));*/


/*        String ab = "a"+"b";
        System.out.println(ab.hashCode());

        String c = "ab";*/


        String s1 = "a";
        String s2 = s1 + "b";
        String s3 = "a" + "b";
        System.out.println(s2 == "ab");
        System.out.println(s3 == "ab");

        String s = "a" + "b" + "c" + "d";
        System.out.println(s == "abcd");
    }
}
