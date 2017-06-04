import by.gsu.epamlab.DbFiller.DbFiller;
import by.gsu.epamlab.DbFiller.impl.CsvDbFiller;
import by.gsu.epamlab.beans.Mark;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.connector.DbConnector;
import by.gsu.epamlab.connector.impl.MySqlConnector;
import by.gsu.epamlab.constants.JdbcConstants;
import by.gsu.epamlab.enums.SearchCriteria;
import by.gsu.epamlab.exceptions.DbConnectorException;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Runner1 {
    public static void main(String[] args) {
        DbConnector connector = null;
        try {
            connector = MySqlConnector.connect(JdbcConstants.DB_URL, JdbcConstants.user, JdbcConstants.password);
            connector.clean();
            DbFiller filler = new CsvDbFiller(connector, "src/results.csv");
            filler.fillDbFromFile();
            connector.printMeanMark();
            List<Result> results = connector.getListOfSearchResults(SearchCriteria.CURENT_MONTH_RESULTS_ASCENDING);
            printList(results);
            printLatestDayResults(results);

        } catch (DbConnectorException e) {
            e.printStackTrace();
        }

    }
    private static <E> void printList(List<E> list){
        for (E element: list) {
            System.out.println(element);
        }
    }
    private static void printLatestDayResults(List<Result> results){
        List<Result> latestDayResults = new LinkedList<>();
        int latestDayNum = 0;
        for (Result result: results) {
            Date date = result.getDate();
            int dayNum = date.getDay();
            if (dayNum > latestDayNum){
                latestDayNum = dayNum;
            }
        }
        for (Result result: results) {
            if (result.getDate().getDay() == latestDayNum){
                latestDayResults.add(result);
            }
        }
        System.out.println("Latest day results");
        printList(latestDayResults);
    }
}
