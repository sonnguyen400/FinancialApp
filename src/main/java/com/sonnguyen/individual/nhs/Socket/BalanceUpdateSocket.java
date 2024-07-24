package com.sonnguyen.individual.nhs.Socket;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@ApplicationScoped
@ServerEndpoint(value="/balance")
@Model
public class BalanceUpdateSocket {
    static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    @OnOpen
    public void onOpen(Session session) {

        sessions.add(session);
    }
    @OnMessage
    public void onMessage(String message,Session session) throws IOException {

        session.getBasicRemote().sendText(session.getId());
        System.out.println(session.getId());
        String username = (String) session.getUserProperties().get("username");
        if (username == null) {
            session.getUserProperties().put("username", message);
            session.getBasicRemote().sendText("System: you are connectd as " + message);
        } else {
            for (Session session_ : sessions) {
                session_.getBasicRemote().sendText(username + ": " + message);
            }
        }
    }
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        sessions.remove(session);
    }
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println(error);
    }
}
