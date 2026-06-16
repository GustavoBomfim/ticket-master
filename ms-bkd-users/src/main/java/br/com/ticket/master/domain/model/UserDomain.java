package br.com.ticket.master.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
public class UserDomain {

    private UUID id;

    private String name;

    private String email;

    private String password;

    private AddressDomain address;

    private Set<RoleDomain> role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;



    private UserDomain(UUID id, String name, String email, String password, AddressDomain address,  Set<RoleDomain> role,
                       LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static UserDomain createUser(String name, String email, String password) {
        initialValidations(name, email, password);
        return new UserDomain(null, name, email, password, null, null, null,
                null, null);
    }

    private static void initialValidations(String name, String email, String password) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
    }

    public void updateName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void updateEmail(String email) {
        if (email != null && !email.trim().isEmpty()) {
            this.email = email;
        }
    }

    public static UserDomain restore(UUID id, String name, String email, String password, AddressDomain address,
                                     Set<RoleDomain> role, LocalDateTime createdAt, LocalDateTime updatedAt,
                                     LocalDateTime deletedAt){
        return new UserDomain(id, name, email , password, address, role, createdAt, updatedAt, deletedAt);
    }

}
