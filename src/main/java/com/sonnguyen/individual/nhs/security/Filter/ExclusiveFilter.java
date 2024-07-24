package com.sonnguyen.individual.nhs.Security.Filter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ExclusiveFilter {
    private static List<String> permitUrl=List.of("/login","/register","/resources","app/otp");
    private static List<String> permitRgx=List.of();
    public static boolean isAllow(ServletRequest servletRequest){
        String requestUrl=((HttpServletRequest) servletRequest).getRequestURI().replace("/nhs_war_exploded","");
        return permitUrl.stream().anyMatch(requestUrl::contains)||requestUrl.equals("/");
    }
}
