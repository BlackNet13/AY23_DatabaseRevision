package com.myapplicationdev.databaserevision;

import java.io.Serializable;

public class Note implements Serializable {
    private int id;
    private String content;
    private int priority;

    public Note(int id, String content, int priority) {
        this.id = id;
        this.content = content;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPriority() {
        return priority;
    }

    public void setNote(String content, int priority){
        this.content = content;
        this.priority = priority;
    }

    public String toString(){
        return String.format("id: %d \tcontent: %s \tpriority: %s", id, content, priority);
    }
}
