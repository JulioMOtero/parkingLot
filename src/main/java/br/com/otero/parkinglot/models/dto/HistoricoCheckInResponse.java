package br.com.otero.parkinglot.models.dto;


import lombok.Data;

@Data
public class HistoricoCheckInResponse {

    private Long id;
    private String periodo;
    private Boolean pago;
    private Boolean checkout;


    @Override
    public String toString() {
        return "ChecInResponse{" +
                "id:" + this.id +
                ", periodo:'" + this.periodo + '\'' +
                ", pago:" + this.pago +
                ", checkout:" + this.checkout +
                '}';
    }
}
