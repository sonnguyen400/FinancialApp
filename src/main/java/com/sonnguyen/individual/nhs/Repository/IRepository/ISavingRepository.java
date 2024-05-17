package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.SavingsInfor;

import java.sql.Connection;
import java.sql.SQLException;

public interface ISavingRepository extends AbstractRepository<SavingsInfor, Integer> {
    SavingsInfor findByAccountId(Integer accountId) throws SQLException;
}
