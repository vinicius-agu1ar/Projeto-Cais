package br.com.compass.cais.entites;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "PIER")
public class Pier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

//    @NotBlank
    @Column(name = "NAME")
    private String name;

//    @NotNull
    @Column(name = "SPOTS")
    private Integer spots ;
}
