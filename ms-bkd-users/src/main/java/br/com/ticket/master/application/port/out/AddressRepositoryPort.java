package br.com.ticket.master.application.port.out;

import br.com.ticket.master.domain.model.AddressDomain;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepositoryPort {
    AddressDomain save(AddressDomain address, UUID userId);
    Optional<AddressDomain> findById(UUID id);
    AddressDomain update(AddressDomain address);
}