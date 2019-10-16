package com.liveus;

import static java.lang.System.out;

public class java11Test {
    //bigdecimal
    public static void main(String[] args){
/*        if(true){
            var a = 0.0023d;
            BigDecimal b = new BigDecimal(String.valueOf(0.0023));
            System.out.println(a*100);
            System.out.println(b);
        }

        User user = new User();
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
}
