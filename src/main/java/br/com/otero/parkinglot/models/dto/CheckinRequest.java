package br.com.otero.parkinglot.models.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CheckinRequest {

    @NotNull
    @NotEmpty
    private String brand;

    @NotNull
    @NotEmpty
    private String model;

    @NotNull
    @NotEmpty
    private String plate;


    private Boolean pagamento;

}
