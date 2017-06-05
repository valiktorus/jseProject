package by.gsu.epamlab.util;

import by.gsu.epamlab.beans.Result;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ResultPrinterUtil {
    public static <E> void printList(List<E> list){
        for (E element: list) {
            System.out.println(element);
        }
    }
    public static void printLatestDayResults(List<Result> results){
        List<Result> latestDayResults = new LinkedList<>();
        long latestTime = 0;
        for (Result result: results) {
            Date date = result.getDate();
            long time = date.getTime();
            if (time > latestTime){
                latestTime = time;
            }
        }
        for (Result result: results) {
            if (result.getDate().getTime() == latestTime){
                latestDayResults.add(result);
            }
        }
        System.out.println("Latest day results");
        printList(latestDayResults);
    }
}
