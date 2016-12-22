package common;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by guof on 20/12/16.
 */
@SpringBootApplication
public class CassandraApp {

    /*@Autowired
    UserRepository repository;


    public void find() {
        repository.findByFirstName("Bob");
    }

    public void save() {
        model.User user = new model.User(UUIDs.random(), "Bob", 23, "Zurich", "bob.lee@gmail.com", "Lee");
        System.out.print(user.getAge());
        repository.save(user);
    }*/

    public static void main(String[] args) {
        Logger log = Logger.getLogger(CassandraApp.class);
        SpringApplication.run(CassandraApp.class, args);
    }

}
