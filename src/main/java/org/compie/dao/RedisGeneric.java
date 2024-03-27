package org.compie.dao;

import org.compie.model.balldontlie.Data;

import java.util.List;
import java.util.Optional;

public interface RedisGeneric {
    void saveAllPlayersDetails(List<Data> dataList);
    void savePlayerDetailsIfNewOrChanged(Data data);
    List<Data> getAllPlayersDetails();
    Optional<Data> getPlayerDetailsById(int id);
}
