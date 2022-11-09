package com.example.zad3_4;

import java.util.UUID;
import java.util.Date;

public class Task {
    private UUID id;
    private String name;
    private Date date;
    private Boolean done;
    private Category category;

    public Task() {
        this.id = UUID.randomUUID();
        this.date = new Date();
        this.category = Category.HOME;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public Date getDate() {
        return date;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean newDone) {
        done = newDone;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setDate(Date time) {
        date = time;
    }

}