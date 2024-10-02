package com.sonnguyen.individual.nhs.context;

import javax.enterprise.inject.Model;

@Model
public class ApplicationConfig {
    @Value(name = "application.debug")
    private String DEBUG_MODE;
    public boolean debugEnable(){
        return DEBUG_MODE.equals("true");
    }
}
