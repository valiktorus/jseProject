package by.gsu.epamlab.handler;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.beans.Student;
import by.gsu.epamlab.enums.EnumMark;
import by.gsu.epamlab.factory.MarkFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

public class ResultHandler extends DefaultHandler {

    public static final int TEST_INDEX = 0;
    public static final int DATE_INDEX = 1;
    public static final int MARK_INDEX = 2;

    private enum ResultsEnum {
        RESULTS("results"),
        STUDENT("student"),
        LOGIN("login"),
        TESTS("tests"),
        TEST("test");

        private String value;

        ResultsEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    private List<Result> results;
    private Result currentResult = null;
    private Student currentStudent = null;
    private ResultsEnum currentEnum = null;
    private EnumMark enumMark = null;

    public ResultHandler(EnumMark enumMark) {
        results = new LinkedList<>();
        this.enumMark = enumMark;
    }

    public List<Result> getResults() {
        return results;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentEnum = ResultsEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultsEnum.TEST){
            currentResult = new Result(currentStudent, attributes.getValue(TEST_INDEX), attributes.getValue(DATE_INDEX), MarkFactory.getMark(enumMark,attributes.getValue(MARK_INDEX)));
            results.add(currentResult);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentEnum == ResultsEnum.LOGIN){
            String value = new String(ch, start, length).trim();
            if (!value.isEmpty()){
                currentStudent = new Student(value);
            }
        }
    }
}
