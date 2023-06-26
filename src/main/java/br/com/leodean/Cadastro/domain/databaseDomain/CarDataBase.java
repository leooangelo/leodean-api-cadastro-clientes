package br.com.leodean.Cadastro.domain.databaseDomain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_CAR")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarDataBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "Car_id", nullable = false)
    @JsonProperty("car_id")
    private String carID;

    @JsonProperty("construtor")
    @Column(name = "Constructor", nullable = false)
    private String constructor;

    @JsonProperty("nome")
    @Column(name = "Name", nullable = false)
    private String name;

    @JsonProperty("modelo")
    @Column(name = "Model", nullable = false)
    private String model;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Cutomer_id")
    private CustomerDataBase customer;
}
