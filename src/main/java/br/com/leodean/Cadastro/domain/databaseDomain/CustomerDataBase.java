package br.com.leodean.Cadastro.domain.databaseDomain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "TB_Customer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDataBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Cutomer_id", nullable = false)
    private String customerID;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Cell", nullable = false)
    private String cell;

    @Column(name = "Email", nullable = false)
    private String email;
}
