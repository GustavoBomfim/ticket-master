package br.com.ticket.master.application.service;

import br.com.ticket.master.application.port.in.FindAddressUseCase;
import br.com.ticket.master.application.port.out.AddressRepositoryPort;
import br.com.ticket.master.domain.exception.ResourceNotFoundException;
import br.com.ticket.master.domain.model.AddressDomain;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@ApplicationScoped
public class FindAddressService implements FindAddressUseCase {

    private static final Logger log = LoggerFactory.getLogger(FindAddressService.class);
    private final AddressRepositoryPort addressRepository;

    public FindAddressService(AddressRepositoryPort addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressDomain execute(UUID addressId) {
        log.info("Buscando endereço com ID: {}", addressId);
        return addressRepository.findById(addressId)
                .orElseThrow(() -> {
                    log.warn("Endereço com ID: {} não encontrado.", addressId);
                    return new ResourceNotFoundException("Address", addressId);
                });
    }
}