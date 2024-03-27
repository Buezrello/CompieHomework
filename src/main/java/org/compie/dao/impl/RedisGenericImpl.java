package org.compie.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.compie.dao.RedisGeneric;
import org.compie.model.balldontlie.Data;
import org.compie.repository.RedisRepositoryPlayerDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RedisGenericImpl implements RedisGeneric {

    private final RedisRepositoryPlayerDetails redisRepositoryPlayerDetails;

    @Override
    public void saveAllPlayersDetails(List<Data> dataList) {
        log.info("saving list Players Details to redis");
        redisRepositoryPlayerDetails.saveAll(dataList);
    }

    @Override
    public void savePlayerDetailsIfNewOrChanged(Data data) {
        Optional<Data> byId = redisRepositoryPlayerDetails.findById(data.getId());

        if (byId.isEmpty()) {
            log.info("new Player with id {}, saving to redis", data.getId());
            redisRepositoryPlayerDetails.save(data);
        } else {
            Data dataFromRedis = byId.get();
            if (!dataFromRedis.equals(data)) {
                log.info("Player Details with id {} were updated, override in redis", data.getId());
                redisRepositoryPlayerDetails.save(data);
            }
        }
    }

    @Override
    public List<Data> getAllPlayersDetails() {
        log.info("fetching all Players Details");
        return (List<Data>) redisRepositoryPlayerDetails.findAll();
    }

    @Override
    public Optional<Data> getPlayerDetailsById(int id) {
        log.info("fetching Player Details by id {}", id);
        return redisRepositoryPlayerDetails.findById(id);
    }


}
