package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Service.IService.IBranchService;
import com.sonnguyen.individual.nhs.dao.Idao.IBranchDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class BranchService implements IBranchService {
    @Inject
    private IBranchDAO branchRepository;
}
