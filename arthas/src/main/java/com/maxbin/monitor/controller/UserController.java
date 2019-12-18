package com.maxbin.monitor.controller;

import com.maxbin.monitor.entity.User;
import com.maxbin.monitor.service.UserService;
import com.maxbin.monitor.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * user控制器
 *
 * @author mason
 * @date 2019/6/1
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user/{id}")
    public ResponseResult<User> getUser(@PathVariable int id) {
        if (id < -1) {
            throw new RuntimeException("id 应大于0");
        }
        User user = userService.getUser(id);
        return ResponseResult.ok(user);
    }

    @GetMapping("/user/delay/{id}")
    public ResponseResult<User> getUserDelay(@PathVariable int id) {
        if (id < -1) {
            throw new RuntimeException("id 应大于0");
        }
        User user = userService.getUser(id);
        userService.doSomething();
        return ResponseResult.ok(user);
    }

    @GetMapping("/users")
    public ResponseResult<User> getUsers(int num) {
        List<User> users = userService.getUsers(num);
        return ResponseResult.ok(users);
    }

    @GetMapping("/oom")
    public ResponseResult<User> getUserWithOOM() {
        new Thread(() -> {
            try {
                userService.getUserWithOOM();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return ResponseResult.ok("done");
    }

    @GetMapping("/exception")
    public ResponseResult<String> getException() {
            System.out.println("start");
            int i = 1 / 0;
            System.out.println("end");
        return ResponseResult.ok("done");
    }
}
