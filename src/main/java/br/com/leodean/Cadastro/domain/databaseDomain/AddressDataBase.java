package br.com.leodean.Cadastro.domain.databaseDomain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_Address")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDataBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Adress_id", nullable = false)
    private String addressID;

    @Column(name = "State", nullable = false)
    private String state;

    @Column(name = "City", nullable = false)
    private String city;

    @Column(name = "ZipCode", nullable = false)
    private String zipCode;

    @Column(name = "StreetName", nullable = false)
    private String streetName;

    @Column(name = "Number", nullable = false)
    private String number;

    @Column(name = "Complement")
    private String complement;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Cutomer_id")
    private CustomerDataBase customer;

}
