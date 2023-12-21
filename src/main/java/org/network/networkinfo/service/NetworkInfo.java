package org.network.networkinfo.service;

import org.springframework.stereotype.Service;
public class NetworkInfo {
    private String wlanMacAddress;
    private String hostName;
    private String ipAddress;

    // 构造函数
    public NetworkInfo(String wlanMacAddress, String hostName, String ipAddress) {
        this.wlanMacAddress = wlanMacAddress;
        this.hostName = hostName;
        this.ipAddress = ipAddress;
    }

    // Getters 和 Setters
    public String getWLANMacAddress() {
        return wlanMacAddress;
    }

    public void setWLANMacAddress(String wlanMacAddress) {
        this.wlanMacAddress = wlanMacAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

}
