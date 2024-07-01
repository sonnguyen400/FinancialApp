package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.Model.Login;

public interface ILoginDAO extends AbstractDAO<Login,Integer> {
    boolean validatePIN(Integer loginId,String PIN);
    Login validateLogin(String username, String password);
}
