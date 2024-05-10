package com.sonnguyen.individual.nhs.Service.IService;

import com.sonnguyen.individual.nhs.Model.Login;

public interface ILoginService {
    boolean validatePIN(Integer loginId,String PIN);
    Login validateLogin(String username, String password);
}
