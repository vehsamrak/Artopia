package vehsamrak.helper;

import java.util.ArrayList;

/**
 * @author Rottenwood
 */
public final class ArrayHelper {

    /**
     * Преобразование ArrayList в строку
     * @param arrayList ArrayList
     * @param delimiter Разделитель
     * @return String
     */
    public static String join(ArrayList<String> arrayList, String delimiter) {
        StringBuilder stringBuilder = new StringBuilder();

        int i = 1;
        for (String s : arrayList) {
            stringBuilder.append(s);

            if (i++ != arrayList.size()) {
                stringBuilder.append(delimiter);
            }
        }

        return stringBuilder.toString();
    }
}
