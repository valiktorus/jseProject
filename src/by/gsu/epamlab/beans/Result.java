package by.gsu.epamlab.beans;

import by.gsu.epamlab.beans.marks.Mark;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Result {
    private final static SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private Student student;
    private String test;
    private Date date;
    private Mark mark;

   /* public Result(String login, String test, String date, String mark){
        this(new Student(login),test, Date.valueOf(date), new Mark(mark));
    }
*/
    public Result(Student student, String test, String date, Mark mark) {
        this(student, test, Date.valueOf(date), mark);
    }

    public Result(String login, String test, String date, Mark mark) {
        this(new Student(login), test, Date.valueOf(date), mark);
    }

    public Result(Student student, String test, Date date, Mark mark) {
        this.student = student;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    private String getStringDate() {
        return OUTPUT_DATE_FORMAT.format(date);
    }
    @Override
    public String toString() {
        return student + ";" + test  + ";" +  getStringDate()  + ";" +  mark;
    }
}
