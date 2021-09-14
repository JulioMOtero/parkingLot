package br.com.otero.parkinglot.service;

import br.com.otero.parkinglot.models.Carro;
import br.com.otero.parkinglot.models.CheckIn;
import br.com.otero.parkinglot.models.dto.CheckinRequest;
import br.com.otero.parkinglot.models.dto.HistoricoCheckInResponse;
import br.com.otero.parkinglot.repository.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class CheckinService {

    @Autowired
    CheckinRepository checkinRepository;


    public List<CheckIn> obterhistorico(Date dataEntrada, Date dataSaida) {

        List<CheckIn> checkIns = this.checkinRepository.FindByPeriodo(dataEntrada, dataSaida);
        checkIns.stream().map(checkIn -> {

            String tempo = Duration.ofMinutes(dataSaida.getTime() - dataEntrada.getTime()).toString();
            System.out.println(tempo);

            HistoricoCheckInResponse histoResponse = HistoricoCheckInResponse.builder()
                    .periodo(tempo).build();
            return histoResponse.toString();
        });
        return checkIns;

    }

    public CheckIn newCheckin(CheckinRequest newCheckin) {
        Carro carro = Carro.builder()
                .brand(newCheckin.getBrand())
                .model(newCheckin.getModel())
                .plate(newCheckin.getPlate()).build();

        CheckIn checkIn = CheckIn.builder()
                .dataEntrada(new Date())
                .carro(carro)
                .pagamento(false)
                .build();
        this.checkinRepository.save(checkIn);
        return checkIn;
    }

    public void checkOut(Long id) {
        Optional<CheckIn> checkout = this.checkinRepository.findById(id);
        if (checkout.get().getPagamento() == false) {
            throw new RuntimeException("Pagamento não realizado, pagar por favor!");
        }

        checkout.get().setDataSaida(new Date());

        this.checkinRepository.save(checkout.get());
    }

    public void pagamento(Long id) {
        Optional<CheckIn> checkout = this.checkinRepository.findById(id);

        if (checkout.isEmpty()) {
            throw new RuntimeException("CheckIn inexistente");
        }

        if (checkout.get().getPagamento()) {
            throw new RuntimeException("Pagamento já realizado");

        }
        checkout.get().setPagamento(true);

        this.checkinRepository.save(checkout.get());
    }
}
