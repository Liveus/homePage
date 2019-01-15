package com.liveus.homePage;

public class SleepCode {
    public static void main(){
        int[] a = new int[]{1,2,5,4};
        for (int b :a){
            try {
                Thread.sleep(b);
                System.out.println(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
