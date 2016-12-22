package config;

import common.DefaultKafkaProducer;

/**
 * Created by taagufe1 on 12/21/16.
 */

//@Configuration
public class AppBeans {

  //  @Bean
    public DefaultKafkaProducer initProducer(){
        System.out.println("start a kafka producer");
        return new DefaultKafkaProducer();
    }
}
