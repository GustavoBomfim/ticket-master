package br.com.ticket.master.application.port.in.command;

import java.util.UUID;

public record UpdateUserCommand(
        UUID id,
        String name,
        String email
) {
}
