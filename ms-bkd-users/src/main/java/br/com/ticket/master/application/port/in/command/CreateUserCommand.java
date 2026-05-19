package br.com.ticket.master.application.port.in.command;

public record CreateUserCommand (
        String name,
        String email,
        String password

){
}
