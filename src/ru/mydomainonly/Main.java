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

        // DigitsTable
        Map<String, Integer> arabicHashMap = new HashMap<>();
        arabicHashMap.put("0", 0);
        arabicHashMap.put("1", 1);
        arabicHashMap.put("2", 2);
        arabicHashMap.put("3", 3);
        arabicHashMap.put("4", 4);
        arabicHashMap.put("5", 5);
        arabicHashMap.put("6", 6);
        arabicHashMap.put("7", 7);
        arabicHashMap.put("8", 8);
        arabicHashMap.put("9", 9);
        arabicHashMap.put("10", 10);
        arabicHashMap.put("11", 11);
        arabicHashMap.put("12", 12);
        arabicHashMap.put("13", 13);
        arabicHashMap.put("14", 14);
        arabicHashMap.put("15", 15);
        arabicHashMap.put("16", 16);
        arabicHashMap.put("17", 17);
        arabicHashMap.put("18", 18);
        arabicHashMap.put("19", 19);

        Map<String, Integer> romeHashMap = new HashMap<>();
        romeHashMap.put("0", 0);
        romeHashMap.put("I", 1);
        romeHashMap.put("II", 2);
        romeHashMap.put("III", 3);
        romeHashMap.put("IV", 4);
        romeHashMap.put("V", 5);
        romeHashMap.put("VI", 6);
        romeHashMap.put("VII", 7);
        romeHashMap.put("VIII", 8);
        romeHashMap.put("IX", 9);
        romeHashMap.put("X", 10);
        romeHashMap.put("XI", 11);
        romeHashMap.put("XII", 12);
        romeHashMap.put("XIII", 13);
        romeHashMap.put("XIV", 14);
        romeHashMap.put("XV", 15);
        romeHashMap.put("XVI", 16);
        romeHashMap.put("XVII", 17);
        romeHashMap.put("XVIII", 18);
        romeHashMap.put("XIX", 19);

        Map<String, Integer> digitalMap = null;
        if(arabicHashMap.containsKey(subStr[0]) && arabicHashMap.containsKey(subStr[1])) {
            digitalMap = arabicHashMap;
        }
        if(romeHashMap.containsKey(subStr[0]) && romeHashMap.containsKey(subStr[1])) {
            digitalMap = romeHashMap;
        }
        if( digitalMap == null ) throw new IllegalArgumentException("Invalid input line");
        if( digitalMap.get(subStr[0]) > 9 || digitalMap.get(subStr[1]) > 9 ) throw new IllegalArgumentException("Too big digital");

        int outValue;
        if( inExpression.contains("+") ) {
            outValue = digitalMap.get(subStr[0]) + digitalMap.get(subStr[1]);
        } else if (inExpression.contains("-")) {
            outValue = digitalMap.get(subStr[0]) - digitalMap.get(subStr[1]);
        } else if (inExpression.contains("*")) {
            outValue = digitalMap.get(subStr[0]) * digitalMap.get(subStr[1]);
        } else if (inExpression.contains("/")) {
            if( digitalMap.get(subStr[1]) == 0 ) throw new IllegalArgumentException("Invalid input line");
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