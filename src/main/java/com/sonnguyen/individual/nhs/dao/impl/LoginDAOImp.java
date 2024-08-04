package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao.idao.ILoginDAO;
import com.sonnguyen.individual.nhs.dao.core.AbstractDAO;
import com.sonnguyen.individual.nhs.model.Login;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Model
public class LoginDAOImp extends AbstractDAO<Login,Integer> implements ILoginDAO {

    @Override
    protected Class<Login> getEntityType() {
        return Login.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
    @Override
    public boolean validatePIN(int loginId,String pin){
        Connection connection=getConnection();
        String query="select case when PIN=? then true else false end as result from login where id=?";
        Integer result=select(connection,query,Integer.class,pin,loginId);
        try {
            connection.close();
        } catch (SQLException|NullPointerException e) {
            System.out.println("Connection can't be closed");
        }
        return result==1;
    }

    @Override
    public Login validateLogin(String username,String password){
        Connection connection=getConnection();
        String query="select * from login where username=? and password=?";
        List<Login> logins=executeSelect(connection,query,username,password);
        try{
            connection.close();
        } catch (SQLException|NullPointerException e) {
            System.out.println("Connection can't be closed");
        }
        if(logins!=null&&logins.size()==1) return logins.get(0);
        return null;
    }
    @Override
    public Optional<Login> findByUsername(String username){
        String query="select * from login where username=?";
        List<Login> login=executeSelect(query,username);
        if(login.isEmpty()) return Optional.empty();
        return Optional.of(login.get(0));
    }
}
