package com.sonnguyen.individual.nhs.Configuration;

import com.sonnguyen.individual.nhs.Service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
    @Bean
    public AccountService accountService(){
        return new AccountService();
    }
}
