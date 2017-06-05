package by.gsu.epamlab.constants;

public class QueryConstants {
    public static final String REMOVE_LOGIN_RECORDS = "truncate table logins;";
    public static final String REMOVE_TESTS_RECORDS = "truncate table tests;";
    public static final String REMOVE_RESULTS_RECORDS = "truncate table results;";
    public static final String FIND_LOGIN_BEGIN = "select idLogin from logins where name = '";
    public static final String FIND_LOGIN_END = "';";
    public static final String FIND_TEST_ID_BEGIN = "select idTest from tests where name = '";
    public static final String FIND_TEST_ID_END = "';";

    public static final String INSERT_LOGIN_BEGIN = "insert into logins (name) values('";
    public static final String INSERT_LOGIN_END = "');";

    public static final String INSERT_TEST_ID_BEGIN = "insert into tests (name) values('";
    public static final String INSERT_TEST_ID_END = "');";

    public static final String ADD_RESULT = "insert into results (loginId, testId, dat, mark) values(?,?,?,?);";

    public static final String FIND_MEAN_INT_MARKS ="select logins.name as name, avg(results.mark) as mark\n" +
            "from results\n" +
            "inner join logins on loginId = logins.idlogin\n" +
            "group by loginId\n" +
            "order by mark desc;";
    public static final String FIND_MEAN_DOUBLE_MARKS ="select logins.name as name, avg(results.mark/10) as mark\n" +
            "from results\n" +
            "inner join logins on loginId = logins.idlogin\n" +
            "group by loginId\n" +
            "order by mark desc;";
    public static final String FIND_MEAN_HALF_MARKS ="select logins.name as name, avg(results.mark/2) as mark\n" +
            "from results\n" +
            "inner join logins on loginId = logins.idlogin\n" +
            "group by loginId\n" +
            "order by mark desc;";

    public static final String FIND_RESULTS = "select logins.name as name, tests.name as test, results.dat, results.mark\n" +
            "from results\n" +
            "inner join logins on loginId = logins.idlogin\n" +
            "inner join tests on testId = tests.idTest\n" +
            "where month(dat) = month(now()) and year(dat) = year(now())\n" +
            "order by results.mark; ";
}
