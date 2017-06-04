package by.gsu.epamlab.DbFiller;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.connector.DbConnector;
import by.gsu.epamlab.exceptions.DbConnectorException;

import java.util.Scanner;

public interface DbFiller {
    void fillDbFromFile() throws DbConnectorException;
}
