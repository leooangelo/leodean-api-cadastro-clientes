package br.com.leodean.Cadastro.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

    @JsonProperty("id_cliente")
    private String customerID;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("telefone")
    private String cell;

    @JsonProperty("email")
    private String email;

    @JsonProperty("carros")
    private List<String> carsList;

    @JsonProperty("enderecos")
    private List<String> addressList;

}
