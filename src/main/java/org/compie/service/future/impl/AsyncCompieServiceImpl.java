package org.compie.service.future.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.compie.dto.PlayersDetailsInput;
import org.compie.feign.FeignUtilities;
import org.compie.model.balldontlie.BalldontlieResponse;
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

    @Override
    public void createPlayersCsvList() throws FileNotFoundException {
        log.info("read async players.csv file");
        List<PlayersDetailsInput> playersDetailsInputFromCsv = ReadWriteCsv.getPlayersDetailsInputFromCsv();
        List<Long> idList = playersDetailsInputFromCsv.stream().map(PlayersDetailsInput::getId).collect(Collectors.toList());

        log.info("fetch async players data from site ot from database");
        List<BalldontlieResponse> balldontlieResponseList = feignUtilities.getBalldontlieResponseList(idList);
        List<org.compie.model.balldontlie.Data> dataLast = balldontlieResponseList.stream().map(BalldontlieResponse::getData).collect(Collectors.toList());

        log.info("write async players date to output.csv");
        ReadWriteCsv.writeScv(dataLast, "output.csv");
    }
}
