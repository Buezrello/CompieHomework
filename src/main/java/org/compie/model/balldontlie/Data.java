package org.compie.model.balldontlie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    @CsvBindByName
    private int id;
    @CsvBindByName
    private String first_name;
    @CsvBindByName
    private String last_name;
}
