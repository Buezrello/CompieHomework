package org.compie.repository;

import org.compie.model.balldontlie.Data;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepositoryPlayerDetails extends CrudRepository<Data, Integer> { }
