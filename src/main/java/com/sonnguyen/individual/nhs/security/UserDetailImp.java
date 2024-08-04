package com.sonnguyen.individual.nhs.security;

import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.model.Role;
import com.sonnguyen.individual.nhs.security.core.SimpleAuthority;
import com.sonnguyen.individual.nhs.security.core.UserAuthority;
import com.sonnguyen.individual.nhs.security.core.UserDetail;

import java.util.Iterator;
import java.util.List;

public class UserDetailImp implements UserDetail {
    private int id;
    private String username;
    private Integer customerId;
    private String password;
    private List<Role> roles;
    private Customer customer;
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Iterator<UserAuthority> getAuthorities() {
        return roles.stream().map(role -> (UserAuthority) new SimpleAuthority(role.getName())).iterator();
    }
    public static UserDetailImp of(Login login){

        UserDetailImp userDetailImp=new UserDetailImp();
        userDetailImp.id=login.getId();
        userDetailImp.username=login.getUsername();
        userDetailImp.password=login.getPassword();
        userDetailImp.customerId=login.getCustomerId();
        userDetailImp.customer=login.getCustomer();
        return userDetailImp;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
