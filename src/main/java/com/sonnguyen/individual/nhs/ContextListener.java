package com.sonnguyen.individual.nhs;

import com.sonnguyen.individual.nhs.constant.LoanStatus;
import com.sun.istack.logging.Logger;

public class ContextListener {
    static final Logger logger = Logger.getLogger(ContextListener.class);
    public static void main(String[] args) {
        System.out.println(LoanStatus.valueOf("fPPROVED").value);

    }
}