package br.com.leodean.Cadastro.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {

    @JsonProperty("car_id")
    private String carID;

    @JsonProperty("construtor")
    private String constructor;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("modelo")
    private String model;

    @JsonProperty("customer")
    private CustomerDTO customer;
}
