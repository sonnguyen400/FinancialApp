package com.sonnguyen.individual.nhs.Service.IService;

import java.util.concurrent.CompletableFuture;

public interface IEmailService {
    public CompletableFuture<Void> sendEmail(String dest, String content, String subject);
}
