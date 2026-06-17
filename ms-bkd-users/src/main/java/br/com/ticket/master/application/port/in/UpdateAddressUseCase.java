package br.com.ticket.master.application.port.in;

import br.com.ticket.master.application.port.in.command.UpdateAddressCommand;
import br.com.ticket.master.domain.model.AddressDomain;

public interface UpdateAddressUseCase {
    AddressDomain execute(UpdateAddressCommand command);
}