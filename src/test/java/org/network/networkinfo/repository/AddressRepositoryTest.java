package org.network.networkinfo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;
import org.network.networkinfo.model.Address;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddressRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AddressRepository repository;

    @Test
    public void testFindByWlanMacAddress() {
        // 给定
        Address address = new Address();
        address.setWlanMacAddress("00:11:22:33:44:55");
        address.setHostName("TestHost");
        address.setIpAddress("192.168.1.1");
        entityManager.persist(address);

        // 当
        Address found = repository.findByWlanMacAddress("00:11:22:33:44:55");

        // 那么
        assertThat(found).isNotNull();
        assertThat(found.getWlanMacAddress()).isEqualTo("00:11:22:33:44:55");
        assertThat(found.getHostName()).isEqualTo("TestHost");
        assertThat(found.getIpAddress()).isEqualTo("192.168.1.1");
    }
}
