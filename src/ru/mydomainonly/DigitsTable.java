package ru.mydomainonly;

import java.util.Map;

public abstract class DigitsTable {
    Map<String, Integer> DigitalHashMap = null;

    public Map<String, Integer> getDigitalHashMap() {
        return this.DigitalHashMap;
    }
    public abstract String toString(Integer value);
}

