package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.model.Login;

import java.util.Optional;

public interface ILoginDAO extends AbstractDAO<Login,Integer> {
    boolean validatePIN(Integer loginId,String PIN);
    Login validateLogin(String username, String password);
    Optional<Login> findByUsername(String username);
}
