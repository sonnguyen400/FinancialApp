package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Repository.IRepository.IBranchRepository;
import com.sonnguyen.individual.nhs.Service.IService.IBranchService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class BranchService implements IBranchService {
    @Inject
    private IBranchRepository branchRepository;
}
