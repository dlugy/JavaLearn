package ru.mydomainonly;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100),
    CD(400), D(500), CM(900), M(1000);

    private int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<RomanNumeral> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                .collect(Collectors.toList());
    }
}

public class RomeDigitsTable extends DigitsTable {
    public RomeDigitsTable() {
        DigitalHashMap = new HashMap<>();
        DigitalHashMap.put("0", 0);
        DigitalHashMap.put("I", 1);
        DigitalHashMap.put("II", 2);
        DigitalHashMap.put("III", 3);
        DigitalHashMap.put("IV", 4);
        DigitalHashMap.put("V", 5);
        DigitalHashMap.put("VI", 6);
        DigitalHashMap.put("VII", 7);
        DigitalHashMap.put("VIII", 8);
        DigitalHashMap.put("IX", 9);
        DigitalHashMap.put("X", 10);
    }

    public String toString(Integer number) {
        if ((number < 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }
        if( number == 0 ) return "0";

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }
}
