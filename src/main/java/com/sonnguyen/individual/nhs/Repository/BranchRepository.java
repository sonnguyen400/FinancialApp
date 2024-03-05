package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Branch;
import com.sonnguyen.individual.nhs.Repository.IRepository.IBranchRepository;

import javax.enterprise.inject.Model;

@Model
public class BranchRepository extends Repository<Branch,Integer> implements IBranchRepository {
    @Override
    public Class<Branch> getEntityClass() {
        return Branch.class;
    }
}
