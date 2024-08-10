package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.idao.ILoanSettingDAO;
import com.sonnguyen.individual.nhs.model.LoanSetting;
import com.sonnguyen.individual.nhs.service.iservice.ILoanSettingService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Model
public class LoanSettingService implements ILoanSettingService {
    @Inject
    private ILoanSettingDAO loanSettingDAO;


    @Override
    public List<LoanSetting> findAll() {
        return loanSettingDAO.findAll();
    }

    @Override
    public Optional<LoanSetting> findByTerm(Integer term) {
        return loanSettingDAO.findByTerm(term);
    }
    @Override
    public Optional<LoanSetting> findById(Integer id){
        return loanSettingDAO.findById(id);
    }
}
