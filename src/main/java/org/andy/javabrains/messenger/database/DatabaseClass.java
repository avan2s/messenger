package org.andy.javabrains.messenger.database;

import org.andy.javabrains.messenger.model.Message;
import org.andy.javabrains.messenger.model.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andy on 26.01.2017.
 * Only a stub for the database
 */
public class DatabaseClass {

    private static Map<String, Profile> profiles;
    private static Map<Long, Message> messages;

    static {
        profiles = new HashMap<>();
        profiles.put("andy", new Profile(1L, "andy", "Andreas", "H"));
        messages = new HashMap<>();
        messages.put(1L, new Message(1, "Hello World!", "andy"));
        messages.put(2L, new Message(2, "Hello Jersey", "andy"));
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

    public static void setProfiles(Map<String, Profile> profiles) {
        DatabaseClass.profiles = profiles;
    }

    /**
     * @return
     */
    public static Map<Long, Message> getMessages() {
        return messages;
    }

    /**
     * @param messages
     */
    public static void setMessages(Map<Long, Message> messages) {
        DatabaseClass.messages = messages;
    }
}
