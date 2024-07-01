package com.sonnguyen.individual.nhs.service.iService;

import com.sonnguyen.individual.nhs.model.Login;

public interface ILoginService {
    boolean validatePIN(Integer loginId,String PIN);
    Login validateLogin(String username, String password);
}
