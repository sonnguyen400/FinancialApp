package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.Model.Branch;
import com.sonnguyen.individual.nhs.dao.Idao.IBranchDAO;

import javax.enterprise.inject.Model;

@Model
public class BranchDAO extends DAO<Branch,Integer> implements IBranchDAO {
    @Override
    public Class<Branch> getEntityClass() {
        return Branch.class;
    }
}
