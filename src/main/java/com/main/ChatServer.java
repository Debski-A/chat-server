package com.main;

import com.caucho.hessian.server.HessianServlet;

import java.time.LocalTime;

public class ChatServer extends HessianServlet implements ChatServerApi {

    private ChatDataSingleton chatDataSingleton; // zawiera informacje potrzebne dla chat-client

    public ChatServer() {
        chatDataSingleton = ChatDataSingleton.ChatData();
    }

    @Override
    public ChatDataSingleton refresh() {
        return chatDataSingleton;
    } // wywolywana interwalowo przez klienta, dostarcza danych dla chatu

    @Override
    public synchronized boolean subscribe(String username) { // logowanie uzytkownika, jesli ok - zwraca true
        if (chatDataSingleton.getUsers().contains(username)) {       // jesli nazwa zajeta - zwraca false
            return false;
        }
        chatDataSingleton.getUsers().add(username);
        return true;
    }

    @Override
    public synchronized void sendMessage(Message message) {
        message.setMessageTimestamp(LocalTime.now()); // ustawia czas wyslania wiadomosci
        chatDataSingleton.getMessages().add(message);
    }

    @Override
    public synchronized void userLeft(String username) {
        chatDataSingleton.getUsers().remove(username);
    }

}
