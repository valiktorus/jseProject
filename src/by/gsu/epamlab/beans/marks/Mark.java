package by.gsu.epamlab.beans.marks;

public abstract class Mark {
    public static final String DELIMITER_REGEX = "\\.";
    public static final int MARK_ARRAY_LENGTH = 1;
    public static final int MARK_FIRST_NUM_INDEX = 0;
    public static final int MARK_SECOND_NUM_INDEX = 1;
    protected int mark;

    public Mark() {
        this(0);
    }

    public Mark(int mark) {
        this.mark = mark;
    }

    public int getIntMark() {
        return mark;
    }

    @Override
    public String toString() {
        return String.valueOf(mark);
    }
}
