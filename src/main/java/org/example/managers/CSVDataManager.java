package org.example.managers;

import com.opencsv.CSVReader;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.example.models.ExchangeRate;
import org.example.utils.ExchangeFormats;
import org.example.utils.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;


public class CSVDataManager {
    private File file;
    private String templatePath = "data/exchange_rate/%s.csv";

    public CSVDataManager(LocalDate date) {
        Path path = Paths.get(String.format(templatePath, ExchangeFormats.formatDate(date)));
        file = new File(path.toUri());
        if (!file.exists()) {
            FileUtils.createNewFile(file);
        }
    }

    public void write(List<ExchangeRate> exchangeRates) {
        try (FileWriter writer = new FileWriter(file)) {
            StatefulBeanToCsv<ExchangeRate> beanToCsv = new StatefulBeanToCsvBuilder<ExchangeRate>(writer)
                    .withMappingStrategy(getMappingStrategy())
                    .build();

            beanToCsv.write(exchangeRates);

        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private MappingStrategy<ExchangeRate> getMappingStrategy() {
        ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
        mappingStrategy.setType(ExchangeRate.class);

        return mappingStrategy;
    }

    public List<ExchangeRate> read() {
        try {
            Reader reader = Files.newBufferedReader(file.toPath());

            CSVReader csvReader = new CSVReader(reader);

            CsvToBean<ExchangeRate> bb = new CsvToBeanBuilder<ExchangeRate>(csvReader)
                    .withType(ExchangeRate.class)
                    .build();

            bb.setMappingStrategy(getMappingStrategy());
            return bb.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}