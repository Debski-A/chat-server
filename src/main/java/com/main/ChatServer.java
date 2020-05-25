package com.main;

import com.caucho.hessian.server.HessianServlet;

import java.time.LocalTime;

public class ChatServer extends HessianServlet implements ChatServerApi {

    private ChatData chatData; // zawiera informacje potrzebne dla chat-client

    public ChatServer() {
        chatData = new ChatData();
    }

    @Override
    public ChatData refresh() {
        return chatData;
    } // wywolywana interwalowo przez klienta, dostarcza danych dla chatu

    @Override
    public synchronized boolean subscribe(String username) { // logowanie uzytkownika, jesli ok - zwraca true
        if (chatData.getUsers().contains(username)) {       // jesli nazwa zajeta - zwraca false
            return false;
        }
        chatData.getUsers().add(username);
        return true;
    }

    @Override
    public synchronized void sendMessage(Message message) {
        message.setMessageTimestamp(LocalTime.now()); // ustawia czas wyslania wiadomosci
        chatData.getMessages().add(message);
    }

    @Override
    public synchronized void userLeft(String username) {
        chatData.getUsers().remove(username);
    }

}
