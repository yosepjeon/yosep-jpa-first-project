package com.yosep.spring.config.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = { ProfileDev.class, ProfileProd.class })
@Configuration
public class ProfileConfig {
}
