package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.model.Membership;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
public interface IMemberShipDao extends AbstractDAO<Membership,Integer> {
    Optional<Membership> findById(Connection connection,int id) throws SQLException;
}