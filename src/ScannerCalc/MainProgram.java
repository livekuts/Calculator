package ScannerCalc;


import java.util.*;
import java.util.stream.Collectors;

public class MainProgram {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Object res2;
        String[] result = new String[2];
        String sep = new String();
//        System.out.println(str);
        if (str.contains("+")) {
            sep = "+";
            result = str.split("\\+");
        } else if (str.contains("-")) {
            sep = "-";
            result = str.split("\\-");
        } else if (str.contains("/")) {
            sep = "/";
            result = str.split("\\/");
        } else if (str.contains("*")) {
            sep = "*";
            result = str.split("\\*");
        } else {
            System.out.println("Please use +,-,/ or * signs!");
            System.exit(0);
        }
        res2 = CheckValues(result, sep);
        System.out.println(res2);
    }

    public static String calculateResult(int[] arrayOfValues, ArrayList arrayOfRomeNumbers, String separator, int numberOfOperation) {
        int result;
        int value1 = arrayOfValues[0];
        int value2 = arrayOfValues[1];
        Object objectResult = new Object();
        String stringResult = new String();
        if (separator == "+") {
            result = value1 + value2;
        } else if (separator == "-") {
            result = value1 - value2;
        } else if (separator == "/") {
            result = value1 / value2;
        } else {
            result = value1 * value2;
        }


        if (numberOfOperation == 2) {
            stringResult = arabicToRoman(result);
        } else {
            stringResult = String.valueOf(result);
        }

        return stringResult;
    }

    public static Object CheckValues(String[] arrayOfValues, String separator) {
        int[] arrayOfIntegerValues = new int[2];
        int operation = 0;
        Object result;
        boolean mixed1;
        boolean mixed2;

        ArrayList<String> romeNumbers = new ArrayList<String>();
        romeNumbers.add("I");
        romeNumbers.add("II");
        romeNumbers.add("III");
        romeNumbers.add("IV");
        romeNumbers.add("V");
        romeNumbers.add("VI");
        romeNumbers.add("VII");
        romeNumbers.add("VIII");
        romeNumbers.add("IX");
        romeNumbers.add("X");

        mixed1 = romeNumbers.contains(arrayOfValues[0]);
        mixed2 = romeNumbers.contains(arrayOfValues[1]);
        if ((mixed1 == false) & (mixed2 == false)) {
            for (int i = 0; i < arrayOfValues.length; i++) {
                int num = Integer.parseInt(arrayOfValues[i]);
                if ((num >= 1) & (num <= 10)) {
                    arrayOfIntegerValues[i] = num;
                    operation = 1;
                } else {
                    System.out.println("You can input numbers only from 1 to 10");
                    System.exit(0);
                }
            }
        } else if ((mixed1 == true) & (mixed2 == true)) {
            for (int i = 0; i < arrayOfValues.length; i++) {
                int figure = romeNumbers.indexOf(arrayOfValues[i]);
                figure = figure + 1;
                if ((figure >= 1) & (figure <= 10)) {
                    arrayOfIntegerValues[i] = figure;
                    operation = 2;
                } else {
                    System.out.println("You can input numbers only from I to X");
                    System.exit(0);
                }
            }
        } else {
            System.out.println("Don`t mix!!!You can input numbers only from I to X either only from 1 to 10");
            System.exit(0);
        }

        result = calculateResult(arrayOfIntegerValues, romeNumbers, separator, operation);

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

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

}


