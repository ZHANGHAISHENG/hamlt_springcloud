package com.hamlt.demo.client;

import com.hamlt.demo.api.service.ProviderRemoteService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "provider-service", path = "", fallback = ProviderFallback.class)
public interface ProviderClient extends ProviderRemoteService {

}
