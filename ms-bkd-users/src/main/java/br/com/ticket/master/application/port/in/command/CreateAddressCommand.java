package br.com.ticket.master.application.port.in.command;

import java.util.UUID;

public record CreateAddressCommand(
    UUID userId, String street, String number, String city, 
    String state, String country, String zipcode
) {}