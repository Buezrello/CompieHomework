package org.compie.model.balldontlie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@RedisHash
public class Data {
    @Id
//    @CsvBindByName
    @CsvBindByPosition(position = 0)
    private int id;
//    @CsvBindByName
    @CsvBindByPosition(position = 1)
    private String first_name;
//    @CsvBindByName
    @CsvBindByPosition(position = 2)
    private String last_name;
}
