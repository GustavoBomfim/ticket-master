package br.com.ticket.master.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class AddressDomain {

    private UUID id;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipcode;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    private AddressDomain (UUID id, String street, String city, String state, String country, String zipcode,
                           LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AddressDomain createAddress(){
        initialValidations(street, city, state, country, zipcode);
        return new AddressDomain(null, street, city, state, country, zipcode, null, null);
    }

    private void initialValidations(String street, String city, String state, String country, String zipcode){
        if(street == null || street.isEmpty()){
            throw new IllegalArgumentException("Street is required");
        }

        if(city == null || city.isEmpty()){
            throw new IllegalArgumentException("City is required");
        }

        if(state == null || state.isEmpty()){
            throw new IllegalArgumentException("State is required");
        }

        if(country == null || country.isEmpty()){
            throw new IllegalArgumentException("Country is required");
        }

        if(zipcode == null || zipcode.isEmpty()){
            throw new IllegalArgumentException("Zipcode is required");
        }
    }


}
