package com.gmail.granovskiy.s;

public class Converter {
    public static int convertRomanToDecimal(String romanNumber) {
        int decimal = 0;
        int lastNumber = 0;
        String romanNumeral = romanNumber.toUpperCase();
        for (int i = romanNumeral.length() - 1; i >= 0; i--) {
            char convertDoDecimal = romanNumeral.charAt(i);
            switch (convertDoDecimal) {
                case 'M':
                    decimal = processDecimal(1000, lastNumber, decimal);
                    lastNumber = 1000;
                    break;
                case 'D':
                    decimal = processDecimal(500, lastNumber, decimal);
                    lastNumber = 500;
                    break;
                case 'C':
                    decimal = processDecimal(100, lastNumber, decimal);
                    lastNumber = 100;
                    break;
                case 'L':
                    decimal = processDecimal(50, lastNumber, decimal);
                    lastNumber = 50;
                    break;
                case 'X':
                    decimal = processDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                    break;
                case 'V':
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    break;
                case 'I':
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    break;
            }
        }
        return Calculator.isOperator(romanNumber.charAt(0)) ? -decimal : decimal;
    }

    private static int processDecimal(int decimal, int lastNumber, int lastDecimal) {
        if (decimal > lastNumber) {
            return lastDecimal + decimal;
        } else {
            return lastDecimal - decimal;
        }
    }

    public static String convertDecimalToRoman(double decimalNumber) {
        StringBuilder romanDecimal = new StringBuilder();
        double decimal = decimalNumber >= 0 ? decimalNumber : -decimalNumber;
        while (decimal > 0) {
            if (decimal >= 1000) {
                romanDecimal.append("M");
                decimal -= 1000;
                continue;
            }
            if (decimal >= 900) {
                romanDecimal.append("CM");
                decimal -= 900;
                continue;
            }
            if (decimal >= 500) {
                romanDecimal.append("D");
                decimal -= 500;
                continue;
            }
            if (decimal >= 400) {
                romanDecimal.append("CD");
                decimal -= 400;
                continue;
            }
            if (decimal >= 100) {
                romanDecimal.append("C");
                decimal -= 100;
                continue;
            }
            if (decimal >= 90) {
                romanDecimal.append("XC");
                decimal -= 90;
                continue;
            }
            if (decimal >= 50) {
                romanDecimal.append("L");
                decimal -= 50;
                continue;
            }
            if (decimal >= 40) {
                romanDecimal.append("XL");
                decimal -= 40;
                continue;
            }
            if (decimal >= 10) {
                romanDecimal.append("X");
                decimal -= 10;
                continue;
            }
            if (decimal >= 9) {
                romanDecimal.append("IX");
                decimal -= 9;
                continue;
            }
            if (decimal >= 5) {
                romanDecimal.append("V");
                decimal -= 5;
                continue;
            }
            if (decimal >= 4) {
                romanDecimal.append("IV");
                decimal -= 4;
                continue;
            }
            if (decimal >= 1) {
                romanDecimal.append("I");
                decimal -= 1;
            }
        }
        return decimalNumber >= 0 ? romanDecimal.toString() : "-" + romanDecimal;
    }
}
