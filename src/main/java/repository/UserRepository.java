package repository;

import model.User;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by guof on 20/12/16.
 */
@Repository
public interface UserRepository extends CassandraRepository<User> {

    @Query("SELECT * FROM users WHERE firstname = ?0")
    User findByFirstName(String firstname);

}
