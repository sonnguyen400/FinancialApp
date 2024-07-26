package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.model.Tier;
import com.sonnguyen.individual.nhs.dao.idao.ITierDAO;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Model
public class TierDAO extends DAO<Tier,Integer> implements ITierDAO {
    @Override
    public Class<Tier> getEntityClass() {
        return Tier.class;
    }

    @Override
    public Optional<Tier> findById(Connection connection, int id) throws SQLException {
        List<Tier> tiers=new ArrayList<>(executeSelect(connection,"select * from tier where id=?",id));
        if(tiers.isEmpty()) return Optional.empty();
        else return Optional.of(tiers.get(0));
    }
}
