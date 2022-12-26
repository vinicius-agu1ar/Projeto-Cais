package br.com.compass.cais.entites;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "SHIP")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEIGHT")
    private Double weight;

    @OneToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @OneToOne
    @JoinColumn(name = "PIER_ID")
    private Pier pier;
}
