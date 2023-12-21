package org.network.networkinfo.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.network.networkinfo.repository.AddressRepository;
import org.network.networkinfo.service.NetworkInfo;
import org.network.networkinfo.service.NetworkInfoRetriever;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class NetworkInfoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private  NetworkInfoRetriever retriever;

    @BeforeEach
    public void setup() {
        NetworkInfoController controller = new NetworkInfoController(retriever);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void testHandleNetworkInfoRequest() throws Exception {
        NetworkInfo networkInfo = new NetworkInfo("00:11:22:33:44:55", "testHost", "192.168.1.1");

        mockMvc.perform(
                        post("/networkInfo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(networkInfo)))
                .andExpect(status().isOk())
                .andExpect(content().string("Network info saved successfully"));
    }
}
