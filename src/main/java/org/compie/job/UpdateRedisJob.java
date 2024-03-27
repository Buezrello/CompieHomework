package org.compie.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.compie.dao.RedisGeneric;
import org.compie.dto.PlayersDetailsInput;
import org.compie.feign.FeignUtilities;
import org.compie.model.balldontlie.BalldontlieResponse;
import org.compie.model.balldontlie.Data;
import org.compie.utils.ReadWriteCsv;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateRedisJob {

    private final FeignUtilities feignUtilities;
    private final RedisGeneric redisGeneric;

    @Async
    @Scheduled(cron = "0 */15 * * * *")
    public void updateRedis() throws FileNotFoundException {
        log.info("scheduler: read async players.csv file");
        List<PlayersDetailsInput> playersDetailsInputFromCsv = ReadWriteCsv.getPlayersDetailsInputFromCsv();
        List<Integer> idList = playersDetailsInputFromCsv.stream().map(PlayersDetailsInput::getId).collect(Collectors.toList());

        log.info("scheduler: read async Players Details from Web API");
        List<BalldontlieResponse> balldontlieResponseList = feignUtilities.getBalldontlieResponseList(idList);
        List<Data> allPlayersDetails = balldontlieResponseList.stream().map(BalldontlieResponse::getData).collect(Collectors.toList());

        log.info("scheduler: update redis if necessary");
        for (Data data : allPlayersDetails) {
            redisGeneric.savePlayerDetailsIfNewOrChanged(data);
        }
    }
}
