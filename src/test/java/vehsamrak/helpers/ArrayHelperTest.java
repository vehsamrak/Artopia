package vehsamrak.helpers;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author Rottenwood
 */
public class ArrayHelperTest extends Assert {

    @Test
    public void join_ArrayListWithStrings_aggregatedString() {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("First string");
        arrayList.add("Second string");
        arrayList.add("Third string");

        String resultString = ArrayHelper.join(arrayList, ", ");

        assertEquals("First string, Second string, Third string", resultString);
    }
}