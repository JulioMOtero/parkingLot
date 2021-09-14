package br.com.otero.parkinglot.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Carro {

    @Id
    private String plate;
    private String brand;
    private String model;


}
