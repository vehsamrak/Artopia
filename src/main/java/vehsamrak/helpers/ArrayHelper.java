package vehsamrak.helpers;

import java.util.ArrayList;

/**
 * @author Rottenwood
 */
public class ArrayHelper {

    /**
     * Преобразование ArrayList в строку
     * @param arrayList ArrayList
     * @return String
     */
    public static String join(ArrayList<String> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : arrayList) {
            stringBuilder.append(s);
        }

        return stringBuilder.toString();
    }
}
