package com.sonnguyen.individual.nhs.service.iservice;

import java.util.concurrent.CompletableFuture;

public interface IEmailService {
    CompletableFuture<Void> sendEmail(String dest, String content, String subject);
}
