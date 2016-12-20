import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * Created by guof on 20/12/16.
 */
public class CassandraApp {
    public static void main(String[] args) {
        String cassandraIp = "127.0.0.1";
        Cluster cluster = Cluster.builder().addContactPoints(cassandraIp).build();
        Session session = cluster.connect("users");

        session.execute("INSERT INTO users (lastname, age, city, email, firstname) VALUES ('Jones', 35, 'Austin', 'bob@example.com', 'Bob')");
        ResultSet results = session.execute("SELECT * FROM users");
        for (Row row : results) {
            System.out.println(row.getString("firstname") + " " +row.getInt("age"));
        }

        session.close();
        cluster.close();

    }
}
