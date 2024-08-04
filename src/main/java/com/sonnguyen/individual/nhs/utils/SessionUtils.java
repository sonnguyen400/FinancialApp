package com.sonnguyen.individual.nhs.utils;

import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.security.UserDetailImp;

import javax.servlet.http.HttpServletRequest;

public class SessionUtils {
    public static final String LOGIN_SESSION = "SESSION_x3sd";
    public static void setSession(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }
    public static Object getSession(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }
    public static UserDetailImp getPrincipal(HttpServletRequest request){
        return (UserDetailImp) getSession(request,SessionUtils.LOGIN_SESSION);
    }
}
