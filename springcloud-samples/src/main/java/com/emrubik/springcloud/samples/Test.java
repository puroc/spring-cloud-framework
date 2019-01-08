package com.emrubik.springcloud.samples;

import com.emrubik.springcloud.api.idm.IUserService;
import com.emrubik.springcloud.domain.to.base.BaseReq;
import com.emrubik.springcloud.domain.to.payload.login.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class Test implements CommandLineRunner {

    @Value("${ip}")
    private String ip;

    @Autowired
    private IUserService userService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("````````````````````");
        System.out.println(ip);
        System.out.println("````````````````````");
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        while (true) {
                            try {
                                System.out.println("please input...");
                                String line = reader.readLine();
                                if ("quit".equalsIgnoreCase(line)) {
                                    System.out.println("game over");
                                    System.exit(0);
                                } else if ("1".equalsIgnoreCase(line)) {
                                    login();
                                    System.out.println("login has send");
                                } else if ("2".equalsIgnoreCase(line)) {
                                    getUserInfo();
                                    System.out.println("getUserInfo has send");
                                } else {
                                    System.out.println("please input \"1\" or \"quit\"");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();

    }

    private void login() {
        LoginReq loginReq = new LoginReq();
        loginReq.setUsername("lisi");
        loginReq.setPassword("111");
        BaseReq<LoginReq> baseReq = new BaseReq<LoginReq>();
        baseReq.setPayLoad(loginReq);
        long start = System.currentTimeMillis();
        ResponseEntity resp = userService.login(baseReq);
        long end = System.currentTimeMillis();
        System.out.println("111111111111111 " + resp + ",time:" + (end - start));
    }

    private void getUserInfo() {
        long start = System.currentTimeMillis();
        ResponseEntity resp = userService.getUserInfo();
        long end = System.currentTimeMillis();
        System.out.println("222222222222 " + resp + ",time:" + (end - start));
    }
}
