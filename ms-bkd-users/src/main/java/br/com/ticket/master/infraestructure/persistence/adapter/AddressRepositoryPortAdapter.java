package br.com.ticket.master.infraestructure.persistence.adapter;

import br.com.ticket.master.application.port.out.AddressRepositoryPort;
import br.com.ticket.master.infraestructure.persistence.repository.AddressRepositoryPanache;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class AddressRepositoryPortAdapter implements AddressRepositoryPort {

    private final AddressRepositoryPanache addressRepositoryPanache;

    public AddressRepositoryPortAdapter(AddressRepositoryPanache addressRepositoryPanache) {
        this.addressRepositoryPanache = addressRepositoryPanache;
    }

}
