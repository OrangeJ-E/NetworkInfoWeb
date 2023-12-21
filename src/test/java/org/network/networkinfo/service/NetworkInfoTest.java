package org.network.networkinfo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class NetworkInfoTest {

    @Test
    public void testNetworkInfoConstructorAndGetters() {
        NetworkInfo networkInfo = new NetworkInfo("00:11:22:33:44:55", "testHost", "192.168.1.1");

        assertEquals("00:11:22:33:44:55", networkInfo.getWLANMacAddress());
        assertEquals("testHost", networkInfo.getHostName());
        assertEquals("192.168.1.1", networkInfo.getIpAddress());
    }

    @Test
    public void testSetters() {
        NetworkInfo networkInfo = new NetworkInfo(null, null, null);

        networkInfo.setWLANMacAddress("00:11:22:33:44:55");
        networkInfo.setHostName("testHost");
        networkInfo.setIpAddress("192.168.1.1");

        assertEquals("00:11:22:33:44:55", networkInfo.getWLANMacAddress());
        assertEquals("testHost", networkInfo.getHostName());
        assertEquals("192.168.1.1", networkInfo.getIpAddress());
    }
}
