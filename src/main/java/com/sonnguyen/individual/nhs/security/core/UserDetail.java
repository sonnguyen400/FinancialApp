package com.sonnguyen.individual.nhs.security.core;

import java.util.Iterator;

public interface UserDetail {
    String getUsername();
    String getPassword();
    Iterator<UserAuthority> getAuthorities();
}
