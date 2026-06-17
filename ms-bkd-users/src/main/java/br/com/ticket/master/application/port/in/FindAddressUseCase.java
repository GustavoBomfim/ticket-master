package br.com.ticket.master.application.port.in;

import br.com.ticket.master.domain.model.AddressDomain;
import java.util.UUID;

public interface FindAddressUseCase {
    AddressDomain execute(UUID addressId);
}