package com.gmail.granovskiy.s;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleInputOutput {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String expression;
        int type;
        try {
            System.out.println("Please, enter 1 for Arabic numbers or enter 2 for Roman numbers.");
            type = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter expression to calculate:");
            expression = bufferedReader.readLine();
            expression = Calculator.ReversePolishNotation(expression);
            if (type == 1) {
                System.out.print(Calculator.calculate(expression, type));
            } else if (type == 2) {
                System.out.print(Converter.convertDecimalToRoman(Calculator.calculate(expression, type)));
            } else {
                System.out.println("You entered wrong type of numbers.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
