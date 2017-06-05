package by.gsu.epamlab.beans.marks;

public class DoubleMark extends Mark{

    public static final String MARK_FORMAT = "%d.%d";

    public DoubleMark() {
    }

    public DoubleMark(String mark){
        String[] markArray = mark.split(DELIMITER_REGEX);
        if (markArray.length == MARK_ARRAY_LENGTH){
            this.mark = Integer.parseInt(markArray[MARK_FIRST_NUM_INDEX]);
        }else {
            this.mark = Integer.parseInt(markArray[MARK_FIRST_NUM_INDEX] + markArray[MARK_SECOND_NUM_INDEX]);
        }
    }

    public DoubleMark(int mark) {
        super(mark);
    }

    @Override
    public String toString() {
        return String.format(MARK_FORMAT, mark/10, mark%10);
    }

}
