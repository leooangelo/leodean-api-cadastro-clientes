package br.com.leodean.Cadastro.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionDomain {

    @JsonProperty("status")
    private HttpStatus httpStatus;
    @JsonProperty("data_hora_erro")
    private String dataHora;
    @JsonProperty("codigo")
    private String codigo;
    @JsonProperty("mensagem")
    private String mensagem;
    @JsonProperty("mensagem_detalhada")
    private String mensagemDetalhada;
    @JsonProperty("erros")
    private List<ErrorRequest> error;
    @JsonProperty("campos")
    private List<?> campos = new ArrayList<>();

    public ExceptionDomain(String codigo, String mensagem, String mensagemDetalhada) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.mensagemDetalhada = mensagemDetalhada;
    }
}
