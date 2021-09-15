package br.com.otero.parkinglot.controller;

import br.com.otero.parkinglot.models.dto.CheckinRequest;
import br.com.otero.parkinglot.models.dto.CheckinResponse;
import br.com.otero.parkinglot.models.dto.HistoricoCheckInResponse;
import br.com.otero.parkinglot.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class CheckinController {

    @Autowired
    private CheckinService checkinService;


    @GetMapping("/historico")
    public ResponseEntity<List<HistoricoCheckInResponse>> historico(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataEntrada,
                                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataSaida) {
        return ResponseEntity.ok(this.checkinService.obterhistorico(dataEntrada, dataSaida));

    }

    @Transactional
    @PostMapping("/checkin")
    ResponseEntity<CheckinResponse> newCheckin(@RequestBody CheckinRequest newCheckin) {
        return ResponseEntity.created(URI.create("")).body(this.checkinService.newCheckin(newCheckin));
    }

    @Transactional
    @PutMapping("/{id}/pay")
    ResponseEntity pagamento(final @PathVariable Long id) {
        this.checkinService.pagamento(id);
        return ResponseEntity.ok().build();
    }


    @Transactional
    @PutMapping("/{id}/checkout")
    ResponseEntity checkOut(final @PathVariable Long id) {
        this.checkinService.checkOut(id);
        return ResponseEntity.ok().build();
    }
}/*


  }*/