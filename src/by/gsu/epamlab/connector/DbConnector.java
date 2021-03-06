package by.gsu.epamlab.connector;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.enums.EnumMark;
import by.gsu.epamlab.enums.SearchCriteria;
import by.gsu.epamlab.exceptions.DbConnectorException;

import java.util.List;

public interface DbConnector extends AutoCloseable{
    void clean() throws DbConnectorException;
    List<Result> getListOfSearchResults(SearchCriteria searchCriteria, EnumMark enumMark) throws DbConnectorException;
    void addResult(Result result) throws DbConnectorException;
    void printMeanMark(EnumMark enumMark) throws DbConnectorException;

}
