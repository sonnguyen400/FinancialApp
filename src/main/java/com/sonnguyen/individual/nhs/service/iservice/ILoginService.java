package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.model.Login;

import java.util.Optional;

public interface ILoginService {
    boolean validatePIN(Integer loginId,String PIN);
    Login validateLogin(String username, String password);
    Optional<Login> findByUsername(String username);
}
