package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.dao.Idao.ILoginDAO;
import com.sonnguyen.individual.nhs.service.iService.ILoginService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class LoginService implements ILoginService {
    @Inject
    ILoginDAO loginRepository;
    @Override
    public boolean validatePIN(Integer loginId,String PIN){
        return loginRepository.validatePIN(loginId,PIN);
    }
    @Override
    public Login validateLogin(String username, String password){
        return loginRepository.validateLogin(username,password);
    }
}
