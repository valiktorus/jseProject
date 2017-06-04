package by.gsu.epamlab.enums;

import by.gsu.epamlab.constants.QueryConstants;

public enum SearchCriteria {
    MEAN_VALUE_OF_STUDENTS_MARKS_DESCENDING(QueryConstants.FIND_MARKS),
    CURENT_MONTH_RESULTS_ASCENDING(QueryConstants.FIND_RESULTS);


    private final String query;

    SearchCriteria(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
