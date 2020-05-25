package com.main;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ChatServerTest {

    // dla kazdego testu instancja chatServer tworzy sie na nowo
    private ChatServer chatServer = new ChatServer();

    @Test
    public void shouldSubscribeUsers() {
        //when
        chatServer.subscribe("Marek");
        chatServer.subscribe("Jarek");
        ChatData data = chatServer.refresh();
        //then
        assertThat(data.getUsers().size(), is(2));
    }

    @Test
    public void shouldReturnFalseIfUsernameIsAlreadyTaken() {
        //when
        chatServer.subscribe("Marek");
        boolean result = chatServer.subscribe("Marek");
        ChatData data = chatServer.refresh();
        //then
        assertFalse(result);
        assertThat(data.getUsers().size(), is(1));
    }

//    @Test
//    public void shouldStoreMessagesInSendingOrder() {
//        //when
//        chatServer.sendMessage(new Message("Jarek", "siema"));
//        chatServer.sendMessage(new Message("Marek", "cześć"));
//        chatServer.sendMessage(new Message("Marek", "aaaa"));
//        ChatData chatData = chatServer.sendMessage(new Message("Jarek", "nara"));
//        //then
//        Iterator<Message> msgIterator = chatData.getMessages().iterator();
//        assertThat(msgIterator.next().getMessageText(), is("siema"));
//        assertThat(msgIterator.next().getMessageText(), is("cześć"));
//        assertThat(msgIterator.next().getMessageText(), is("aaaa"));
//        assertThat(msgIterator.next().getMessageText(), is("nara"));
//    }
}
