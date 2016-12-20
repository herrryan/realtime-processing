package model;

import lombok.Data;
import org.springframework.cassandra.core.*;
import org.springframework.data.cassandra.mapping.*;


/**
 * Created by guof on 20/12/16.
 */
@Table
@Data
public class User {
    @PrimaryKeyColumn(
            name = "firstname",
            ordinal = 2,
            type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
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
