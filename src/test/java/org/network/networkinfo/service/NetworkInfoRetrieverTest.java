package org.network.networkinfo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.network.networkinfo.model.Address;
import org.network.networkinfo.repository.AddressRepository;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NetworkInfoRetrieverTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private NetworkInfoRetriever networkInfoRetriever;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
@Test
    void testWriteToDatabase() {
        NetworkInfo testNetworkInfo = new NetworkInfo("00：11：22：33：44：55","HostName","192.168.1.1");
        Object testObject = new Address();  // 创建 Address 对象
        // 设置 Address 对象的属性

        when(addressRepository.save(any(Address.class))).thenReturn((Address) testObject);

        // 调用 writeToDatabase 方法
        networkInfoRetriever.writeToDatabase(testNetworkInfo);

        verify(addressRepository).save(any(Address.class));
    }
    // 这里可以添加更多的测试方法，例如测试异常情况、边界条件等
}
