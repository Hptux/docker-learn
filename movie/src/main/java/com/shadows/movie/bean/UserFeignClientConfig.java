package com.shadows.movie.bean;

import feign.Contract;
import org.springframework.context.annotation.Bean;

public class UserFeignClientConfig {
    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }
}
