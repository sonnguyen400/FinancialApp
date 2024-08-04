package com.sonnguyen.individual.nhs.security.core;

import java.util.Optional;

public interface UserDetailService {
    Optional<UserDetail> findByUsername(String username);
}
