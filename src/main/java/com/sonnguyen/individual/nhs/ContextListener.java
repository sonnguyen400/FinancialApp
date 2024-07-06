package com.sonnguyen.individual.nhs;

import com.sonnguyen.individual.nhs.Model.SavingsInfo;
import com.sonnguyen.individual.nhs.Model.Tier;
import com.sonnguyen.individual.nhs.dao.GeneralDAO;
import com.sonnguyen.individual.nhs.dao.MemberShipDAO;
import com.sonnguyen.individual.nhs.dao.TierDAO;
import com.sun.istack.logging.Logger;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.Optional;

public class ContextListener {
    static final Logger logger = Logger.getLogger(ContextListener.class);
    public static void main(String[] args) {
        MemberShipDAO memberShipDAO=new MemberShipDAO();
        try {
            System.out.println(memberShipDAO.findById(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}