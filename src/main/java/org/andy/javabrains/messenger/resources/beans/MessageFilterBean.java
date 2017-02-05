package org.andy.javabrains.messenger.resources.beans;

import javax.ws.rs.QueryParam;

/**
 * Created by Andy on 30.01.2017.
 */
public class MessageFilterBean {
    private
    @QueryParam("year")
    int year;
    private
    @QueryParam("start")
    int start;
    private
    @QueryParam("size")
    int size;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
