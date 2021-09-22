package br.com.otero.parkinglot.service;


import br.com.otero.parkinglot.exception.BusinessException;
import br.com.otero.parkinglot.models.dto.CheckinRequest;
import br.com.otero.parkinglot.models.dto.CheckinResponse;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {


    @Autowired
    private CheckinService checkinService;

    @Test
    public void checkinOk() {
        CheckinRequest checkin = CheckinRequest.builder()
                .brand("Nissan")
                .model("Sentra")
                .plate("ASD1234")
                .build();

        CheckinResponse checkinResponse = this.checkinService.newCheckin(checkin);

        Assert.assertNotNull(checkinResponse.getId());
    }

    @Rule
    public ExpectedException checkoutSemPagar = ExpectedException.none();


    @Test(expected = BusinessException.class)
    public void checkoutsempagar() {
        this.checkinService.checkOut(4L);
        this.checkoutSemPagar.expect(BusinessException.class);
        this.checkoutSemPagar.expectMessage("Pagamento nao realizado");
    }

}
