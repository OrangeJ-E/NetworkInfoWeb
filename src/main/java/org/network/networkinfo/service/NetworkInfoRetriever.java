package org.network.networkinfo.service;

import org.springframework.stereotype.Service;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.*;
import java.util.Collections;
import java.util.Enumeration;

@Service
public class NetworkInfoRetriever {

    private static final String DATABASE_URL = "jdbc:sqlite:/IJAVA/NetworkInfo/untitled/src/network.db";

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
        String selectQuery = "SELECT * FROM Address WHERE wlan_mac_address=?";
        String updateQuery = "UPDATE Address SET host_name=?,ip_address=? WHERE wlan_mac_address=?";
        String insertQuery = "INSERT INTO Address (host_name, wlan_mac_address, ip_address) VALUES (?,?,?)";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {

            selectStatement.setString(1, networkInfo.getWLANMacAddress());
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    setStatementParameters(updateStatement, networkInfo);
                    updateStatement.executeUpdate();
                }
            } else {
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    setStatementParameters(preparedStatement, networkInfo);
                    preparedStatement.executeUpdate();
                }
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("数据库操作出错：" + e.getMessage());
        }
    }

    private void setStatementParameters(PreparedStatement statement, NetworkInfo networkInfo) throws SQLException {
        statement.setString(1, networkInfo.getHostName());
        statement.setString(2, networkInfo.getWLANMacAddress());
        statement.setString(3, networkInfo.getIpAddress());
    }

    public String getWLANMacAddress() throws Exception {
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

    public String getHostName() throws Exception {
        return InetAddress.getLocalHost().getHostName();
    }

    public String getIpAddress() throws Exception {
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
