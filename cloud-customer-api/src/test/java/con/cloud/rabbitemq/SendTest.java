package con.cloud.rabbitemq;

import com.cloud.configuration.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * Created by sunhaidi on 2019-06-04.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("de")
public class SendTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void send() {
        for (int i = 1; i <= 1; i++) {

            String context = i + ":hello " + new Date();
            System.out.println("Sender : " + context);
            rabbitTemplate.convertAndSend("testQueue", context);
        }
    }

    @Test
    public void sendTopic() {
        for (int i = 1; i <= 1; i++) {

            String context = i + ":hello " + new Date();
            System.out.println("Sender : " + context);
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("fanout_exchange", "", context, correlationData);
        }
    }

}
