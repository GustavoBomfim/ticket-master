package br.com.ticket.master.application.port.in;

import br.com.ticket.master.application.port.in.command.CreateAddressCommand;
import br.com.ticket.master.domain.model.AddressDomain;

public interface CreateAddressUseCase {
    AddressDomain execute(CreateAddressCommand command);
}