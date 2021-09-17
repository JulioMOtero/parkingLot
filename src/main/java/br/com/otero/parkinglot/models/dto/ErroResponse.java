package br.com.otero.parkinglot.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(staticName = "create")
public class ErroResponse {

    private HttpStatus status;
    private String message;

}
