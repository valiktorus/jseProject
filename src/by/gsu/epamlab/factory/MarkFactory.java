package by.gsu.epamlab.factory;

import by.gsu.epamlab.beans.marks.DoubleMark;
import by.gsu.epamlab.beans.marks.HalfMark;
import by.gsu.epamlab.beans.marks.IntMark;
import by.gsu.epamlab.beans.marks.Mark;
import by.gsu.epamlab.enums.EnumMark;

public class MarkFactory {
    public static Mark getMark(EnumMark enumMark, String value){
        Mark mark = null;
        switch (enumMark){
            case INT_MARK:
                mark = new IntMark(value);
                break;
            case HALF_MARK:
                mark = new HalfMark(value);
                break;
            case DOUBLE_MARK:
                mark = new DoubleMark(value);
                break;
        }
        return mark;
    }
}
