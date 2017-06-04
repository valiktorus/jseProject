package by.gsu.epamlab.beans;

import by.gsu.epamlab.exceptions.MarkFormatExceptions;

public class Mark {
    private final int mark;

    public Mark() {
        this(0);
    }
    public Mark(String mark){
        this(convertStringToMark(mark));
    }

    public Mark(int mark) {
        this.mark = mark;
    }

    public int getIntMark() {
        return mark;
    }

    public Mark sum(Mark mark){
        return sum(mark.getIntMark());
    }

    public Mark sum(int mark){
        return new Mark(this.mark + mark);
    }



    private static int convertStringToMark(String mark){
        String[] markArray = mark.split("\\.");
        int resultMark;
        switch (markArray.length){
            case 1:
                resultMark = Integer.parseInt(markArray[0]);
                break;
            case 2:
                resultMark = Integer.parseInt(markArray[0] + markArray[1]);
                break;
            default:
                throw new MarkFormatExceptions();
        }
        return resultMark;
    }

    @Override
    public String toString() {
        return String.valueOf(mark);
    }
}
