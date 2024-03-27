package org.compie.feign;

import org.compie.model.balldontlie.BalldontlieResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "balldontlie-server", url = "${balldontlie.host}")
public interface BalldontlieFeignClient {
    @GetMapping("/v1/players/{id}")
    BalldontlieResponse getGeoapifyResponse(@RequestHeader("Authorization") String apiKey,
                                            @RequestParam String id);
}
