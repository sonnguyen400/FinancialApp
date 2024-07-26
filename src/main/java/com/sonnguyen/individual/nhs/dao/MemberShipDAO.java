package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.model.Membership;
import com.sonnguyen.individual.nhs.dao.idao.IMemberShipDao;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Model
public class MemberShipDAO extends DAO<Membership,Integer> implements IMemberShipDao {
    @Override
    public Class<Membership> getEntityClass() {
        return Membership.class;
    }

    @Override
    public Optional<Membership> findById(Connection connection, int id) throws SQLException {
        List<Membership> memberships = new ArrayList<>(executeSelect(connection,"select * from membership where id=?",id));
        if(memberships.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(memberships.get(0));
    }
}
