package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Login;

public interface ILoginRepository extends AbstractRepository<Login,Integer>{
    boolean validatePIN(Integer loginId,String PIN);
    Login validateLogin(String username, String password);
}
