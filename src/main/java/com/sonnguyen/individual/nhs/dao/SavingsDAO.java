package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.model.SavingsInfo;
import com.sonnguyen.individual.nhs.dao.idao.ISavingDAO;

import javax.enterprise.inject.Model;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Model
public class SavingsDAO extends DAO<SavingsInfo,Integer> implements ISavingDAO {

    @Override
    public Class<SavingsInfo> getEntityClass() {
        return SavingsInfo.class;
    }



    @Override
    public Optional<SavingsInfo> findByAccountId(Integer accountId) throws SQLException {
        String query="select * from savings_info where account_id=?";
        List<SavingsInfo> result= executeSelect(query,accountId);
        if(result!=null && result.size()==1){
            return Optional.of(result.get(0));
        }
        return Optional.empty();
    }


}
