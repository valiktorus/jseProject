package by.gsu.epamlab.beans.marks;

/**
 * Created by Torus on 05.06.2017.
 */
public class IntMark extends Mark{
    public IntMark() {
    }

    public IntMark(String mark) {
        super(Integer.parseInt(mark));
    }

    public IntMark(int mark) {
        super(mark);
    }

}
