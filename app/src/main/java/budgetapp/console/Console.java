package budgetapp.console;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Console implements IConsole {

    @Override
    public int readInt(String text) {
        Scanner s = new Scanner(System.in);
        int result;
        while (true) {
            try {
                System.out.println(text);
                result = s.nextInt();
                s.nextLine();
                break;
            } catch (Exception e) {
                s.nextLine();
                System.out.println("Invalid input. Try again :)");
            }
        }
        return result;
    }
    @Override
    public String readString(String text) {
        Scanner s = new Scanner(System.in);
        String result;
        while (true) {
            try {
                System.out.println(text);
                result = s.nextLine();
                break;
            } catch (Exception e) {
                s.nextLine();
                System.out.println("Invalid input.");
            }
        }
        return result.trim();
    }
    @Override
    public void printString(String input) {
        System.out.println(input);
    }
    /* Read integer if within the specified range*/
    public int readInt(String text, int lower, int upper) {
        Scanner s = new Scanner(System.in);
        int result;
        while (true) {
            try {
                System.out.println(text);
                result = s.nextInt();
                if (result >= lower && result <= upper) {
                    s.nextLine();
                    break;
                } else {
                    System.out.println("Invalid input. Try again :)");
                }
            } catch (Exception e) {
                s.nextLine();
                System.out.println("Invalid input. Try again :)");
            }
        }
        return result;
    }
    /* Read string if matches predefined pattern*/
    /* Date pattern yyyy-MM-dd: "^(19|20)\\d\\d[- \\/.](0[1-9]|1[012])[- \\/.](0[1-9]|[12][0-9]|3[01])$"*/
    /* Yes/No pattern (case insensitive: "^(?:Yes|no|y|n)\b" */
    public String readString(String text, String pattern) {
        Scanner s = new Scanner(System.in);
        String result;
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        while (true) {
            try {
                System.out.println(text);
                result = s.nextLine();
                Matcher matcher = p.matcher(result);
                if (matcher.find() || result.equals("")) {
                    break;
                }
                else {
                    System.out.println("Invalid input. Try again :)");
                }
            } catch (Exception e) {
                s.nextLine();
                System.out.println("Invalid input.");
            }
        }
        return result.trim();
    }
    public double readDouble(String text) {
        Scanner s = new Scanner(System.in);
        double result;
        while (true) {
            try {
                System.out.println(text);
                result = s.nextDouble();
                s.nextLine();
                break;
            } catch (Exception e) {
                s.nextLine();
                System.out.println("Invalid input.");
            }
        }
        return result;
    }
}
