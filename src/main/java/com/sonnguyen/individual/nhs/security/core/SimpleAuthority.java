package com.sonnguyen.individual.nhs.security.core;

public class SimpleAuthority implements UserAuthority {
    private String authority;
    public SimpleAuthority(String authority) {
        this.authority = authority;
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
