package org.compie.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayersDetailsInput {
    @CsvBindByName(column = "id")
    private Long id;

    @CsvBindByName(column = "nickname")
    private String nickname;
}
