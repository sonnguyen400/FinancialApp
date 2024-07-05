package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.Model.Membership;
import com.sonnguyen.individual.nhs.dao.Idao.IMemberShipDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberShipDAO extends DAO<Membership,Integer> implements IMemberShipDao {
    @Override
    public Optional<Membership> findById(Connection connection, int id) throws SQLException {
        List<Membership> memberships = new ArrayList<>(executeSelect(connection,"select * from membership where id=?",id));
        if(memberships.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(memberships.get(0));
    }
}