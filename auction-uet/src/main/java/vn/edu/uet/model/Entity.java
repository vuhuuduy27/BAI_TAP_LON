package vn.edu.uet.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Entity implements Serializable {
    protected String id;
    protected LocalDateTime createdAt;

    public Entity() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public abstract void printInfo();
}