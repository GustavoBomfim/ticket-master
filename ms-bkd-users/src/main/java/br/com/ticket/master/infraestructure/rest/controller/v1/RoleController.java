package br.com.ticket.master.infraestructure.rest.controller.v1;

import br.com.ticket.master.application.port.in.FindRoleByIdUseCase;
import br.com.ticket.master.application.port.in.FindRoleUseCase;
import br.com.ticket.master.domain.model.RoleDomain;
import br.com.ticket.master.infraestructure.rest.controller.mapper.RoleRestMapper;
import br.com.ticket.master.infraestructure.rest.dto.response.RoleResponseDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/v1/roles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Role")
public class RoleController {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    private final FindRoleUseCase findRoleUseCase;
    private final FindRoleByIdUseCase findRoleByIdUseCase;

    public RoleController(FindRoleUseCase findRoleUseCase,FindRoleByIdUseCase findRoleByIdUseCase){
        this.findRoleUseCase = findRoleUseCase;
        this.findRoleByIdUseCase = findRoleByIdUseCase;
    }


    @GET
    @Operation(summary = "Buscar todas as roles.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Roles encontrada com sucesso."),
            @APIResponse(responseCode = "404", description = "Roles não encontrada")
    })
    public RestResponse<List<RoleResponseDTO>> findAll() {

        log.info("Recebido a requisição para buscar todas as roles.");

        List<RoleDomain> roleDomains = findRoleUseCase.execute();

        List<RoleResponseDTO> roleResponseDTOS = roleDomains.stream().map(RoleRestMapper::toResponse).toList();

        return RestResponse.ok(roleResponseDTOS);

    }


    @GET
    @Path("/{roleId}")
    @Operation(summary = "Buscar role por id.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Role encontrada com sucesso"),
            @APIResponse(responseCode = "404", description = "Role não encontrado"),
            @APIResponse(responseCode = "400", description = "Dados de requisição inválidos")
    })
    public RestResponse<RoleResponseDTO> findRoleById(
            @Parameter(description = "Id da role") @PathParam("roleId") Integer roleId) {

        log.info("Recebido a requisição para buscar role por id: {}", roleId);

        RoleDomain roleDomain = findRoleByIdUseCase.execute(roleId);

        return RestResponse.ok(RoleRestMapper.toResponse(roleDomain));

    }


}
