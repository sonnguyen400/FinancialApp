package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.Idao.IBranchDAO;
import com.sonnguyen.individual.nhs.service.iService.IBranchService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class BranchService implements IBranchService {
    @Inject
    private IBranchDAO branchRepository;
}
