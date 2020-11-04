package com.example.tokenAPI;

import com.example.tokenAPI.controller.PaymentsController;
import com.example.tokenAPI.dao.PaymentsRepository;
import com.example.tokenAPI.model.Events;
import com.example.tokenAPI.model.Payments;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class TokenApiApplicationTests {
	@Autowired
	private PaymentsRepository paymentsRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PaymentsController paymentsController;

    /**
     *
     */
    @Test
    public void getAllTest() {
        Iterable<Events> evList = paymentsController.getAllPayments();
        Lists.newArrayList(evList).size();
        assertThat(Lists.newArrayList(evList).size()).isEqualTo(10);
        //assertThat(evList).isEmpty();
    }

    /**
     *
     */
	@Test
	public void getEventByIdTest(){
        /*        {
                "id": 22,
                "paymentId": "6daba155-ebb5-424e-bb88-f51effd69bb5",
                "fromAccount": "0xcb5361449a338be5bda5915010577fba0694f0ff",
                "toAccount": "0x4e10c4f87d103745c708fad042dce2cb2fd490bb",
                "amount": 1,
                "date": "2020-11-02T12:29:22",
                "success": null
        }*/
	    Events ev1 = new Events();
        ev1.setAmount(1);
        String sDate1="2020-11-02T12:29:22";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(sDate1, formatter);
        ev1.setDate(dateTime);
        ev1.setFromAccount("0xcb5361449a338be5bda5915010577fba0694f0ff");
        ev1.setPaymentId("6daba155-ebb5-424e-bb88-f51effd69bb5");
        ev1.setToAccount("0x4e10c4f87d103745c708fad042dce2cb2fd490bb");
        ev1.setSuccess(null);
        ev1.setId(22);
        entityManager.merge(ev1);
        entityManager.flush();
        //when
        Events testEvent = paymentsController.getById(ev1.getId()).get();

        //then
        assertThat(testEvent.getPaymentId()).isEqualTo(ev1.getPaymentId());

    }

    /**
     *
     * @throws SQLException
     */
    @Test
    public void getByPayIdTest() throws SQLException {
        /*        {
                "id": 22,
                "paymentId": "6daba155-ebb5-424e-bb88-f51effd69bb5",
                "fromAccount": "0xcb5361449a338be5bda5915010577fba0694f0ff",
                "toAccount": "0x4e10c4f87d103745c708fad042dce2cb2fd490bb",
                "amount": 1,
                "date": "2020-11-02T12:29:22",
                "success": null
        }*/
        Events ev1 = new Events();
        ev1.setAmount(1);
        String sDate1="2020-11-02T12:29:22";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(sDate1, formatter);
        ev1.setDate(dateTime);
        ev1.setFromAccount("0xcb5361449a338be5bda5915010577fba0694f0ff");
        ev1.setPaymentId("6daba155-ebb5-424e-bb88-f51effd69bb5");
        ev1.setToAccount("0x4e10c4f87d103745c708fad042dce2cb2fd490bb");
        ev1.setSuccess(null);
        ev1.setId(22);

        //when
        Iterable<Events> testEvent = paymentsController.getByPayId(ev1.getPaymentId());

        //then
        assertThat(testEvent.iterator().next().getPaymentId().toString()).isEqualTo(ev1.getPaymentId().toString());

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void addPaymentTest() throws Exception {
        /*        {
                "id": 22,
                "paymentId": "6daba155-ebb5-424e-bb88-f51effd69bb5",
                "fromAccount": "0xcb5361449a338be5bda5915010577fba0694f0ff",
                "toAccount": "0x4e10c4f87d103745c708fad042dce2cb2fd490bb",
                "amount": 1,
                "date": "2020-11-02T12:29:22",
                "success": null
        }*/
        Events ev1 = new Events();
        ev1.setAmount(1);
        String sDate1 = "2020-11-02T12:29:22";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(sDate1, formatter);
        ev1.setDate(dateTime);
        ev1.setFromAccount("0xcb5361449a338be5bda5915010577fba0694f0ff");
        ev1.setPaymentId("6daba155-ebb5-424e-bb88-f51effd69bb5");
        ev1.setToAccount("0x4e10c4f87d103745c708fad042dce2cb2fd490bb");
        ev1.setSuccess(null);
        ev1.setId(22);
        entityManager.merge(ev1);
        entityManager.flush();

        Payments pay1 = new Payments();
        pay1.setAccountfrom("0xcb5361449a338be5bda5915010577fba0694f0ff");
        pay1.setAccountto("0x4e10c4f87d103745c708fad042dce2cb2fd490bb");
        pay1.setId(null);
        pay1.setPk("0x4421db7455ad6ec647949702d1b66364b4d8a16f0d2dc04e05336e2cfeb84afe");

        String result = paymentsController.addPayment(pay1);

        assertThat(result).isEqualTo("Operation done =)");


    }
}
