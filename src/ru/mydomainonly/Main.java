package ru.mydomainonly;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner in = new Scanner(System.in);
        System.out.print("Enter expression to calculate: ");

        String inExpression = in.nextLine().replaceAll("\\s+","").toUpperCase();

        String[] subStr;
        subStr = inExpression.split("[+\\-*/]");

        // Validate input arguments
        if( subStr.length != 2 ) throw new IllegalArgumentException("Invalid input line");
        // Не более 1 оператора
        if( inExpression.length()+1 != inExpression.replaceAll("[+\\-*/]","12").length() ) throw new IllegalArgumentException("Invalid input line");
        if( subStr[0].length() < 1 ) throw new IllegalArgumentException("Invalid input line");

        ArabicDigitsTable arabicDigitsTable = new ArabicDigitsTable();
        RomeDigitsTable romeDigitsTable = new RomeDigitsTable();

        // Get number system for calculate
        DigitsTable digitsTable = null;
        if(ProperNumberSystem(romeDigitsTable,subStr[0],subStr[1])) {
            digitsTable = romeDigitsTable;
        }
        if(ProperNumberSystem(arabicDigitsTable,subStr[0],subStr[1])) {
            digitsTable = arabicDigitsTable;
        }
        if( digitsTable == null ) throw new IllegalArgumentException("Different number system or wrong arguments");

        Map<String, Integer> digitalMap;
        digitalMap = digitsTable.getDigitalHashMap();

        if( digitalMap.get(subStr[0]) > 10 || digitalMap.get(subStr[1]) > 10 ||
                digitalMap.get(subStr[0]) < 1 || digitalMap.get(subStr[1]) < 1) throw new IllegalArgumentException("Numbers must be between 1 and 10");

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

        if( outValue >= 0 ) {
            System.out.printf("Вычисленное значение: %s\n", digitsTable.toString(outValue));
        } else {
            System.out.printf("Вычисленное значение: -%s\n", digitsTable.toString(-outValue));
        }
    }

    public static boolean ProperNumberSystem(DigitsTable digitsTable, String firstNumber, String secondNumber) {
        return digitsTable.getDigitalHashMap().containsKey(firstNumber) &&
                digitsTable.getDigitalHashMap().containsKey(secondNumber);
    }
}