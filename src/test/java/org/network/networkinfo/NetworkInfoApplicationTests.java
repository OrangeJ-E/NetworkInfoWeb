package org.network.networkinfo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EntityScan (basePackages = "org.network.networkinfo.model")
class NetworkInfoApplicationTests {

    @Test
    void contextLoads() {
    }

}
