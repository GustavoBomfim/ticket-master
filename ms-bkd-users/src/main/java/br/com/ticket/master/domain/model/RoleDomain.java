package br.com.ticket.master.domain.model;

import java.time.LocalDateTime;

public class RoleDomain {

    private Integer id;

    private String name;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private RoleDomain(Integer id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
