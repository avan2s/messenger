package org.andy.javabrains.messenger.model;

/**
 * Created by Andy on 31.01.2017.
 */
public class Link {
    private String link;
    private String rel;

    public Link() {
    }

    public Link(String link, String rel) {
        this.link = link;
        this.rel = rel;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
