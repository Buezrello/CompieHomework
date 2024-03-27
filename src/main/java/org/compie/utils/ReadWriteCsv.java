package org.compie.utils;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.compie.dto.PlayersDetailsInput;
import org.compie.model.balldontlie.Data;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ReadWriteCsv {

    public static List<PlayersDetailsInput> getPlayersDetailsInputFromCsv() throws FileNotFoundException {
        log.info("Reading players.csv from resources");

        File file = ResourceUtils.getFile("classpath:players.csv");

        return new CsvToBeanBuilder<PlayersDetailsInput>(new FileReader(file))
                .withType(PlayersDetailsInput.class)
                .withSkipLines(1)
                .build()
                .parse();
    }

    public static void writeScv(List<Data> data, String filePath) {
        try (Writer writer = new FileWriter(filePath);
             CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR,
                     CSVWriter.NO_QUOTE_CHARACTER,
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                     CSVWriter.DEFAULT_LINE_END)) {

            writer.write("ID,First Name,Last Name\n");
            StatefulBeanToCsv<Data> beanToCsv = new StatefulBeanToCsvBuilder<Data>(csvWriter)
                    .build();

            beanToCsv.write(data);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
    }

}
