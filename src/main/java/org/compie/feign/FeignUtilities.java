package org.compie.feign;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.compie.config.BalldontlieConfigurationProperties;
import org.compie.model.balldontlie.BalldontlieResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class FeignUtilities {
    private final BalldontlieFeignClient balldontlieFeignClient;
    private final BalldontlieConfigurationProperties balldontlieConfigurationProperties;

    public List<BalldontlieResponse> getBalldontlieResponseList(List<Integer> idList) {
        List<BalldontlieResponse> balldontlieResponseList = new ArrayList<>();

        for (int id : idList) {
            try {
                BalldontlieResponse balldontlieResponse = balldontlieFeignClient.getBalldontlieResponse(balldontlieConfigurationProperties.getApiKey(), id);
                balldontlieResponseList.add(balldontlieResponse);
            } catch (FeignException feignException) {
                log.error(feignException.getMessage());
            }
        }

        return balldontlieResponseList;
    }

    public BalldontlieResponse getBalldontlieResponseById(Integer id) {
        BalldontlieResponse balldontlieResponse = null;
        try {
            balldontlieResponse = balldontlieFeignClient.getBalldontlieResponse(balldontlieConfigurationProperties.getApiKey(), id);
        } catch (FeignException feignException) {
            log.error(feignException.getMessage());
        }

        return balldontlieResponse;
    }
}
