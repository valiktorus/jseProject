package by.gsu.epamlab.DbFiller.impl;

import by.gsu.epamlab.DbFiller.DbFiller;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.connector.DbConnector;
import by.gsu.epamlab.enums.EnumMark;
import by.gsu.epamlab.exceptions.DbConnectorException;
import by.gsu.epamlab.handler.ResultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class XmlDbFiller implements DbFiller{
    private String fileName;
    DbConnector connector;
    private XMLReader reader;
    private ResultHandler resultHandler;
    private EnumMark enumMark;

    public XmlDbFiller(DbConnector connector, String fileName, EnumMark enumMark) {
        this.connector = connector;
        this.fileName = fileName;
        this.enumMark = enumMark;
        resultHandler = new ResultHandler(enumMark);
        try {
            reader = XMLReaderFactory.createXMLReader();
        } catch (SAXException e) {
            System.err.println("SAX parser error" + e.getMessage());
        }
        reader.setContentHandler(resultHandler);
    }

    @Override
    public void fillDbFromFile() throws DbConnectorException {
        try {
            reader.parse(fileName);
        } catch (IOException e) {
            System.err.println("Error in I/O " + e.getMessage());
        } catch (SAXException e) {
            System.err.println("Error of SAX parser " + e.getMessage());
        }
        fillDbFromList(resultHandler.getResults());
    }
    private void fillDbFromList(List<Result> results) throws DbConnectorException {
        for (Result result: results) {
            connector.addResult(result);
        }
    }
}
