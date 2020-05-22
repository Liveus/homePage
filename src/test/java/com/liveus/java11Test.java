package com.liveus;

import org.junit.Test;

import java.util.Objects;

import static java.lang.System.out;

public class java11Test {
    //bigdecimal
    public static void main(String[] args){
/*        if(true){
            var a = 0.0023d;
            BigDecimal b = new BigDecimal(String.valueOf(0.0023));
            System.out.println(a*100);
            System.out.println(b);
        }*/

        var a = "xtea";
        var b = 2;
        var c = 'c';
        String z = "rggb";
        char[] zs = z.toCharArray();
        out.println(a);
        out.println(b);
        out.println(c);


/*        User user = new User();
        user.setName("aa");
        user.setId(11);
        System.out.println(user.toString());
        user = new User();
        user.setName("qq");
        System.out.println(user.toString());*/

/*        float a = (float) (Math.round(Float.valueOf(new String("288074"))*10000f)/ 10000f);
        System.out.println(a);
        System.out.println(Float.valueOf(new String("288074"))*10000f);
        System.out.println(Math.round(Float.valueOf(new String("288074"))*10000f));
        System.out.println(Math.round((288074f*10000f)));
        System.out.println(288074f*10000f);*/

        String s1 = "hello​world‌";

        String s2 = "helloworld";

        out.println(s1.equals(s2));

    }

    public static double myPow(double x, int n) {
        double origin;
        if(n==0){
            x = 1;
        }
        else if(n>0){
            origin = x;
            for (int i = 0;i<n-1;i++){
                x = x*origin;
            }
        }else {
            origin = 1d/x;
            x = origin;
            for (int i = 0;i<Math.abs(n)-1;i++){
                x = x*origin;
            }
        }
        return x;
    }

    @Test
    public void myPowTest(){
        out.println(Objects.equals(Math.pow(2.1,3),myPow2(2.1,3)));
        out.println(Objects.equals(Math.pow(2,-2),myPow2(2,-2)));
        out.println(Objects.equals(Math.pow(-2,2),myPow2(-2,2)));
        out.println(Objects.equals(Math.pow(-2,-2),myPow2(-2,-2)));
        out.println(Objects.equals(Math.pow(1,2147483647),myPow2(1,2147483647)));
    }
    public static double quickMul(double x,int n){
        double res = 0;
        if(n==0){
            return 1;
        }
        double y = quickMul(x,n/2);
        return n%2==0?y*y:y*y*x;
    }

    public static double myPow2(double x,int n){
        return n>0?quickMul(x,n):1/quickMul(x,-n);
    }





}
