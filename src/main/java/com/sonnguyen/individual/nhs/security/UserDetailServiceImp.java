package com.sonnguyen.individual.nhs.security;

import com.sonnguyen.individual.nhs.security.core.UserDetail;
import com.sonnguyen.individual.nhs.security.core.UserDetailService;
import com.sonnguyen.individual.nhs.service.iservice.ILoginService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Optional;

@Model
public class UserDetailServiceImp implements UserDetailService {
    @Inject
    ILoginService loginService;
    @Override
    public Optional<UserDetail> findByUsername(String username) {
        return loginService.findByUsername(username).map(UserDetailImp::of);
    }
}
