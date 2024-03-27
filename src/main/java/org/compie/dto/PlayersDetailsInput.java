package org.compie.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayersDetailsInput {
    @CsvBindByPosition(position = 0)
    private Integer id;

    @CsvBindByPosition(position = 1)
    private String nickname;
}
