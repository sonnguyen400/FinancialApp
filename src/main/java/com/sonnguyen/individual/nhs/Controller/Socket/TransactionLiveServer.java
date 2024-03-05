package com.sonnguyen.individual.nhs.Controller.Socket;



import org.jboss.logging.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/transaction")
public class TransactionLiveServer {
    private final Logger logger =Logger.getLogger(this.getClass().getName());
    @OnOpen
    public void onOpen(Session session) {
        logger.info("Session start "+session.getId());
    }
    @OnMessage
    public String onMessage(String message, Session session) {
        System.out.println("Message received: " + message);
        return message;
    }
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }

}
