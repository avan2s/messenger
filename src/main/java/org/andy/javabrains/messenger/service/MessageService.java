package org.andy.javabrains.messenger.service;

import org.andy.javabrains.messenger.database.DatabaseClass;
import org.andy.javabrains.messenger.exception.DataNotFoundException;
import org.andy.javabrains.messenger.model.Message;

import java.util.*;


/**
 * Created by Andy on 26.01.2017.
 * Stub for DAO-Layer
 */
public class MessageService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService() {
    }

    public List<Message> getAllMessages() {
        return new ArrayList<Message>(this.messages.values());
    }

    public Message getMessage(long id) {
        Message message = this.messages.get(id);
        if (message == null) {
            throw new DataNotFoundException("Message with id " + id + " not found!");
        }
        return message;
    }

    public List<Message> getAllMessagesForYear(int year) {
        List<Message> messagesForYear = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Collection<Message> allMessages = this.messages.values();
        for (Message message : allMessages) {
            calendar.setTime(message.getCreated());
            if (calendar.get(Calendar.YEAR) == year) {
                messagesForYear.add(message);
            }
        }
        return messagesForYear;
    }

    public List<Message> getAllMessagesPaginated(int startIndex, int size) {
        List<Message> paginatedList = new ArrayList<Message>(this.messages.values());
        int toIndex = startIndex + size;

        if (toIndex > paginatedList.size()) {
            toIndex = paginatedList.size();
        }
        return paginatedList.subList(startIndex, toIndex);
    }

    public Message addMessage(Message message) {
        int newId = this.messages.size() + 1;
        message.setId(newId);
        this.messages.put(message.getId(), message);
        return message;
    }


    public Message updateMessage(Message message) {
        boolean invalidId = message.getId() <= 0 || !this.messages.containsKey(message.getId());
        if (invalidId) {
            return null;
        }
        this.messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(long id) {
        return this.messages.remove(id);
    }
}
