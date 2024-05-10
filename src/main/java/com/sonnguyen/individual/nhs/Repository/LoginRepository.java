package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.Repository.IRepository.ILoginRepository;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Model
public class LoginRepository extends Repository<Login,Integer> implements ILoginRepository  {
    @Override
    public Class<Login> getEntityClass() {
        return Login.class;
    }

    @Override
    public boolean validatePIN(Integer LoginId,String PIN) {
        Connection connection=getConnection();
        String query="select case \n" +
                "\twhen PIN='?' then true\n" +
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
                result=rs.getBoolean(1);
            } catch (SQLException e) {
                return false;
            }finally {

                if(rs!=null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
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
}
