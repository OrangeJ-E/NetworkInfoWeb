package org.network.networkinfo.service;

import org.network.networkinfo.model.Address;
import org.network.networkinfo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service
public class NetworkInfoRetriever {

    @Autowired
    private AddressRepository addressRepository;

    public NetworkInfo retrieveNetworkInfo() throws Exception {
        String wlanMacAddress = getWLANMacAddress();
        String hostName = getHostName();
        String ipAddress = getIpAddress();

        if (wlanMacAddress != null && hostName != null && ipAddress != null) {
            return new NetworkInfo(wlanMacAddress, hostName, ipAddress);
        } else {
            return null;
        }
    }

    public void writeToDatabase(NetworkInfo networkInfo) {
        Address address = addressRepository.findByWlanMacAddress(networkInfo.getWLANMacAddress());
        if (address == null) {
            address = new Address();
        }
        address.setWlanMacAddress(networkInfo.getWLANMacAddress());
        address.setHostName(networkInfo.getHostName());
        address.setIpAddress(networkInfo.getIpAddress());

        addressRepository.save(address);
    }

    private String getWLANMacAddress() throws Exception {
        for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            if (isWirelessInterface(networkInterface)) {
                byte[] macBytes = networkInterface.getHardwareAddress();
                if (macBytes != null && macBytes.length == 6) {
                    StringBuilder macAddress = new StringBuilder();
                    for (byte b : macBytes) {
                        macAddress.append(String.format("%02X", b));
                        macAddress.append(":");
                    }
                    return macAddress.substring(0, macAddress.length() - 1);
                }
            }
        }
        return null;
    }

    public boolean isWirelessInterface(NetworkInterface networkInterface) {
        String displayName = networkInterface.getDisplayName().toLowerCase();
        return displayName.contains("wireless") || displayName.contains("wifi");
    }

    private String getHostName() throws Exception {
        return InetAddress.getLocalHost().getHostName();
    }
    private static final Logger logger = LoggerFactory.getLogger(NetworkInfoRetriever.class);

    public void someMethod() {
        logger.info("Some informational message");
        logger.debug("Debug message");
        logger.error("Error message", new RuntimeException("Error"));
    }
    private String getIpAddress() throws Exception {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface currentInterface = interfaces.nextElement();
            if (!currentInterface.isLoopback() && currentInterface.isUp()) {
                Enumeration<InetAddress> addresses = currentInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress currentAddress = addresses.nextElement();
                    if (!currentAddress.isLoopbackAddress() && !currentAddress.getHostAddress().contains(":")) {
                        return currentAddress.getHostAddress();
                    }
                }
            }
        }
        return null;
    }
}
