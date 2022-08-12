package test;

    /* This program will convert Roman numerals to ordinary arabic numerals
       and vice versa.  The user can enter a numerals of either type.  Arabic
       numerals must be in the range from 1 to 3999 inclusive.  The user ends
       the program by entering an empty line.
    */

//System.out.println(str.matches("(X|IX|IV|V?I{0,3})\\s[-+*/]\\s(X|IX|IV|V?I{0,3})$")); // (I-X) + пробел + (мат. операция) + пробел + (I-X)
//System.out.println(str.matches("(0|1|2|3|4|5|6|7|8|9|10)\\s[-+*/]\\s(0|1|2|3|4|5|6|7|8|9|10)")); // (0-10) + пробел + (мат. операция) + пробел + (0-10)

import java.util.Arrays;
import java.util.Scanner;

public class RomanConverter {

    public static void main(String[] args) {

        String str = ""; //для расчётов
        Scanner scanner = new Scanner(System.in);

        while (true) { //бесконечные вызовы пока не будет введена команда stop

            System.out.print("[Калькулятор] Пожалуйста введите данные для расчётов: ");
            str = scanner.nextLine();// читаем строку

            if (str.equalsIgnoreCase("stop")) {// сразу проверка на выход
                System.out.println("Выход из программы");
                return;// выход из метода
            }

            try {// работа программы
                System.out.println(calc(str));

            } catch (Exception e) {//исключение
                System.out.println("Программа завершена");
                System.err.println("[Ошибка] " + e.getMessage());
                return;//выход из метода
            }
        }
    }

    public static String calc(String input) throws Exception {
        if (input.matches("(X|IX|IV|V?I{0,3})\\s[-+*/]\\s(X|IX|IV|V?I{0,3})$")) { // (I-X) + пробел + (мат. операция) + пробел + (I-X) - РИМ
            //Расчитать, тут всё правильно
            //Исключение, если число отрицательное

            int num = 0; // использую для вывода расчётов

            String[] arStr = input.split(" ");

            switch (arStr[1]) {// 2-ой элемент (-+*/)
                case "-": num = new RomanNumeral(arStr[0]).toInt() - new RomanNumeral(arStr[2]).toInt(); break;
                case "+": num = new RomanNumeral(arStr[0]).toInt() + new RomanNumeral(arStr[2]).toInt(); break;
                case "*": num = new RomanNumeral(arStr[0]).toInt() * new RomanNumeral(arStr[2]).toInt(); break;
                case "/": num = new RomanNumeral(arStr[0]).toInt() / new RomanNumeral(arStr[2]).toInt(); break;

            }
            if (num < 0) {
                throw new Exception("Исключение, число отрицательное");
            }
            return new RomanNumeral(num).toString();
        }
        else if (input.matches("(1|2|3|4|5|6|7|8|9|10)\\s[-+*/]\\s(1|2|3|4|5|6|7|8|9|10)$")) {// (1-10) + пробел + (мат. операция) + пробел + (1-10) - АРАБИК
            //Расчитать, тут всё правильно
            int num = 0;// использую для вывода расчётов

            String[] arStr = input.split(" ");
            switch (arStr[1]) {// 2-ой элемент (-+*/)
                case "-": num = Integer.parseInt(arStr[0]) - Integer.parseInt(arStr[2]); break;
                case "+": num = Integer.parseInt(arStr[0]) + Integer.parseInt(arStr[2]); break;
                case "*": num = Integer.parseInt(arStr[0]) * Integer.parseInt(arStr[2]); break;
                case "/": num = Integer.parseInt(arStr[0]) / Integer.parseInt(arStr[2]); break;
            }
            return String.valueOf(num);
        }

        // ДАльше только исключения и ошибки

        else if (input.matches("(X|IX|IV|V?I{0,3})\\s[-+*/]\\s(1|2|3|4|5|6|7|8|9|10)$")) { // если так
            throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления");
        }
        else if (input.matches("(1|2|3|4|5|6|7|8|9|10)\\s[-+*/]\\s(X|IX|IV|V?I{0,3})$")) { // или так
            throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления");
        }


        else if (input.matches("(X|IX|IV|V?I{0,3})\\s[-+*/]\\s(X|IX|IV|V?I{0,3})\\s(X|IX|IV|V?I{0,3}).+")) { // I + I + .....
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        else if (input.matches("(1|2|3|4|5|6|7|8|9|10)\\s[-+*/]\\s(1|2|3|4|5|6|7|8|9|10).+")){// 1 + 1 + ....
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        else { // И для других ошибок
            throw new Exception("Строка не являеться математической операцией");
        }
    }
}
