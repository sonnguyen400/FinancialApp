package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.Repository.IRepository.ILoginRepository;
import com.sonnguyen.individual.nhs.Service.IService.ILoginService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class LoginService implements ILoginService {
    @Inject
    ILoginRepository loginRepository;
    @Override
    public boolean validatePIN(Integer loginId,String PIN){
        return loginRepository.validatePIN(loginId,PIN);
    }
    @Override
    public Login validateLogin(String username, String password){
        return loginRepository.validateLogin(username,password);
    }
}
