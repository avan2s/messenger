package org.andy.javabrains.messenger.model;

import java.util.Date;

/**
 * Created by Andy on 30.01.2017.
 */
public class Comment {
    private long id;
    private String message;
    private Date created;
    private String author;

    public Comment() {
    }

    public Comment(String message, Date created, String author) {
        this.message = message;
        this.created = created;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
