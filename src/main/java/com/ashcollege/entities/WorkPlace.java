package com.ashcollege.entities;

public class WorkPlace {
    private long id;
    private String name;
    private int since;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSince() {
        return since;
    }

    public void setSince(int since) {
        this.since = since;
    }
}
