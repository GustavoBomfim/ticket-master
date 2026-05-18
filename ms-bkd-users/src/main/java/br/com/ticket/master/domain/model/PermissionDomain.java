package br.com.ticket.master.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PermissionDomain {

    private Integer id;

    private String name;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private PermissionDomain(Integer id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PermissionDomain createPermission(String name, String description) {
        initialValidations(name, description);
        return new PermissionDomain(null, name, description, null, null);
    }

    private static void initialValidations(String name, String description) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        if(description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }
    }

}
