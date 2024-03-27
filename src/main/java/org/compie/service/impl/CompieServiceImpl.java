package org.compie.service.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.compie.model.response.StatusResponse;
import org.compie.service.CompieService;
import org.compie.service.future.AsyncCompieService;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
@Slf4j
@Data
public class CompieServiceImpl implements CompieService {

    private final AsyncCompieService asyncCompieService;

    @Override
    public StatusResponse getPlayersDetails() throws FileNotFoundException {

        asyncCompieService.createPlayersCsvList();

        return new StatusResponse("IN_PROGRESS", "async request send");
    }
}
