package br.com.otero.parkinglot.repository;

import br.com.otero.parkinglot.models.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface CheckinRepository extends JpaRepository<CheckIn, Long> {

    @Query("SELECT c FROM CheckIn c " +
            "WHERE c.dataEntrada BETWEEN :dataEntrada AND :dataSaida")
    List<CheckIn> FindByPeriodo(Date dataEntrada, Date dataSaida);


}


