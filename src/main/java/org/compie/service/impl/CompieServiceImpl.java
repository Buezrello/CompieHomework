package org.compie.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.compie.model.response.StatusResponse;
import org.compie.service.CompieService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompieServiceImpl implements CompieService {
    @Override
    public StatusResponse getPlayersDetails() {


        return new StatusResponse("IN_PROGRESS", "async request send");
    }
}
