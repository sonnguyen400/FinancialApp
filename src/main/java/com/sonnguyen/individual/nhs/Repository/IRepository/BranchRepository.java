package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Branch;

public class BranchRepository extends Repository<Branch,Integer> {
    public static final BranchRepository branchRepository=new BranchRepository();
    public static BranchRepository getInstance(){
        return branchRepository;
    }
}
