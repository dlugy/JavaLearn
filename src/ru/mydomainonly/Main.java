package ru.mydomainonly;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner in = new Scanner(System.in);
        System.out.print("Enter value: ");

        String inExpression = in.nextLine().replaceAll("\\s+","").toUpperCase();

        String[] subStr;
        subStr = inExpression.split("[+\\-*/]");

        // Validate input arguments
        if( subStr.length != 2 ) throw new IllegalArgumentException("Invalid input line");
        if( inExpression.length()+1 != inExpression.replaceAll("[+\\-*/]","12").length() ) throw new IllegalArgumentException("Invalid input line");
        if( subStr[0].length() < 1 ) throw new IllegalArgumentException("Invalid input line");

        ArabicDigitsTable arabicDigitsTable = new ArabicDigitsTable();
        RomeDigitsTable romeDigitsTable = new RomeDigitsTable();

        Map<String, Integer> digitalMap = null;
        if(arabicDigitsTable.getDigitalHashMap().containsKey(subStr[0]) && arabicDigitsTable.getDigitalHashMap().containsKey(subStr[1])) {
            digitalMap = arabicDigitsTable.getDigitalHashMap();
        }
        if(romeDigitsTable.getDigitalHashMap().containsKey(subStr[0]) && romeDigitsTable.getDigitalHashMap().containsKey(subStr[1])) {
            digitalMap = romeDigitsTable.getDigitalHashMap();
        }
        if( digitalMap == null ) throw new IllegalArgumentException("Different number system or wrong arguments");
        if( digitalMap.get(subStr[0]) > 9 || digitalMap.get(subStr[1]) > 9 ) throw new IllegalArgumentException("Too big digital");

        int outValue;
        if( inExpression.contains("+") ) {
            outValue = digitalMap.get(subStr[0]) + digitalMap.get(subStr[1]);
        } else if (inExpression.contains("-")) {
            outValue = digitalMap.get(subStr[0]) - digitalMap.get(subStr[1]);
        } else if (inExpression.contains("*")) {
            outValue = digitalMap.get(subStr[0]) * digitalMap.get(subStr[1]);
        } else if (inExpression.contains("/")) {
            if( digitalMap.get(subStr[1]) == 0 ) throw new IllegalArgumentException("Divide by zero");
            outValue = digitalMap.get(subStr[0]) / digitalMap.get(subStr[1]);
        } else throw new IllegalArgumentException("Invalid input line");

        Integer finalOutValue = outValue;
        Optional<String> result = digitalMap.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), finalOutValue))
                .map(Map.Entry::getKey)
                .findFirst();

        if(result.isPresent() ) {
            System.out.printf("Вычисленное значение: %s\n", result.get());
        } else {
            throw new IllegalArgumentException("В таблице соответствий чисел и изображений не найден результат вычисления");
        }
    }
}