package by.gsu.epamlab.enums;

import by.gsu.epamlab.constants.QueryConstants;

public enum SearchCriteria {
    MEAN_VALUE_OF_STUDENTS_INT_MARKS_DESCENDING(QueryConstants.FIND_MEAN_INT_MARKS),
    MEAN_VALUE_OF_STUDENTS_DOUBLE_MARKS_DESCENDING(QueryConstants.FIND_MEAN_DOUBLE_MARKS),
    MEAN_VALUE_OF_STUDENTS_HALF_MARKS_DESCENDING(QueryConstants.FIND_MEAN_HALF_MARKS),
    CURRENT_MONTH_RESULTS_ASCENDING(QueryConstants.FIND_RESULTS);


    private final String query;

    SearchCriteria(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
