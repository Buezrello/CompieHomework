package org.compie.service.future.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.compie.dao.RedisGeneric;
import org.compie.dto.PlayersDetailsInput;
import org.compie.feign.FeignUtilities;
import org.compie.model.balldontlie.BalldontlieResponse;
import org.compie.model.balldontlie.Data;
import org.compie.service.future.AsyncCompieService;
import org.compie.utils.ReadWriteCsv;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncCompieServiceImpl implements AsyncCompieService {

    private final FeignUtilities feignUtilities;
    private final RedisGeneric redisGeneric;

    @Override
    public void createPlayersCsvList() throws FileNotFoundException {
        log.info("read async players.csv file");
        List<PlayersDetailsInput> playersDetailsInputFromCsv = ReadWriteCsv.getPlayersDetailsInputFromCsv();
        List<Integer> idList = playersDetailsInputFromCsv.stream().map(PlayersDetailsInput::getId).collect(Collectors.toList());

        log.info("fetch async all Players Details from redis");
        List<Data> allPlayersDetails = redisGeneric.getAllPlayersDetails();

        if (allPlayersDetails.isEmpty()) {
            log.info("Redis is empty");
            log.info("fetch async players data from Web API");
            allPlayersDetails = getData(idList);
        } else {
            List<Integer> redisIdList = allPlayersDetails.stream().map(Data::getId).collect(Collectors.toList());
            idList.removeAll(redisIdList);
            if (!idList.isEmpty()) {
                log.info("some players were not found in Redis, fetching async from Web API");
                List<Data> newPlayersDetails = getData(idList);
                allPlayersDetails.addAll(newPlayersDetails);
            }
        }

        log.info("write async players date to output.csv");
        ReadWriteCsv.writeScv(allPlayersDetails, "output.csv");
    }

    private List<Data> getData(List<Integer> idList) {
        List<Data> allPlayersDetails;
        List<BalldontlieResponse> balldontlieResponseList = feignUtilities.getBalldontlieResponseList(idList);
        allPlayersDetails = balldontlieResponseList.stream().map(BalldontlieResponse::getData).collect(Collectors.toList());
        log.info("saving async all Players Details to Redis");
        redisGeneric.saveAllPlayersDetails(allPlayersDetails);
        return allPlayersDetails;
    }
}
