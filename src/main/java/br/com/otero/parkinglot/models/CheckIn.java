package br.com.otero.parkinglot.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Carro carro;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataEntrada;
    private Boolean pagamento = false;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataSaida;


    public CheckIn(Carro carro) {
    }


}
