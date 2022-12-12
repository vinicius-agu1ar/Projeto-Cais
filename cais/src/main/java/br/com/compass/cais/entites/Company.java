package br.com.compass.cais.entites;

import br.com.compass.cais.enums.Origin;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "COMPANY")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "CNPJ")
    private String cnpj;

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "Origin")
    private Origin origin;
}
