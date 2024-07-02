package com.sonnguyen.individual.nhs.DTO;

import com.sonnguyen.individual.nhs.Model.Login;

public class LoginDTO{
    private Integer id;
    private String username;
    private Integer customerId;


    public static LoginDTO parse(Login login){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.id = login.getId();
        loginDTO.username=login.getUsername();
        loginDTO.customerId=login.getCustomerId();
        return loginDTO;
    }
}
