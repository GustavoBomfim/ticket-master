package br.com.ticket.master.application.service;

import br.com.ticket.master.application.port.in.CreateAddressUseCase;
import br.com.ticket.master.application.port.in.command.CreateAddressCommand;
import br.com.ticket.master.application.port.out.AddressRepositoryPort;
import br.com.ticket.master.domain.model.AddressDomain;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class CreateAddressService implements CreateAddressUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateAddressService.class);
    private final AddressRepositoryPort addressRepository;

    public CreateAddressService(AddressRepositoryPort addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressDomain execute(CreateAddressCommand command) {
        log.info("Iniciando criação de endereço para o usuário ID: {}", command.userId());

        AddressDomain addressDomain = AddressDomain.create(
                command.street(), command.number(), command.city(),
                command.state(), command.country(), command.zipcode()
        );

        AddressDomain savedAddress = addressRepository.save(addressDomain, command.userId());
        log.info("Endereço criado com sucesso com ID: {}", savedAddress.getId());
        return savedAddress;
    }
}