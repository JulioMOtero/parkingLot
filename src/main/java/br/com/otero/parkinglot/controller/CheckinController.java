package br.com.otero.parkinglot.controller;

import br.com.otero.parkinglot.models.CheckIn;
import br.com.otero.parkinglot.models.dto.CheckinRequest;
import br.com.otero.parkinglot.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class CheckinController {

    @Autowired
    private CheckinService checkinService;


    @GetMapping("/historico")
    public List<CheckIn> historico(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataEntrada,
                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataSaida) {
        return this.checkinService.obterhistorico(dataEntrada, dataSaida);

    }

    @Transactional
    @PostMapping("/checkin")
    CheckIn newCheckin(@RequestBody CheckinRequest newCheckin) {
        return this.checkinService.newCheckin(newCheckin);
    }

    @Transactional
    @PutMapping("/{id}/pay")
    void pagamento(final @PathVariable Long id) {
        this.checkinService.pagamento(id);
    }


    @Transactional
    @PutMapping("/{id}/checkout")
    void checkOut(final @PathVariable Long id) {
        this.checkinService.checkOut(id);
    }
}/*


  }*/