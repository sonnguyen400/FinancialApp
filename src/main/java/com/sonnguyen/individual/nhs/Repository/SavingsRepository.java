package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Constant.AccountType;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.AccountHolder;
import com.sonnguyen.individual.nhs.Model.SavingsInfor;
import com.sonnguyen.individual.nhs.Repository.IRepository.ISavingRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SavingsRepository extends Repository<SavingsInfor,Integer> implements ISavingRepository {

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
