package com.yosep.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YosepJpaApplication {
    public static void main(String[] args) {
        String profile = System.getProperty("spring.profiles.active");
        if(profile == null) {
            System.out.println("profile= " + profile + "입니다.");
            System.setProperty("spring.profiles.active","develop");
        }
        profile = System.getProperty("spring.profiles.active");
//        System.out.println("spring.profiles.active= @@@" + profile);

        SpringApplication.run(YosepJpaApplication.class, args);
    }
}
