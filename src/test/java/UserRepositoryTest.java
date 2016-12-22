import config.CassandraConfig;

import com.datastax.driver.core.utils.UUIDs;

import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CassandraConfig.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void repositoryStoreUser(){
        User user = new User(UUIDs.random(), "Bob", 23, "Zurich", "bob.lee@gmail.com", "Lee");
        repository.save(user);
    }
}