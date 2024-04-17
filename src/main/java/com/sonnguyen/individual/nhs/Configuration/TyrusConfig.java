package com.sonnguyen.individual.nhs.Configuration;

import org.jboss.weld.context.http.HttpSessionContext;
import org.springframework.context.annotation.Bean;

import javax.enterprise.inject.Model;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.UUID;

@Model
public class TyrusConfig extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        sec.getUserProperties().put("username", UUID.randomUUID().toString().substring(0,6));
        sec.getConfigurator().modifyHandshake(sec,request,response);
    }
}
