package br.com.compass.cais.entites;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "SHIP")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @NotBlank
    @Column(name = "WEIGHT")
    private Double weight;

    @OneToOne
    @NotNull
    private Company companyId;

    @OneToOne
    private Pier pierId;
}
