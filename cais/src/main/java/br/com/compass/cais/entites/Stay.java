package br.com.compass.cais.entites;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "STAY")
public class Stay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SHIP_ID")
    private Ship ship;

    @Column(name = "ENTRY")
    private LocalDateTime entry;

    @Column(name = "EXIT")
    private LocalDateTime exit;

    @Column(name = "VALUE")
    private BigDecimal value;
}
