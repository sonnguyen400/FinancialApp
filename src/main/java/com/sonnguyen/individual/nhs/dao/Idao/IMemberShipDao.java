package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.Model.Membership;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
public interface IMemberShipDao extends AbstractDAO<Membership,Integer> {
    Optional<Membership> findById(Connection connection,int id) throws SQLException;
}
