package org.network.networkinfo.repository;

import org.network.networkinfo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface AddressRepository extends JpaRepository<Object, Long> {
    // 可以添加自定义的查询方法
    Optional<Object> findById(Long aLong);

    Address findByWlanMacAddress(String wlanMacAddress);
}
