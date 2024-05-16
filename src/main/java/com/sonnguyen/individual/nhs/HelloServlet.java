package com.sonnguyen.individual.nhs;


import com.sonnguyen.individual.nhs.Constant.LoanStatus;

public class HelloServlet {
    public static void main(String[] args){
        System.out.println(LoanStatus.valueOf("PENDING"));
    }
}