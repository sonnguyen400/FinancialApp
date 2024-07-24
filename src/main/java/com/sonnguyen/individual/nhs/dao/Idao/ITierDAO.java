package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.Model.Tier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface ITierDAO extends AbstractDAO<Tier,Integer>{
    Optional<Tier> findById(Connection connection, int id) throws SQLException;
}
