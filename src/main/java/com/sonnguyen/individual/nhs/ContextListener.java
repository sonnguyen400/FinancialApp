package com.sonnguyen.individual.nhs;

import com.sonnguyen.individual.nhs.Model.SavingsInfo;
import com.sun.istack.logging.Logger;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ContextListener {
    static final Logger logger = Logger.getLogger(ContextListener.class);
    public static void main(String[] args) {
        SavingsInfo savingsInfo=new SavingsInfo();
        ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
        Validator validator=factory.getValidator();
        validator.validate(savingsInfo, SavingsInfo.class);
    }
}