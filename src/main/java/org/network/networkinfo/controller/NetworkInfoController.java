package org.network.networkinfo.controller;
import org.network.networkinfo.service.NetworkInfoRetriever;
import org.network.networkinfo.service.NetworkInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class NetworkInfoController {
    private final NetworkInfoRetriever retriever = new NetworkInfoRetriever();

    @PostMapping("/networkInfo")
    public String handleNetworkInfoRequest(@RequestBody NetworkInfo networkInfo) {
        try {
            retriever.writeToDatabase(networkInfo);
            return "Network info saved successfully";
        }catch (Exception e) {
            return "Error saving network info:" + e.getMessage();
        }
    }
}
