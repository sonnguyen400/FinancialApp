package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.Model.SavingsInfor;
import com.sonnguyen.individual.nhs.dao.Idao.ISavingDAO;

import javax.enterprise.inject.Model;
import java.sql.SQLException;
import java.util.List;
@Model
public class SavingsRepository extends DAO<SavingsInfor,Integer> implements ISavingDAO {

    @Override
    public Class<SavingsInfor> getEntityClass() {
        return SavingsInfor.class;
    }



    @Override
    public SavingsInfor findByAccountId(Integer accountId) throws SQLException {
        String query="select * from savings_infor where account_id=?";
        List<SavingsInfor> result= executeSelect(query,accountId);
        if(result!=null && result.size()==1){
            return result.get(0);
        }
        return null;
    }


}
