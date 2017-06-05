package by.gsu.epamlab.beans.marks;

/**
 * Created by Torus on 05.06.2017.
 */
public class HalfMark extends Mark {
    public HalfMark() {
    }

    public HalfMark(String mark) {
        String[] markArray = mark.split(DELIMITER_REGEX);
        if (markArray.length == MARK_ARRAY_LENGTH) {
            this.mark = Integer.parseInt(markArray[MARK_FIRST_NUM_INDEX]);
        }else {
            this.mark = (int) (Double.parseDouble(mark) * 2);
        }
    }

    public HalfMark(int mark) {
        super(mark);
    }

    @Override
    public String toString() {
        int doubleMark = mark/2;
        return String.format("%d.%d", mark/2, (mark%2)*5);
    }
}
