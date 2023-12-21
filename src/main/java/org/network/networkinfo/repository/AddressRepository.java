package org.network.networkinfo.repository;

import org.network.networkinfo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Object, Long> {
    // 可以添加自定义的查询方法
    Address findByWlanMacAddress(String wlanMacAddress);
}
