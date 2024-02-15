import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String input = sc.nextLine();
        System.out.println("Результат выражения = " + calc(input));
    }

    public static String calc(String input) {
        String[] numberMatOper = input.split(" ");
        if (numberMatOper.length != 3) {
            throw new NumberFormatException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String num1 = numberMatOper[0];
        String num2 = numberMatOper[2];
        char operations = numberMatOper[1].charAt(0);
        switch (operations) {
            case '+':
                operations = '+';
                break;
            case '-':
                operations = '-';
            case '*':
                operations = '-';
                break;
            case '/':
                operations = '-';
                break;
            default:
                operations = '&';
                break;
        }
        if (operations == '&') {
            throw new IllegalArgumentException("Вы ввели неверный оператор. Попробуйте снова!");
        }
        if (isRomanNumbers(num1) && isRomanNumbers(num2)) {
            int result = getSumm(romanToInteger(num1), romanToInteger(num2), operations);
            if(result < 1) {
                throw new NumberFormatException("Результат отрицательный, попробуйте повторно выполнить рассчет.");
            }
            return intToRoman(getSumm(romanToInteger(num1), romanToInteger(num2), operations));
        } else return " " + getSumm(Integer.parseInt(num1), Integer.parseInt(num2), operations);
    }
    public static int getSumm(int x1, int x2, char oper) {
        if (x1 > 10 || x2 > 10 || x1 < 0 || x2 < 0) {
            throw new NumberFormatException("Калькулятор умеет работать только целыми числами от 1 до 10");
        }
        int result = 0;
        switch (oper){
            case '+':
                 result = x1 + x2;
                break;
            case '-':
                 result = x1 - x2;
                 break;
            case '*':
                 result = x1 * x2;
                break;
            case '/':
                result = x1 - x2;
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }
    public static boolean isRomanNumbers(String string) {
        return string.matches("^M*(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    }
    public static int romanToInteger(String roman) {
        Map<Character,Integer> numbersMap = new HashMap<>();
        numbersMap.put('I', 1);
        numbersMap.put('V', 5);
        numbersMap.put('X', 10);
        numbersMap.put('L', 50);
        numbersMap.put('C', 100);
        numbersMap.put('D', 500);
        numbersMap.put('M', 1000);

        final int[] result = {0};
        IntStream.range(0, roman.length()).forEach(i -> {
            char c = roman.charAt(i);
            char next = i < roman.length() - 1 ? roman.charAt(i + 1) : ' ';
            if (numbersMap.get(c) < numbersMap.getOrDefault(next, 0)) result[0] -= numbersMap.get(c);
            else result[0] += numbersMap.get(c);
        });
        return result[0];
    }
    public static String intToRoman(int num){
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLetters = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();
        for(int i=0;i<values.length;i++)
        {
            while(num >= values[i])
            {
                num = num - values[i];
                roman.append(romanLetters[i]);
            }
        }
        return roman.toString();
    }
    }
