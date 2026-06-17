package br.com.ticket.master.infraestructure.rest.exception;

import br.com.ticket.master.domain.exception.ResourceNotFoundException;
import br.com.ticket.master.infraestructure.rest.dto.response.error.ConstraintViolationProblem;
import br.com.ticket.master.infraestructure.rest.dto.response.error.ProblemDetail;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ServerExceptionMapper
    public Response handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.warn("ResourceNotFoundException capturada: {}", ex.getMessage());
        var problem = new ProblemDetail(
                "Recurso não encontrado",
                Response.Status.NOT_FOUND.getStatusCode(),
                ex.getMessage()
        );
        return Response.status(Response.Status.NOT_FOUND).entity(problem).build();
    }

    @ServerExceptionMapper
    public Response handleConstraintViolationException(ConstraintViolationException ex) {
        log.warn("ConstraintViolationException capturada. Violações: {}", ex.getConstraintViolations());

        List<ConstraintViolationProblem.Violation> violations = ex.getConstraintViolations().stream()
                .map(violation -> new ConstraintViolationProblem.Violation(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()
                ))
                .collect(Collectors.toList());

        var problem = new ConstraintViolationProblem(
                "about:blank",
                "Um ou mais campos são inválidos",
                Response.Status.BAD_REQUEST.getStatusCode(),
                "A requisição contém dados inválidos.",
                violations
        );

        return Response.status(Response.Status.BAD_REQUEST).entity(problem).build();
    }

    @ServerExceptionMapper
    public Response handleGenericException(Exception ex) {
        log.error("Exceção não tratada capturada: {}", ex.getMessage(), ex);
        var problem = new ProblemDetail(
                "Erro interno do servidor",
                Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                "Ocorreu um erro inesperado. Tente novamente mais tarde."
        );
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(problem).build();
    }
}