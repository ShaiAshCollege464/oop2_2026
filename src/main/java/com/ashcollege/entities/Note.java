package com.ashcollege.entities;

public class Note {
    private long id;
    private String text;
    private User writer;

    public Note () {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Note (String text, User writer) {
        this.text = text;
        this.writer = writer;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }
}
