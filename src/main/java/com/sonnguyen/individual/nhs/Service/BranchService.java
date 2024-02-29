package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Repository.IRepository.IBranchRepository;
import com.sonnguyen.individual.nhs.Service.IService.IBranchService;

import javax.inject.Inject;

public class BranchService implements IBranchService {
    @Inject
    private IBranchRepository branchRepository;
}
