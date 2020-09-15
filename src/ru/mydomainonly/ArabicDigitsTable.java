package ru.mydomainonly;

import java.util.HashMap;

public class ArabicDigitsTable extends DigitsTable {
    public ArabicDigitsTable() {
        DigitalHashMap = new HashMap<>();
        DigitalHashMap.put("0", 0);
        DigitalHashMap.put("1", 1);
        DigitalHashMap.put("2", 2);
        DigitalHashMap.put("3", 3);
        DigitalHashMap.put("4", 4);
        DigitalHashMap.put("5", 5);
        DigitalHashMap.put("6", 6);
        DigitalHashMap.put("7", 7);
        DigitalHashMap.put("8", 8);
        DigitalHashMap.put("9", 9);
        DigitalHashMap.put("10", 10);
    }

    public String toString(Integer number) {
        return number.toString();
    }
}
