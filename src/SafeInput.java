import java.util.Scanner;

public class SafeInput {

    // get non-empty string input using scanner:
    public static String getNonZeroLenString(Scanner scanner, String prompt) {
        String retString;
        do {
            System.out.print(prompt); // show prompt add space
            retString = scanner.nextLine();
        } while (retString.isBlank());
        // same as: while (retString.trim().length() == 0); //  length() method in Java counts all characters in the string, including white spaces.
        // same as: while (retString.trim().isEmpty()); // isEmpty() method in Java checks if a string has a length of 0. It does not ignore or trim white spaces.

        return retString.trim();

    }

    // get a valid integer input using scanner:
    public static int getInt(Scanner scanner, String prompt) {
        int intNum = 0;
        boolean validInt = false;
        do {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                intNum = scanner.nextInt();
                validInt = true;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        } while (!validInt);

        return intNum;
    }

    // get a valid double input using scanner:
    public static double getDouble(Scanner scanner, String prompt) {
        double doubleNum = 0;
        boolean validDouble = false;
        do {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                doubleNum = scanner.nextDouble();
                validDouble = true;
            } else {
                System.out.println("Invalid input. Please enter a decimal number.");
                scanner.next();
            }
        } while (!validDouble);

        return doubleNum;
    }

    // get a valid integer from a given range:
    public static int getRangedInt(Scanner scanner, String prompt, int low, int high) {
        int userInt = 0;
        boolean validInt = false;
        do {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                userInt = scanner.nextInt();
                if (userInt >= low && userInt <= high) {
                    validInt = true;
                } else {
                    System.out.println("Invalid input. Please enter an integer between " + low + " and " + high + ".");
                    scanner.nextLine();
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        } while (!validInt);

        return userInt;
    }

    // get a valid double from a given range:
    public static double getRangedDouble(Scanner scanner, String prompt, double low, double high) {
        double inputDouble = 0;
        boolean validDouble = false;
        do {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                inputDouble = scanner.nextDouble();
                if (inputDouble >= low && inputDouble <= high) {
                    validDouble = true;
                } else {
                    System.out.println("Invalid input. Please enter a decimal number between " + low + " and " + high + ".");
                    scanner.nextLine();
                }
            }
            else  {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        } while (!validDouble);

        return inputDouble;
    }

    // get a valid Y or N response from users:
    public static boolean getYNConfirm(Scanner scanner, String prompt) {
        String userResponse;
        boolean validResponse = false;
        boolean confirm = false;
        do {
            System.out.print(prompt);
            userResponse = scanner.nextLine();
            if (userResponse.trim().equalsIgnoreCase("y") || userResponse.trim().equalsIgnoreCase("n")) {
                validResponse = true;
                if (userResponse.trim().equalsIgnoreCase("y")) {
                    confirm = true;
                }
            }
//            else {
//                System.out.println("Invalid input. Please enter 'y' or 'n'.");
//            }
        } while (!validResponse);

        return confirm;
    }

    // get a string in a given patten:
    public static void getRegExString(Scanner scanner, String prompt, String regEx) {
        String retString;
        boolean validString = false;
        do {
            System.out.print(prompt);
            retString = scanner.nextLine();
            if (retString.matches(regEx)) {
                validString = true;
            } else {
                System.out.println("Invalid input. Please enter a valid string.");
            }
        } while (!validString);
    }

    // formatting a header:
    public static void prettyHeader(String msg) {
        int msgLength = msg.length();
        int whiteSpaces = (60 - msgLength - 6) / 2;
        // line 1:
        for (int i = 0; i < 60; i++) {
            System.out.print("*");
        }
        // line 2:
        System.out.print("\n***");
        for (int i = 0; i < whiteSpaces; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < whiteSpaces; i++) {
            System.out.print(" ");
        }
        System.out.println("***");
        // line 3:
        for (int i = 0; i < 60; i++) {
            System.out.print("*");
        }
        System.out.println();

    }
}