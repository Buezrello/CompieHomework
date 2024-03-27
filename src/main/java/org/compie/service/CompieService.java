package org.compie.service;

import org.compie.model.response.StatusResponse;

import java.io.FileNotFoundException;

public interface CompieService {

    StatusResponse getPlayersDetails() throws FileNotFoundException;
}
