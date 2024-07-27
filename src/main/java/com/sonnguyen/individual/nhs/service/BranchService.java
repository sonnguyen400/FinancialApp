package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.impl.BranchDAOImpl;
import com.sonnguyen.individual.nhs.service.iservice.IBranchService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class BranchService implements IBranchService {
    @Inject
    private BranchDAOImpl branchDAO;
}
