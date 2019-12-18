package com.maxbin.monitor.service;


import lombok.extern.slf4j.Slf4j;
import com.maxbin.monitor.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户服务类
 *
 * @author mason
 * @date 2019/6/1
 */
@Slf4j
@Component
public class UserService {

    private List<User> userList = new ArrayList<User>();
    /**
     * 根据ID获取用户
     *
     * @return
     */
    public User getUser(int id) {
        String userId = String.valueOf(id);
        return User.builder().id(userId)
                .name("user-" + userId)
                .dateOfBirth(new Date())
                .gender("m")
                .email("user-" + userId + "@test.com")
                .build();
    }

    /**
     * 获取用户数组
     *
     * @return
     */
    public List<User> getUsers(int num) {
        userList.clear();
        for(int i=0 ; i < num; i++){
            userList.add(mockUser());
        }
        return userList;
    }


    /**
     * 内存溢出
     *
     * @return
     */
    public void getUserWithOOM() throws InterruptedException {

        while(true){
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(1);
            userList.add(mockUser());
            log.debug("add user count:" + userList.size());
        }
    }

    /**
     * 模拟用户
     *
     * @return
     */
    private User mockUser() {
        String userId = UUID.randomUUID().toString();
        return User.builder().id(userId)
                .name("user-" + userId)
                .dateOfBirth(new Date())
                .gender("m")
                .email("user-" + userId + "@test.com")
                .build();
    }

    public void doSomething() {
        System.out.println("start...");
        double a = Math.random();
        sleep();
        System.out.println("end");
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
