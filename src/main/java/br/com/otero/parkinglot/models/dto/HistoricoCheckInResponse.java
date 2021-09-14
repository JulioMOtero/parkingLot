package br.com.otero.parkinglot.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoCheckInResponse {

    private Long id;
    private String periodo;
    private Boolean pago;
    private Boolean checkout;


    @Override
    public String toString() {
        return "Historico{" +
                "id:" + this.id +
                ", periodo:'" + this.periodo + " Minutos" + '\'' +
                ", pago:" + this.pago +
                ", checkout:" + this.checkout +
                '}';
    }
}
