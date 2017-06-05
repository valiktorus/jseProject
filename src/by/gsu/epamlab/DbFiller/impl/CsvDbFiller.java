package by.gsu.epamlab.DbFiller.impl;

import by.gsu.epamlab.DbFiller.DbFiller;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.beans.marks.Mark;
import by.gsu.epamlab.connector.DbConnector;
import by.gsu.epamlab.enums.EnumMark;
import by.gsu.epamlab.exceptions.DbConnectorException;
import by.gsu.epamlab.factory.MarkFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class CsvDbFiller implements DbFiller{
    public static final int LOGIN_INDEX = 0;
    public static final int TEST_INDEX = 1;
    public static final int DATE_INDEX = 2;
    public static final int MARK_INDEX = 3;
    private DbConnector connector;
    private String fileName;
    private EnumMark enumMark;

    public CsvDbFiller(DbConnector connector, String fileName, EnumMark markFormat) {
        this.connector = connector;
        this.fileName = fileName;
        enumMark = markFormat;
    }

    @Override
    public void fillDbFromFile() throws DbConnectorException{
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            scanner.useLocale(Locale.ENGLISH);
            while (scanner.hasNextLine()) {
                Result result = getResult(scanner, enumMark);
                connector.addResult(result);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Result getResult(Scanner scanner, EnumMark enumMark){
        String line = scanner.nextLine();
        String[] arrayLine = line.split(";");
        Mark mark = MarkFactory.getMark(enumMark, arrayLine[MARK_INDEX]);
        Result result = new Result(arrayLine[LOGIN_INDEX],arrayLine[TEST_INDEX],arrayLine[DATE_INDEX], mark);
        return result;
    }
}
