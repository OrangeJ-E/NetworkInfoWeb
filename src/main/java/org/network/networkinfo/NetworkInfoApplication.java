package org.network.networkinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("org.network.networkinfo.model")
public class NetworkInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkInfoApplication.class, args);
    }

}
