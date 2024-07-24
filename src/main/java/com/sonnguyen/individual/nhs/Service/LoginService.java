package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.Service.IService.ILoginService;
import com.sonnguyen.individual.nhs.dao.Idao.ILoginDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Optional;

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

    @Override
    public Optional<Login> findByUsername(String username) {
        return Optional.empty();
    }
}
