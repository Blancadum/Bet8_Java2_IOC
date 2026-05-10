package cat.betdatabase.util;
/**
 * Utility class containing all constant values used throughout the application.
 * <p>
 * This class defines constants for:
 * <ul>
 *   <li>Default file and directory names</li>
 *   <li>User prompts and messages (in Catalan)</li>
 *   <li>Error messages for various validation scenarios</li>
 *   <li>Display formatting strings and separators</li>
 *   <li>Date and time format patterns</li>
 * </ul>
 * </p>
 * <p>
 * All constants are public and static for easy access throughout the application.
 * </p>
 */
public class Constants {


    public static final int DEFAULT_COLUMN_NUMBER = 7;
    public static final String MESSAGE_DEFAULT_ASK_STRING = "Introdueixi una cadena de text";
    public static final String MESSAGE_DEFAULT_ERROR_STRING = "S'ha introduït un text buit";
    public static final String MESSAGE_DEFAULT_ASK_INTEGER = "Introdueixi un valor enter";
    public static final String MESSAGE_DEFAULT_ERROR_INTEGER = "El valor introduït no correspon a un enter";
    public static final String MESSAGE_DEFAULT_ASK_FLOAT = "Introdueixi un valor amb decimals per l'opció";
    public static final String MESSAGE_DEFAULT_ERROR_FLOAT = "El valor introduït no correspon a un nombre amb decimals";
    public static final String ERROR_SHOW_MESSAGE_EMPTY = "El títol i el text principal no poden ser nuls o buits.";
    public static final String ERROR_BET_LIST_EMPTY = "La llista d'apostes no pot ser nul·la o buida.";
    public static final String MESSAGE_SEPARATOR = "--------------------------------------------------------------------------------------------------------------";
    public static final String START_MENU_HEADER = "BET IOC!";
  
    public final static String LIST_HEADER = "DATETIME     SPORT       ESDEVENIMENT        JUGADOR           TIPUS                      QUOTES    IMPORT";
    public final static String TEMP_LIN = "-------------------------------------------------------------------------------------------------------------";
    public final static String BET_BOARD_FORMAT = "%-12s %-11s %-18s %-18s %-25s %7s %9s";
    public static final String EMPTY_SPACE = "";
    public static final String ERROR_HEADER = "ERROR";   
    public static final String INFO_HEADER = "INFO";   
    public static final String DATETIME_FORMAT = "yyyyMMddHHmm";
    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String ERROR_INSERTING_BET = "Error en inserir l'aposta: ";
    public static final String ERROR_SHOWING_BETS = "Error en mostrar les apostes: ";
    public static final String ERROR_DELETE_DATA_FILE = "No s'ha pogut esborrar l'arxiu de dades.";

    public static final String NEWLINE = "\n";
    public static final String SEPARATOR = ",";
    public static final String SPACE = " ";
    public static final String EMPTY_STRING = "";
    public static final String FLOAT_TWO_DECIMALS = "%.2f";
    
}
