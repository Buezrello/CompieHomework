package org.compie.service.future;

import org.springframework.scheduling.annotation.Async;

import java.io.FileNotFoundException;

public interface AsyncCompieService {

    @Async
    void createPlayersCsvList() throws FileNotFoundException;
}
