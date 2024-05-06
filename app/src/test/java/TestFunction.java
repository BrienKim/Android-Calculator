import function.FormulaEditor;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFunction {

    @Test
    public void TestTheCode()
    {
        String str = "-5";
        int index = str.replaceFirst( "-?\\d+\\.?\\d*\\D*$", " ").length() - 1;
        String sub = str.substring(index);


        assertEquals("", sub);
    }
}
