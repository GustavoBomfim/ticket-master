package br.com.ticket.master.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class AddressDomain {

    private UUID id;
    private String street;
    private String number; // Adicionado
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AddressDomain(UUID id, String street, String number, String city, String state, String country, String zipcode,
                          LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static AddressDomain create(String street, String number, String city, String state, String country, String zipcode) {
        if (street == null || street.isBlank()) throw new IllegalArgumentException("Street is required");
        if (number == null || number.isBlank()) throw new IllegalArgumentException("Number is required");
        if (city == null || city.isBlank()) throw new IllegalArgumentException("City is required");
        if (zipcode == null || zipcode.isBlank()) throw new IllegalArgumentException("Zipcode is required");
        
        return new AddressDomain(null, street, number, city, state, country, zipcode, LocalDateTime.now(), null);
    }

    public static AddressDomain restore(UUID id, String street, String number, String city, String state, String country, String zipcode,
                                        LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new AddressDomain(id, street, number, city, state, country, zipcode, createdAt, updatedAt);
    }

    public void update(String street, String number, String city, String state, String country, String zipcode) {
        if (street != null && !street.isBlank()) this.street = street;
        if (number != null && !number.isBlank()) this.number = number;
        if (city != null && !city.isBlank()) this.city = city;
        if (state != null && !state.isBlank()) this.state = state;
        if (country != null && !country.isBlank()) this.country = country;
        if (zipcode != null && !zipcode.isBlank()) this.zipcode = zipcode;
    }
}