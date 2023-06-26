package br.com.leodean.Cadastro.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class HandlerCustomException extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public final ResponseEntity<ExceptionDomain> handleApiExceptionApiCadastro(ExceptionApiCadastro ex, WebRequest request) {
        ExceptionDomain error = new ExceptionDomain(ex.getCodigoErro(), ex.getMsgCustom(), ex.getMessage());
        error.setCampos(ex.getObjetosSaida());
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorRequest> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            ErrorRequest errorRequest = ErrorRequest.builder()
                    .defaultMessage(error.getDefaultMessage())
                    .field(((FieldError) error).getField())
                    .build();
            errors.add(errorRequest);
        });


        ExceptionDomain problem = ExceptionDomain.builder()
                .dataHora(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(errors)
                .build();

        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);
    }
}
