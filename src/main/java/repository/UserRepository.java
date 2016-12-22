package repository;



import model.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by guof on 20/12/16.
 */
@Repository
public interface UserRepository extends CassandraRepository {

    @Query("SELECT * FROM users WHERE firstname = ?0")
    SecurityProperties.User findByFirstName(String firstname);

    User save(User user);
}
