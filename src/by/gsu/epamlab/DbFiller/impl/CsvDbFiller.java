package by.gsu.epamlab.DbFiller.impl;

import by.gsu.epamlab.DbFiller.DbFiller;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.connector.DbConnector;
import by.gsu.epamlab.exceptions.DbConnectorException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class CsvDbFiller implements DbFiller{
    private DbConnector connector;
    private String fileName;

    public CsvDbFiller(DbConnector connector, String fileName) {
        this.connector = connector;
        this.fileName = fileName;
    }

    @Override
    public void fillDbFromFile() throws DbConnectorException{
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                Result result = getResult(scanner);
                connector.addResult(result);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Result getResult(Scanner scanner){
        String line = scanner.nextLine();
        String[] arrayLine = line.split(";");
        Result result = new Result(arrayLine[0],arrayLine[1],arrayLine[2], arrayLine[3]);
        return result;
    }
}
