package br.com.ticket.master.infraestructure.rest.controller.v1;

import br.com.ticket.master.application.port.in.CreateUserUseCase;
import br.com.ticket.master.application.port.in.FindUserUseCase;
import br.com.ticket.master.application.port.in.UpdateUserUseCase;
import br.com.ticket.master.application.port.in.command.CreateUserCommand;
import br.com.ticket.master.application.port.in.command.UpdateUserCommand;
import br.com.ticket.master.domain.model.UserDomain;
import br.com.ticket.master.infraestructure.rest.controller.mapper.UserRestMapper;
import br.com.ticket.master.infraestructure.rest.dto.request.CreateUserRequestDTO;
import br.com.ticket.master.infraestructure.rest.dto.request.UpdateUserRequestDTO;
import br.com.ticket.master.infraestructure.rest.dto.response.UserResponseDTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "User")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final CreateUserUseCase createUserUseCase;
    private final FindUserUseCase findUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase, FindUserUseCase findUserUseCase, UpdateUserUseCase updateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.findUserUseCase = findUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }

    @POST
    @Operation(summary = "Criar um novo usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @APIResponse(responseCode = "400", description = "Dados de requisição inválidos")
    })
    public RestResponse<UUID> createUser(@Valid CreateUserRequestDTO request) {
        log.info("Recebida requisição para criar usuário com email: {}", request.email());

        CreateUserCommand userCommand = UserRestMapper.createUserCommand(request);

        UserDomain userDomain = createUserUseCase.execute(userCommand);

        log.info("Usuário criado com sucesso com ID: {}", userDomain.getId());
        return RestResponse.status(Response.Status.CREATED, userDomain.getId());
    }


    @GET
    @Path("/{userId}")
    @Operation(summary = "Buscar um usuário por ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Usuário encontrado"),
            @APIResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public RestResponse<UserResponseDTO> findUser(@PathParam("userId") UUID userId){
        log.info("Recebida requisição para buscar usuário com ID: {}", userId);

        UserDomain userDomain = findUserUseCase.execute(userId);

        UserResponseDTO userResponseDTO = UserRestMapper.toUserResponseDTO(userDomain);

        log.info("Usuário com ID: {} encontrado.", userId);
        return RestResponse.status(Response.Status.OK, userResponseDTO);
    }

    @PATCH
    @Path("/{userId}")
    @Operation(summary = "Atualizar parcialmente os dados do usuário (nome ou email)")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @APIResponse(responseCode = "404", description = "Usuário não encontrado"),
            @APIResponse(responseCode = "400", description = "Dados de requisição inválidos")
    })
    public RestResponse<UserResponseDTO> updateUser(
            @PathParam("userId") UUID userId,
            @Valid UpdateUserRequestDTO request) {
        log.info("Recebida requisição para atualizar usuário com ID: {}", userId);

        UpdateUserCommand command = UserRestMapper.toUpdateUserCommand(userId, request);

        UserDomain updatedUser = updateUserUseCase.execute(command);

        UserResponseDTO responseDTO = UserRestMapper.toUserResponseDTO(updatedUser);

        log.info("Usuário com ID: {} atualizado com sucesso.", userId);
        return RestResponse.status(Response.Status.OK, responseDTO);
    }
}