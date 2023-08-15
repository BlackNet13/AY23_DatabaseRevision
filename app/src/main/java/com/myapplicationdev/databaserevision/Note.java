package com.myapplicationdev.databaserevision;

public class Note {
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

    public String toString(){
        return String.format("id: %d \tcontent: %s \tpriority: %s", id, content, priority);
    }
}
