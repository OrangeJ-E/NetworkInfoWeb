package org.network.networkinfo.model;
import static org.junit.Assert.*;
import org.junit.Test;

public class AddressTest {

    @Test
    public void testAddressProperties() {
        Address address = new Address();
        address.setWlanMacAddress("00:11:22:AA:BB:CC");
        address.setHostName("TestHost");
        address.setIpAddress("192.168.0.1");

        assertEquals("00:11:22:AA:BB:CC", address.getWlanMacAddress());
        assertEquals("TestHost", address.getHostName());
        assertEquals("192.168.0.1", address.getIpAddress());
    }
}
