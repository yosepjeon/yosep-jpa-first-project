package com.yosep.spring.config.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile(value = "develop")
@PropertySource({"classpath:develop/application.yml"})
public class ProfileDev {

}
