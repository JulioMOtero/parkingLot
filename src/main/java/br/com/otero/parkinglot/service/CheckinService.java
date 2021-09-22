package br.com.otero.parkinglot.service;

import br.com.otero.parkinglot.exception.BusinessException;
import br.com.otero.parkinglot.exception.NotFoundException;
import br.com.otero.parkinglot.models.Carro;
import br.com.otero.parkinglot.models.CheckIn;
import br.com.otero.parkinglot.models.dto.CheckinRequest;
import br.com.otero.parkinglot.models.dto.CheckinResponse;
import br.com.otero.parkinglot.models.dto.HistoricoCheckInResponse;
import br.com.otero.parkinglot.repository.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CheckinService {

    @Autowired
    CheckinRepository checkinRepository;


    public List<HistoricoCheckInResponse> obterhistorico(Date dataEntrada, Date dataSaida) {
        this.validacaoDataEntradaSaida(dataEntrada, dataSaida);
        List<CheckIn> checkIns = this.checkinRepository.FindByPeriodo(dataEntrada, dataSaida);
        return checkIns.stream().map(checkIn -> {
            String tempo = (((checkIn.getDataSaida().getTime() - checkIn.getDataEntrada().getTime()) / 1000) / 60) + " minutos";

            return HistoricoCheckInResponse.builder()
                    .id(checkIn.getId())
                    .periodo(tempo)
                    .pago(checkIn.getPagamento())
                    .checkout(checkIn.getDataSaida() != null)
                    .build();
        }).collect(Collectors.toList());

    }

    protected void validacaoDataEntradaSaida(Date dataEntrda, Date dataSaida) {
        if (dataEntrda == null || dataSaida == null) {
            throw new IllegalArgumentException("data de entrada e saida devem ser preencidas");

        }
        if (dataEntrda.getTime() > dataSaida.getTime()) {
            throw new IllegalArgumentException("data de entrada nao pode ser maior que a de saida");
        }
    }


    public CheckinResponse newCheckin(CheckinRequest newCheckin) {
        Carro carro = Carro.builder()
                .brand(newCheckin.getBrand())
                .model(newCheckin.getModel())
                .plate(newCheckin.getPlate()).build();

        CheckIn checkIn = CheckIn.builder()
                .dataEntrada(new Date())
                .carro(carro)
                .pagamento(false)
                .build();


        if (newCheckin.getPlate() == null) {
            throw new BusinessException("Placa invalida");
        }
        if (newCheckin.getPlate().length() != 7) {
            throw new BusinessException("Placa deve conter 7 digitos");
        }
        if (newCheckin.getModel().isEmpty() || newCheckin.getModel().isBlank()) {
            throw new BusinessException("Modelo invalido");
        }
        if (newCheckin.getBrand().isEmpty() || newCheckin.getBrand().isBlank()) {
            throw new BusinessException("Marca invalida");
        }


        this.checkinRepository.save(checkIn);

        return CheckinResponse.builder()
                .id(checkIn.getId())
                .build();

    }

    public void checkOut(Long id) {
        CheckIn checkout = this.checkinRepository.findById(id).orElseThrow(() ->
                new NotFoundException("id: " + id + " Não encontrado"));

        if (!checkout.getPagamento()) {
            throw new BusinessException("Pagamento nao realizado");
        }
        if (checkout.getDataSaida() != null) {
            throw new BusinessException("checkout ja realizado");
        }

        checkout.setDataSaida(new Date());

        this.checkinRepository.save(checkout);
    }

    public void pagamento(Long id) {
        CheckIn checkout = this.checkinRepository.findById(id).orElseThrow(() -> new NotFoundException("id: " + id + " Não encontrado"));

        if (checkout.getPagamento()) {
            throw new BusinessException("Pagamento já realizado");
        }
        checkout.setPagamento(true);

        this.checkinRepository.save(checkout);
    }

//    public String data() {
//        return (new Date().getTime() / 1000 / 60) + "minutos";
////         String tempo = (((checkIn.getDataSaida().getTime() - checkIn.getDataEntrada().getTime()) / 1000) / 60) + " minutos";
//    }

}
