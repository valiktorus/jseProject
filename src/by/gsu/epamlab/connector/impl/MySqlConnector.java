package by.gsu.epamlab.connector.impl;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.connector.DbConnector;
import by.gsu.epamlab.constants.QueryConstants;
import by.gsu.epamlab.enums.Driver;
import by.gsu.epamlab.enums.EnumMark;
import by.gsu.epamlab.enums.SearchCriteria;
import by.gsu.epamlab.exceptions.DbConnectorException;
import by.gsu.epamlab.factory.MarkFactory;
import by.gsu.epamlab.factory.MarkQueryFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MySqlConnector implements DbConnector{
    public static final int LOGIN_COLUMN_INDEX = 1;
    public static final int TEST_COLUMN_INDEX = 2;
    public static final int DATE_COLUMN_INDEX = 3;
    public static final int MARK_COLUMN_INDEX = 4;
    public static final int LOGIN_PARAMETER_INDEX = 1;
    public static final int TEST_PARAMETER_INDEX = 2;
    public static final int DATE_PARAMETER_INDEX = 3;
    public static final int MARK_PARAMETER_INDEX = 4;
    public static final int ID_TEST_COLUMN_INDEX = 1;
    public static final int AVG_MARK_COLUMN_INDEX = 2;
    public static final String MEAN_MARK_REGEX = "%s;%.2f%n";
    private static DbConnector connector;

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public MySqlConnector(Connection connection) {
        this.connection = connection;
    }

    public static DbConnector connect(String dbUrl, String login, String password) throws DbConnectorException{
        if (connector == null){
            try {
                Class.forName(Driver.MySQL.getDriverName());
                connector = new MySqlConnector(DriverManager.getConnection(dbUrl, login, password));
            } catch (SQLException | ClassNotFoundException e) {
               throw new DbConnectorException("Error in creating new connector", e);
            }
        }
        return connector;
    }

    @Override
    public void clean() throws DbConnectorException {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(QueryConstants.REMOVE_LOGIN_RECORDS);
            statement.executeUpdate(QueryConstants.REMOVE_TESTS_RECORDS);
            statement.executeUpdate(QueryConstants.REMOVE_RESULTS_RECORDS);
        } catch (SQLException e) {
            throw new DbConnectorException("Error in cleaning tables", e);
        }
    }

    @Override
    public List<Result> getListOfSearchResults(SearchCriteria searchCriteria, EnumMark enumMark) throws DbConnectorException {
        List<Result> results = new LinkedList<>();
        try {
            resultSet = statement.executeQuery(searchCriteria.getQuery());
            while (resultSet.next()){
                Result result = new Result(resultSet.getString(LOGIN_COLUMN_INDEX),
                        resultSet.getString(TEST_COLUMN_INDEX),
                        resultSet.getString(DATE_COLUMN_INDEX),
                        MarkFactory.getMark(enumMark,resultSet.getString(MARK_COLUMN_INDEX)));
                results.add(result);
            }
        } catch (SQLException e) {
            throw new DbConnectorException("Error in getting list of results", e);
        }
        return results;
    }

    @Override
    public void addResult(Result result) throws DbConnectorException{
        String login = result.getStudent().getLogin();
        int loginId;
        String test = result.getTest();
        int idTest;
        Date date = result.getDate();
        int mark = result.getMark().getIntMark();
        try {
            resultSet = statement.executeQuery(QueryConstants.FIND_LOGIN_BEGIN + login + QueryConstants.FIND_LOGIN_END);
            loginId = getLoginId(resultSet, login);
            resultSet = statement.executeQuery(QueryConstants.FIND_TEST_ID_BEGIN + test + QueryConstants.FIND_TEST_ID_END);
            idTest = getIdTest(resultSet, test);
            preparedStatement = connection.prepareStatement(QueryConstants.ADD_RESULT);
            preparedStatement.setInt(LOGIN_PARAMETER_INDEX, loginId);
            preparedStatement.setInt(TEST_PARAMETER_INDEX, idTest);
            preparedStatement.setDate(DATE_PARAMETER_INDEX, date);
            preparedStatement.setInt(MARK_PARAMETER_INDEX, mark);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbConnectorException("Error during adding result to DB", e);
        }
    }

    private int getLoginId(ResultSet resultSet, String login) throws SQLException{
        int loginId;
            if (resultSet.next()){
                loginId = resultSet.getInt(LOGIN_COLUMN_INDEX);
            }else {
                statement.executeUpdate(QueryConstants.INSERT_LOGIN_BEGIN + login + QueryConstants.INSERT_LOGIN_END);
                resultSet = statement.executeQuery(QueryConstants.FIND_LOGIN_BEGIN + login + QueryConstants.FIND_LOGIN_END);
                resultSet.next();
                loginId = resultSet.getInt(LOGIN_COLUMN_INDEX);
            }
        return loginId;
    }

    private int getIdTest(ResultSet resultSet, String test) throws SQLException{
        int idTest;
        if (resultSet.next()){
            idTest = resultSet.getInt(ID_TEST_COLUMN_INDEX);
        }else {
            statement.executeUpdate(QueryConstants.INSERT_TEST_ID_BEGIN + test + QueryConstants.INSERT_TEST_ID_END);
            resultSet = statement.executeQuery(QueryConstants.FIND_TEST_ID_BEGIN + test + QueryConstants.FIND_TEST_ID_END);
            resultSet.next();
            idTest = resultSet.getInt(1);
        }
        return idTest;
    }

    @Override
    public void printMeanMark(EnumMark enumMark) throws DbConnectorException{
        System.out.println("Mean marks:");
        try {
            resultSet = statement.executeQuery(MarkQueryFactory.getQuery(enumMark));
            while (resultSet.next()){
                String login = resultSet.getString(LOGIN_COLUMN_INDEX);
                double avgMark = resultSet.getDouble(AVG_MARK_COLUMN_INDEX);
                System.out.printf(MEAN_MARK_REGEX, login, avgMark);
            }
        } catch (SQLException e) {
            throw new DbConnectorException("Error in printing mean mark", e);
        }
    }

    @Override
    public void close() throws Exception {
        connector = null;
        if (resultSet != null && !resultSet.isClosed()) {
            resultSet.close();
        }
        if (preparedStatement != null && !preparedStatement.isClosed()) {
            preparedStatement.close();
        }
        if (statement != null && !statement.isClosed()) {
            statement.close();
        }
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
