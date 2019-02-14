package com.gmail.granovskiy.s;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Calculator {
    public static String ReversePolishNotation(String expression) throws Exception {
        StringBuilder operatorsStack = new StringBuilder();
        StringBuilder operandsOut = new StringBuilder();
        char symbol;
        char symbolTemp = ' ';
        for (int i = 0; i < expression.length(); i++) {
            symbol = expression.charAt(i);
            if (isOperator(symbol)) {
                appendElement(operatorsStack, symbolTemp, symbol, operandsOut);
                if (i == 0 && isOperator(symbol)) {
                    operandsOut.append(symbol);
                } else {
                    operandsOut.append(" ");
                    operatorsStack.append(symbol);
                }
            } else if ('(' == symbol) {
                operatorsStack.append(symbol);
            } else if (')' == symbol) {
                doSomething(symbolTemp, operatorsStack, operandsOut);
                operatorsStack.setLength(operatorsStack.length() - 1);
            } else {
                operandsOut.append(symbol);
            }
        }
        while (operatorsStack.length() > 0) {
            operandsOut.append(" ").append(operatorsStack.substring(operatorsStack.length() - 1));
            operatorsStack.setLength(operatorsStack.length() - 1);
        }
        return operandsOut.toString();
    }

    private static void doSomething(char symbolTemp, StringBuilder operatorsStack, StringBuilder operandsOut) throws Exception {
        symbolTemp = operatorsStack.substring(operatorsStack.length() - 1).charAt(0);
        while ('(' != symbolTemp) {
            if (operatorsStack.length() < 1) {
                throw new Exception("Error when opening parenthesis.");
            }
            operandsOut.append(" ").append(symbolTemp);
            operatorsStack.setLength(operatorsStack.length() - 1);
            symbolTemp = operatorsStack.substring(operatorsStack.length() - 1).charAt(0);
        }
    }

    private static void appendElement(StringBuilder operatorsStack, char symbolTemp, char symbol, StringBuilder operandsOut) {
        while (operatorsStack.length() > 0) {
            symbolTemp = operatorsStack.substring(operatorsStack.length() - 1).charAt(0);
            if (isOperator(symbolTemp) && (priorOperation(symbol) <= priorOperation(symbolTemp))) {
                operandsOut.append(" ").append(symbolTemp).append(" ");
                operatorsStack.setLength(operatorsStack.length() - 1);
            } else {
                operandsOut.append(" ");
                break;
            }
        }
    }

    public static boolean isOperator(char symbol) {
        switch (symbol) {
            case '-':
            case '+':
            case '*':
            case '/':
            case '^':
                return true;
        }
        return false;
    }

    private static byte priorOperation(char prior) {
        switch (prior) {
            case '^':
                return 3;
            case '*':
            case '/':
            case '%':
                return 2;
        }
        return 1;
    }

    public static double calculate(String expresion, int type) throws Exception {
        String symbolTemp = "";
        double firstNumber = 0;
        double secondNumber = 0;
        Deque<Double> stack = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(expresion);
        //   breaking a string into tokens
        while (st.hasMoreTokens()) {
            try {
                pushNumbers(symbolTemp, firstNumber, secondNumber, st, type, stack);
            } catch (Exception e) {
                throw new Exception("Wrong symbol in the expression.");
            }
        }
        if (stack.size() > 1) {
            throw new Exception("Amount of operators is not equal to the amount of operands.");
        }
        return stack.pop();
    }

    private static void pushNumbers(String symbolTemp, double firstNumber, double secondNumber, StringTokenizer st, int type, Deque<Double> stack) throws Exception {
        symbolTemp = st.nextToken().trim();
        if (1 == symbolTemp.length() && isOperator(symbolTemp.charAt(0))) {
            if (stack.size() < 2) {
                throw new Exception("Wrong amount of data in the stack for operations" + symbolTemp);
            }
            secondNumber = stack.pop();
            firstNumber = stack.pop();
            switch (symbolTemp.charAt(0)) {
                case '+':
                    firstNumber += secondNumber;
                    break;
                case '-':
                    firstNumber -= secondNumber;
                    break;
                case '/':
                    firstNumber /= secondNumber;
                    break;
                case '*':
                    firstNumber *= secondNumber;
                    break;
                case '%':
                    firstNumber %= secondNumber;
                    break;
                case '^':
                    firstNumber = Math.pow(firstNumber, secondNumber);
                    break;
                default:
                    throw new Exception("Operation impossible" + symbolTemp);
            }
            stack.push(firstNumber);
        } else {
            if (type == 1) {
                firstNumber = Double.parseDouble(symbolTemp);
                stack.push(firstNumber);
            } else {
                firstNumber = Converter.convertRomanToDecimal(symbolTemp);
                stack.push(firstNumber);
            }
        }
    }
}
