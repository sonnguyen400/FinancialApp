package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.dao.Idao.ILoginDAO;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Model
public class LoginDAO extends DAO<Login,Integer> implements ILoginDAO {

    @Override
    public Class<Login> getEntityClass() {
        return Login.class;
    }

    @Override
    public boolean validatePIN(Integer LoginId,String PIN) {
        Connection connection=getConnection();
        String query="select case \n" +
                "\twhen PIN=? then true\n" +
                "    else false\n" +
                "    end as result\n" +
                " from login where id=?;";
        if(connection!=null){
            ResultSet rs=null;
            Boolean result;
            try(PreparedStatement preparedStatement=connection.prepareStatement(query)) {
                preparedStatement.setString(1,PIN);
                preparedStatement.setInt(2,LoginId);
                rs= preparedStatement.executeQuery();
                rs.next();
                result=rs.getBoolean(1);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }finally {

                if(rs!=null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return result;
        }

        return false;
    }

    @Override
    public Login validateLogin(String username, String password) {
        String query="select * from login where username=? and password=?";
        List<Login> login;
        try {
            login=find(query,username,password);
            if(login.size()!=1) throw new SQLException("Result set contains not just one login");
        } catch (SQLException e) {
            return null;
        }
        return login.get(0);
    }

    @Override
    public Optional<Login> findByUsername(String username) {
        String query="select * from login where username=?";
        try {
            List<Login> logins=executeSelect(query,username);
            if(logins.isEmpty()) return Optional.empty();
            return Optional.of(logins.get(0));
        } catch (SQLException e) {
            return Optional.empty();
        }

    }
}
