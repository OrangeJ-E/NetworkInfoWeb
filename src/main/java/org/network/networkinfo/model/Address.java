package org.network.networkinfo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;


@Entity
@Table(name = "Address")  // 确保表名与数据库中的表名匹配
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 数据库表的主键

    @Column(name = "wlan_mac_address", nullable = false, unique = true)
    private String wlanMacAddress;  // WLAN MAC 地址

    @Column(name = "host_name")
    private String hostName;  // 主机名

    @Column(name = "ip_address")
    private String ipAddress;  // IP 地址

    // 构造函数
    public Address() {
    }

    // 为每个字段提供 getter 和 setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWlanMacAddress() {
        return wlanMacAddress;
    }

    public void setWlanMacAddress(String wlanMacAddress) {
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
