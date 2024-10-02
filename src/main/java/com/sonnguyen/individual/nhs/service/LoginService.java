package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.context.Value;
import com.sonnguyen.individual.nhs.dao.impl.LoginDAOImp;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.service.iservice.ILoginService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Optional;

@Model
public class LoginService implements ILoginService {
    @Inject
    LoginDAOImp loginDAO;
    @Override
    public boolean validatePIN(Integer loginId,String PIN){
        return loginDAO.validatePIN(loginId,PIN);
    }
    @Override
    public Login validateLogin(String username, String password){
        return loginDAO.validateLogin(username,password);
    }
    @Override
    public Optional<Login> findByUsername(String username) {

        return loginDAO.findByUsername(username);
    }
}
