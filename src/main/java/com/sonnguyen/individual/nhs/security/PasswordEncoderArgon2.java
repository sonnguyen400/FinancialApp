package com.sonnguyen.individual.nhs.security;

import com.sonnguyen.individual.nhs.security.core.PasswordEncoder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import javax.enterprise.inject.Model;
@Model
public class PasswordEncoderArgon2 implements PasswordEncoder {
    private static PasswordEncoderArgon2 passwordEncoderArgon2;
    private Argon2PasswordEncoder argon2PasswordEncoder;


    public synchronized static PasswordEncoderArgon2 getInstance() {
        if(passwordEncoderArgon2==null) passwordEncoderArgon2=new PasswordEncoderArgon2(new Argon2PasswordEncoder(16, 32, 1, 60000, 10));
        return passwordEncoderArgon2;
    }

    public PasswordEncoderArgon2(Argon2PasswordEncoder argon2PasswordEncoder) {
        this.argon2PasswordEncoder = argon2PasswordEncoder;
    }

    public PasswordEncoderArgon2() {
        this.argon2PasswordEncoder=new Argon2PasswordEncoder(16, 32, 1, 60000, 10);
    }

    @Override
    public String encode(String rawPassword) {
        return argon2PasswordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword){
        return argon2PasswordEncoder.matches(rawPassword,encodedPassword);
    }
}
