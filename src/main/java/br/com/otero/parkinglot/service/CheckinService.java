package br.com.otero.parkinglot.service;

import br.com.otero.parkinglot.models.Carro;
import br.com.otero.parkinglot.models.CheckIn;
import br.com.otero.parkinglot.models.dto.CheckinRequest;
import br.com.otero.parkinglot.repository.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class CheckinService {

    @Autowired
    CheckinRepository checkinRepository;


    public List<CheckIn> obterhistorico(Date dataEntrada, Date dataSaida) {

        return this.checkinRepository.FindByPeriodo(dataEntrada, dataSaida);

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

    public CheckIn checkOut(CheckinRequest updateCheckin) {
        CheckIn checkIn = CheckIn.builder()
                .dataSaida(new Date())
                .build();
        return checkIn;
    }

    public CheckIn pagamento(Long id) {
        Optional<CheckIn> checkout = this.checkinRepository.findById(id);

        if (checkout.get().getPagamento() == false) {
            checkout.get().setPagamento(true);
        }
        if (checkout.get().getPagamento() == true) {
            System.out.println("pagamento já realizado");

        }

        return null;
    }
}
