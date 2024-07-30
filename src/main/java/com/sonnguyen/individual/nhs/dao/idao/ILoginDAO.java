package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.dao.core.GeneralDAO;
import com.sonnguyen.individual.nhs.model.Login;

public interface ILoginDAO extends GeneralDAO<Login,Integer> {
    boolean validatePIN(int loginId,String PIN);
    Login validateLogin(String username, String password);
}
