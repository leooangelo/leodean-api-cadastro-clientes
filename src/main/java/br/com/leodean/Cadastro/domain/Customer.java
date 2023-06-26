package br.com.leodean.Cadastro.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Valid
public class Customer {

    @JsonProperty("id_cliente")
    private String customerID;

    @NotBlank(message = "Nome nao pode estar vazio")
    @NotNull(message = "Nome é obrigatorio")
    @JsonProperty("nome")
    private String name;

    @NotBlank(message = "Telefone nao pode estar vazio")
    @NotNull(message = "Telefone é obrigatorio")
    @JsonProperty("telefone")
    private String cell;

    @NotBlank(message = "Email nao pode estar vazio")
    @NotNull(message = "Email é obrigatorio")
    @JsonProperty("email")
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("carros")
    private List<Car> carsList;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("enderecos")
    private List<Address> addressList;
}
