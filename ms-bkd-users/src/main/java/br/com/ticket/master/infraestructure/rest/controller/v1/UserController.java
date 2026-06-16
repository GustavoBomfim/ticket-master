package br.com.ticket.master.infraestructure.rest.controller.v1;

import br.com.ticket.master.application.port.in.CreateUserUseCase;
import br.com.ticket.master.application.port.in.FindUserUseCase;
import br.com.ticket.master.application.port.in.command.CreateUserCommand;
import br.com.ticket.master.domain.model.UserDomain;
import br.com.ticket.master.infraestructure.rest.controller.mapper.UserRestMapper;
import br.com.ticket.master.infraestructure.rest.dto.request.CreateUserRequestDTO;
import br.com.ticket.master.infraestructure.rest.dto.response.UserResponseDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.UUID;

@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "User")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final FindUserUseCase findUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase,  FindUserUseCase findUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.findUserUseCase = findUserUseCase;
    }

    @POST
    public RestResponse<UUID> createUser(CreateUserRequestDTO request) {

        CreateUserCommand userCommand = UserRestMapper.createUserCommand(request);

        UserDomain userDomain = createUserUseCase.execute(userCommand);

        return RestResponse.status(Response.Status.CREATED, userDomain.getId());
    }


    @GET
    @Path("/{userId}")
    public RestResponse<UserResponseDTO> findUser(@PathParam("userId") UUID userId){

        UserDomain userDomain = findUserUseCase.execute(userId);

        UserResponseDTO userResponseDTO = UserRestMapper.toUserResponseDTO(userDomain);

        return RestResponse.status(Response.Status.OK, userResponseDTO);
    }
}
