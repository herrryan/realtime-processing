package common;

import com.datastax.driver.core.utils.UUIDs;
import model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;


/**
 * Created by taagufe1 on 12/21/16.
 */
@RestController
public class DefaultKafkaRestController {

    private static Logger log = Logger.getLogger(DefaultKafkaRestController.class);

    @Autowired
    DefaultKafkaProducer kafkaProducer;

    @RequestMapping("/user")
    public void sendUser(@RequestBody User user) throws ExecutionException, InterruptedException{
        log.info("Sending to Kafka: " + user.getFirstname() + " " + user.getLastname());
        kafkaProducer.send(user.getFirstname() + " " + user.getLastname());
    }

}
