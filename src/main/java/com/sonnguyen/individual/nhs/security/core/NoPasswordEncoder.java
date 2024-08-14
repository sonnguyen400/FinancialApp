package com.sonnguyen.individual.nhs.security.core;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Model;

@Model
@Alternative
public class NoPasswordEncoder implements PasswordEncoder {
    private static NoPasswordEncoder instance;
    public static synchronized NoPasswordEncoder getInstance() {
        if(instance==null) instance=new NoPasswordEncoder();
        return instance;
    }
    public String encodePassword(String password) {
        return password;
    }
    public String decodePassword(String encodedPassword) {
        return encodedPassword;
    }

    @Override
    public String encode(String rawPassword) {
        return rawPassword;
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }
}
