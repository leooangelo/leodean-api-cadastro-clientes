package br.com.leodean.Cadastro.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Id;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {

    @Id
    @JsonProperty("adress_id")
    private String adress_id;

    @JsonProperty("estado")
    private String state;

    @JsonProperty("cidade")
    private String city;

    @JsonProperty("cep")
    private String zipCode;

    @JsonProperty("logradouro")
    private String streetName;

    @JsonProperty("numero")
    private String number;

    @JsonProperty("complemento")
    private String complement;

    @JsonProperty("customer")
    private CustomerDTO customer;

}
