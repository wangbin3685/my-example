package com.maxbin.monitor.service;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author : binwang
 * @date : 2019/12/16
 */
public class MyThread implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("my thread");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();




    }
}
