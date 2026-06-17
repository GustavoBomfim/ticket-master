package br.com.ticket.master.infraestructure.rest.controller.v1;

import br.com.ticket.master.application.port.in.CreateAddressUseCase;
import br.com.ticket.master.application.port.in.FindAddressUseCase;
import br.com.ticket.master.application.port.in.UpdateAddressUseCase;
import br.com.ticket.master.application.port.in.command.CreateAddressCommand;
import br.com.ticket.master.application.port.in.command.UpdateAddressCommand;
import br.com.ticket.master.domain.model.AddressDomain;
import br.com.ticket.master.infraestructure.rest.controller.mapper.AddressRestMapper;
import br.com.ticket.master.infraestructure.rest.dto.request.CreateAddressRequestDTO;
import br.com.ticket.master.infraestructure.rest.dto.request.UpdateAddressRequestDTO;
import br.com.ticket.master.infraestructure.rest.dto.response.AddressResponseDTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Address")
public class AddressController {

    private static final Logger log = LoggerFactory.getLogger(AddressController.class);

    private final CreateAddressUseCase createAddressUseCase;
    private final FindAddressUseCase findAddressUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;

    public AddressController(CreateAddressUseCase createAddressUseCase, FindAddressUseCase findAddressUseCase, UpdateAddressUseCase updateAddressUseCase) {
        this.createAddressUseCase = createAddressUseCase;
        this.findAddressUseCase = findAddressUseCase;
        this.updateAddressUseCase = updateAddressUseCase;
    }

    @POST
    @Path("/users/{userId}/address")
    @Operation(summary = "Cadastrar um endereço para um usuário")
    @APIResponse(responseCode = "201", description = "Endereço criado com sucesso")
    @APIResponse(responseCode = "404", description = "Usuário não encontrado")
    public RestResponse<AddressResponseDTO> createAddress(
            @Parameter(description = "ID do usuário") @PathParam("userId") UUID userId,
            @Valid CreateAddressRequestDTO request) {

        log.info("Recebida requisição para criar endereço para o usuário ID: {}", userId);
        var command = new CreateAddressCommand(userId, request.street(), request.number(), request.city(), request.state(), request.country(), request.zipcode());
        AddressDomain addressDomain = createAddressUseCase.execute(command);
        return RestResponse.status(Response.Status.CREATED, AddressRestMapper.toResponse(addressDomain));
    }

    @GET
    @Path("/addresses/{addressId}")
    @Operation(summary = "Buscar um endereço por ID")
    @APIResponse(responseCode = "200", description = "Endereço encontrado")
    @APIResponse(responseCode = "404", description = "Endereço não encontrado")
    public RestResponse<AddressResponseDTO> findAddressById(
            @Parameter(description = "ID do endereço") @PathParam("addressId") UUID addressId) {

        log.info("Recebida requisição para buscar endereço com ID: {}", addressId);
        AddressDomain addressDomain = findAddressUseCase.execute(addressId);
        return RestResponse.ok(AddressRestMapper.toResponse(addressDomain));
    }

    @PATCH
    @Path("/addresses/{addressId}")
    @Operation(summary = "Atualizar parcialmente um endereço")
    @APIResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @APIResponse(responseCode = "404", description = "Endereço não encontrado")
    public RestResponse<AddressResponseDTO> updateAddress(
            @Parameter(description = "ID do endereço") @PathParam("addressId") UUID addressId,
            @Valid UpdateAddressRequestDTO request) {

        log.info("Recebida requisição para atualizar endereço com ID: {}", addressId);
        var command = new UpdateAddressCommand(addressId, request.street(), request.number(), request.city(), request.state(), request.country(), request.zipcode());
        AddressDomain updatedAddress = updateAddressUseCase.execute(command);
        return RestResponse.ok(AddressRestMapper.toResponse(updatedAddress));
    }
}