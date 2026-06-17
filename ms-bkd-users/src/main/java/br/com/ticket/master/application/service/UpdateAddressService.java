package br.com.ticket.master.application.service;

import br.com.ticket.master.application.port.in.UpdateAddressUseCase;
import br.com.ticket.master.application.port.in.command.UpdateAddressCommand;
import br.com.ticket.master.application.port.out.AddressRepositoryPort;
import br.com.ticket.master.domain.exception.ResourceNotFoundException;
import br.com.ticket.master.domain.model.AddressDomain;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class UpdateAddressService implements UpdateAddressUseCase {

    private static final Logger log = LoggerFactory.getLogger(UpdateAddressService.class);
    private final AddressRepositoryPort addressRepository;

    public UpdateAddressService(AddressRepositoryPort addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressDomain execute(UpdateAddressCommand command) {
        log.info("Iniciando atualização para o endereço ID: {}", command.addressId());

        AddressDomain existingAddress = addressRepository.findById(command.addressId())
                .orElseThrow(() -> {
                    log.warn("Endereço com ID: {} não encontrado para atualização.", command.addressId());
                    return new ResourceNotFoundException("Address", command.addressId());
                });

        existingAddress.update(
                command.street(), command.number(), command.city(),
                command.state(), command.country(), command.zipcode()
        );

        AddressDomain updatedAddress = addressRepository.update(existingAddress);
        log.info("Endereço com ID: {} atualizado com sucesso.", updatedAddress.getId());
        return updatedAddress;
    }
}