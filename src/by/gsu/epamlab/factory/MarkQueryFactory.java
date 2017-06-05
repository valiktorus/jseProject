package by.gsu.epamlab.factory;

import by.gsu.epamlab.enums.EnumMark;
import by.gsu.epamlab.enums.SearchCriteria;

public class MarkQueryFactory {
    public static String getQuery(EnumMark enumMark){
        SearchCriteria searchCriteria = null;
        switch (enumMark){
            case INT_MARK:
                searchCriteria = SearchCriteria.MEAN_VALUE_OF_STUDENTS_INT_MARKS_DESCENDING;
                break;
            case DOUBLE_MARK:
                searchCriteria = SearchCriteria.MEAN_VALUE_OF_STUDENTS_DOUBLE_MARKS_DESCENDING;
                break;
            case HALF_MARK:
                searchCriteria = SearchCriteria.MEAN_VALUE_OF_STUDENTS_HALF_MARKS_DESCENDING;
                break;
        }
        return searchCriteria.getQuery();
    }
}
