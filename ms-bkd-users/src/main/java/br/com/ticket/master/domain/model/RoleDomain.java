package br.com.ticket.master.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class RoleDomain {

    private UUID id;

    private String name;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private RoleDomain(UUID id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static RoleDomain createRole(String name, String description) {
        return new RoleDomain(null, name, description, null, null);
    }

}
