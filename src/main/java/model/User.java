package model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cassandra.core.*;
import org.springframework.data.cassandra.mapping.*;

import java.util.UUID;


/**
 * Created by guof on 20/12/16.
 */
@Table("users")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class User {
    @PrimaryKeyColumn(
            name = "id",
            ordinal = 2,
            type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private UUID id;
    @PrimaryKeyColumn(name="firstname",ordinal = 2,
            type = PrimaryKeyType.PARTITIONED)
    private String firstname;
    @Column
    private Integer age;
    @Column
    private String city;
    @Column
    private String email;
    @Column
    private String lastname;

}
