import by.gsu.epamlab.DbFiller.DbFiller;
import by.gsu.epamlab.DbFiller.impl.CsvDbFiller;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.connector.DbConnector;
import by.gsu.epamlab.connector.impl.MySqlConnector;
import by.gsu.epamlab.constants.JdbcConstants;
import by.gsu.epamlab.enums.EnumMark;
import by.gsu.epamlab.enums.SearchCriteria;
import by.gsu.epamlab.exceptions.DbConnectorException;
import by.gsu.epamlab.util.ResultPrinterUtil;

import java.util.List;

public class Runner3 {
    public static void main(String[] args) {
        DbConnector connector = null;
        try {
            connector = MySqlConnector.connect(JdbcConstants.DB_URL, JdbcConstants.user, JdbcConstants.password);
            connector.clean();
            DbFiller filler = new CsvDbFiller(connector, GeneralConstants.CSV_FILE_NAME_WITH_HALF_MARKS, EnumMark.HALF_MARK);
            filler.fillDbFromFile();
            connector.printMeanMark(EnumMark.HALF_MARK);
            List<Result> results = connector.getListOfSearchResults(SearchCriteria.CURRENT_MONTH_RESULTS_ASCENDING, EnumMark.HALF_MARK);
            ResultPrinterUtil.printLatestDayResults(results);
        } catch (DbConnectorException e) {
            System.err.println("Connector error" + e.getMessage());
        } finally {
            if (connector != null) {
                try {
                    connector.close();
                } catch (Exception e) {
                    System.err.println("Error occurred while closing resources" + e.getMessage());
                }
            }
        }
    }
}
