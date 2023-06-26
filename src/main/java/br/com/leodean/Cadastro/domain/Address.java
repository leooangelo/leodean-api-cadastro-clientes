package br.com.leodean.Cadastro.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

    @JsonProperty("id_endereco")
    private String adress_id;

    @NotBlank(message = "Estado nao pode estar vazio")
    @NotNull(message = "Estado é obrigatorio")
    @JsonProperty("estado")
    private String state;

    @NotBlank(message = "Cidade nao pode estar vazio")
    @NotNull(message = "Cidade é obrigatorio")
    @JsonProperty("cidade")
    private String city;

    @NotBlank(message = "Cep nao pode estar vazio")
    @NotNull(message = "Cep é obrigatorio")
    @JsonProperty("cep")
    private String zipCode;

    @NotBlank(message = "Logradouro nao pode estar vazio")
    @NotNull(message = "Logradouro é obrigatorio")
    @JsonProperty("logradouro")
    private String streetName;

    @NotBlank(message = "Numero nao pode estar vazio")
    @NotNull(message = "Numero é obrigatorio")
    @JsonProperty("numero")
    private String number;

    @JsonProperty("complemento")
    private String complement;

    @JsonProperty("customer")
    private Customer customer;

}
