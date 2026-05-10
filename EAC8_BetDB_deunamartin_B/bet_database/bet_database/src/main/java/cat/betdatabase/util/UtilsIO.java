package cat.betdatabase.util;
import java.util.Scanner;

/**
 * Utility class for handling user input and output in the console.
 * <p>
 * This class provides methods for displaying messages, errors, menus, and 
 * asking for various types of input such as strings, integers, and floats.
 * It also supports formatted display of bet lists.
 * </p>
 * 
 * <p>It relies on predefined constants to standardize messages and layout.</p>
 */
public class UtilsIO {

    private Scanner scanner;

    /**
     * Constructs a UtilsIO object and initializes the input scanner.
     */
    public UtilsIO() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a formatted message with a header and body content.
     * Throws IllegalArgumentException if either part is null or empty.
     *
     * @param header the title or heading of the message
     * @param mainText the main body of the message
     * @throws IllegalArgumentException if header or mainText is null or empty
     */
    public void showAnyMessage(String header, String mainText) {
        if (mainText == null || mainText.isEmpty()
            || header == null || header.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_SHOW_MESSAGE_EMPTY);
        }
        System.out.println(Constants.EMPTY_SPACE);
        System.out.println(Constants.MESSAGE_SEPARATOR);
        System.out.println(header);
        System.out.println(Constants.MESSAGE_SEPARATOR);
        System.out.println(mainText);
        System.out.println(Constants.EMPTY_SPACE);
    }

    /**
     * Displays a formatted menu using a default menu header.
     *
     * @param menuText the content of the menu to display
     */
    public void showMenu(String menuText) {
        showAnyMessage(Constants.START_MENU_HEADER, menuText);
    }

    /**
     * Displays a formatted error message using a default error header.
     *
     * @param errorText the content of the error message
     */
    public void showError(String errorText) {
        showAnyMessage(Constants.ERROR_HEADER, errorText);
    }

    /**
     * Displays an informational message using a default info header.
     *
     * @param infoText the content of the info message
     */
    public void showInfo(String infoText) {
        showAnyMessage(Constants.INFO_HEADER, infoText);
    }

    /**
     * Prompts the user for any string input and returns it.
     *
     * @param message the prompt shown to the user
     * @return the user’s input as a string
     */
    public String askForAnyString(String message){
        if (message == null || message.isEmpty()) {
            message = Constants.MESSAGE_DEFAULT_ASK_STRING;
        }
        System.out.println(message);
        return scanner.nextLine();
    }

    /**
     * Prompts the user for a non-empty string input.
     * Repeats until valid input is entered.
     *
     * @param message the prompt shown to the user
     * @param errorMessage message shown when the input is empty
     * @return a non-empty string from the user
     */
    public String askForNotEmptyString(String message, String errorMessage) {
        while (true) {
            if (errorMessage == null || errorMessage.isEmpty()) {
                errorMessage = Constants.MESSAGE_DEFAULT_ERROR_STRING;
            }
            String inputText = askForAnyString(message);
            if (!inputText.isEmpty()) {
                return inputText;
            }
            System.out.println(errorMessage);
        }
    }

    /**
     * Prompts the user to enter an integer value.
     * Repeats until a valid integer is entered.
     *
     * @param message prompt shown to the user
     * @param errorMessage message shown when the input is invalid
     * @return a valid integer entered by the user
     */
    public int askForInteger(String message, String errorMessage) {
        if (message == null || message.isEmpty()) {
            message = Constants.MESSAGE_DEFAULT_ASK_INTEGER;
        }
        if (errorMessage == null || errorMessage.isEmpty()) {
            errorMessage = Constants.MESSAGE_DEFAULT_ERROR_INTEGER;
        }
        while (true) {
            System.out.print(message + Constants.NEWLINE);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return input;
            }
            scanner.nextLine();
            System.out.println(errorMessage);
        }
    }

    /**
     * Prompts the user to enter a floating-point value.
     * Repeats until a valid float is entered.
     *
     * @param message prompt shown to the user
     * @param errorMessage message shown when the input is invalid
     * @return a valid float entered by the user
     */
    public float askForFloat(String message, String errorMessage) {
        if (message == null || message.isEmpty()) {
            message = Constants.MESSAGE_DEFAULT_ASK_FLOAT;
        }
        if (errorMessage == null || errorMessage.isEmpty()) {
            errorMessage = Constants.MESSAGE_DEFAULT_ERROR_FLOAT;
        }
        while (true) {
            System.out.print(message + Constants.NEWLINE);
            if (scanner.hasNextFloat()) {
                float input = scanner.nextFloat();
                scanner.nextLine(); // Consume newline
                return input;
            }
            scanner.nextLine();
            System.out.println(errorMessage);
        }
    }

    /**
     * Displays a list of bets formatted using a predefined template.
     * Each bet must have exactly the expected number of columns (timestamp, sport, event, betType, odds, amount).
     * Malformed bet entries are silently skipped.
     *
     * @param betList the raw string containing all bets separated by newline
     * @throws IllegalArgumentException if betList is null or empty
     */
    public void showBets(String betList) {
        if (betList == null || betList.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_BET_LIST_EMPTY);
        }
        System.out.println(Constants.EMPTY_SPACE);
        System.out.println(Constants.TEMP_LIN);
        System.out.println(Constants.LIST_HEADER);
        System.out.println(Constants.TEMP_LIN);
        String[] bets = betList.split(Constants.NEWLINE);
        for (int i = 0; i < bets.length; i++) {
            String[] bet = bets[i].split(Constants.SEPARATOR);
            if (bet.length == Constants.DEFAULT_COLUMN_NUMBER) {
                try {
                    String event = bet[1].substring(0, Math.min(bet[1].length(), 18));
                    String datetime = bet[2].substring(0, Math.min(bet[2].length(), 10));
                    String oddsString = bet[5].trim().isEmpty() ? Constants.EMPTY_STRING : String.format(Constants.FLOAT_TWO_DECIMALS, Float.parseFloat(bet[5]));
                    String betString = bet[6].trim().isEmpty() ? Constants.EMPTY_STRING : String.format(Constants.FLOAT_TWO_DECIMALS, Float.parseFloat(bet[6]));
                    System.out.println(String.format(
                        Constants.BET_BOARD_FORMAT, 
                        datetime, bet[0], event, bet[3], bet[4], oddsString, betString));
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
    }
}