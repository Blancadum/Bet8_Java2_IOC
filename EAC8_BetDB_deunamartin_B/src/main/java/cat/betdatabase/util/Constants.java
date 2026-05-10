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

    // === DEFAULTS ===
    public static final int DEFAULT_COLUMN_NUMBER = 7;
    public static final String BETTOR_NAME = "El nom de l'apostant";
    public static final String MESSAGE_DEFAULT_BET_DESCRIPTION = "Descripció de l'aposta:";
    public static final String MESSAGE_DEFAULT_BET_DATA = "Dades actuals de l'aposta:\n";
    public static final String MESSAGE_DEFAULT_EDIT_CANCELLED = "Edició cancel·lada.";
    public static final String MESSAGE_DEFAULT_BET_UPDATED = "Aposta actualitzada correctament!";
    public static final String MESSAGE_DEFAULT_BET_DELETED = "Aposta eliminada correctament!";
    public static final String MESSAGE_DEFAULT_EVENT_DELETED = "event eliminat correctament!";
    public static final String MESSAGE_DEFAULT_DELETE_CANCELLED = "Eliminació cancel·lada.";

    // === MENSAJES DE ENTRADA (ASK) ===
    public static final String MESSAGE_DEFAULT_ASK_STRING = "Introdueix una cadena de text";
    public static final String MESSAGE_DEFAULT_ASK_INTEGER = "Introdueix un valor enter";
    public static final String MESSAGE_DEFAULT_ASK_FLOAT = "Introdueix un valor amb decimals per l'opció";
    public static final String MESSAGE_DEFAULT_ASK_DATETIME = "Introdueix la data i hora (format: yyyyMMddHHmm):";
    public static final String MESSAGE_DEFAULT_ASK_ODDS = "Quotes (ex: 1.75):";
    public static final String MESSAGE_DEFAULT_ASK_AMOUNT = "Import (ex: 50.0):";
    public static final String MESSAGE_DEFAULT_SPORT = "Introdueix el tipus d'esport:";
    public static final String MESSAGE_DEFAULT_EVENT_ID = "Introdueix l'ID de l'esdeveniment:";
    public static final String MESSAGE_DEFAULT_EVENT = "Introdueix el nom de l'esdeveniment:";
    public static final String MESSAGE_DEFAULT_FILTER_TYPE = "Introdueix el tipus d'esport:";


    // === MENSAJES DE ERROR / VALIDACIÓN ===
    public static final String MESSAGE_DEFAULT_EMPTY = "no pot ser buit";
    public static final String MESSAGE_DEFAULT_ERROR_STRING = "S'ha introduït un text buit";
    public static final String MESSAGE_DEFAULT_ERROR_INTEGER = "El valor introduït no correspon a un enter";
    public static final String MESSAGE_DEFAULT_ERROR_FLOAT = "El valor introduït no correspon a un nombre amb decimals";
    public static final String ERROR_CONTAINS_NUMBERS = "no pot contenir números";
    public static final String ERROR_SPORT_TYPE = "El tipus d'esport només pot contenir lletres";
    public static final String ERROR_EVENT_NAME = "El nom de l'event ha de contenir almenys una lletra";
    public static final String ERROR_BETTOR_NAME = "El nom de l'apostant ha de tenir format DNI (8 dígits + 1 lletra, ex: 12345678A)";
    public static final String ERROR_BET_DESCRIPTION = "La descripció de l'aposta ha de contenir almenys una lletra";
    public static final String ERROR_SHOW_MESSAGE_EMPTY = "El títol i el text principal no poden ser nuls o buits.";
    public static final String ERROR_BET_LIST_EMPTY = "La llista d'apostes no pot ser nul·la o buida.";
    public static final String ERROR_DATE_FUTURE = "La data ha de ser futura. Torna-ho a intentar.";
    public static final String ERROR_DATATIME_PARSE = "Format incorrecte. Utilitza: yyyyMMddHHmm (ex: 202605251430)";
    public static final String ERROR_DATE_NULL = "La data no pot ser nul·la.";
    public static final String ERROR_EVENT_ID_NOT_FOUND = "No existeix cap event amb l'ID: ";
    public static final String ERROR_BET_EXISTS = "ja té una aposta en aquest event.";
    public static final String ERROR_ID_INVALID = "ID no vàlid: ";
    public static final String ERROR_DELETE_BET = "Error al eliminar l'aposta: ";
    public static final String MESSAGE_DEFAULT_CONFIRM_DELETE = "Segur que vols eliminar aquesta aposta?\n\n";

    // === MENSAJES DE ÉXITO ===
    public static final String ID_EVENT_OK = "event afegit correctament amb ID: ";
    public static final String ID_BET_OK = "Aposta afegida correctament amb ID: ";

    // === ERRORES DE OPERACIÓN ===
    public static final String ERROR_INSERTING_BET = "Error en inserir l'aposta: ";
    public static final String ERROR_SHOWING_BETS = "Error en mostrar les apostes: ";
    public static final String ERROR_NO_BETS = "No hi ha apostes per mostrar.";
    public static final String ERROR_DELETE_DATA_FILE = "No s'ha pogut esborrar l'arxiu de dades.";

    // === EXPRESIONES REGULARES ===
    public static final String REGEX_ONLY_LETTERS = "^[a-zA-Z\\s]+$";
    public static final String REGEX_LETTERS_NUMBERS = "^[a-zA-Z0-9\\s\\-]+$";
    public static final String REGEX_DNI_FORMAT = "^\\d{8}[A-Z]$";

    // === FORMATOS Y SEPARADORES ===
    public static final String DATETIME_FORMAT = "yyyyMMddHHmm";
    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String FLOAT_TWO_DECIMALS = "%.2f";
    public static final String NEWLINE = "\n";
    public static final String SEPARATOR = ",";
    public static final String SPACE = " ";
    public static final String EMPTY_STRING = "";

    // === HEADERS Y DISPLAYS ===
    public static final String START_MENU_HEADER = "BET IOC!";
    public static final String ERROR_HEADER = "ERROR";
    public static final String INFO_HEADER = "INFO";
    public static final String MESSAGE_SEPARATOR = "--------------------------------------------------------------------------------------------------------------";
    public static final String LIST_HEADER = "DATETIME     SPORT       EVENT                  JUGADOR           TIPUS                      QUOTES    IMPORT";
    public static final String TEMP_LIN = "-------------------------------------------------------------------------------------------------------------";
    public static final String BET_BOARD_FORMAT = "%-12s %-11s %-18s %-18s %-25s %7s %9s";
    public static final String EMPTY_SPACE = "";

    // === MENU ===
    public static final String OP1_AFEGIR_event = "1. Afegir un event\n";
    public static final String OP2_AFEGIR_APOSTA = "2. Afegir una aposta a un event\n";
    public static final String OP3_VEURE_APOSTES = "3. Veure el llistat d'apostes\n";
    public static final String OP4_EDITAR_APOSTA = "4. Editar una aposta\n";
    public static final String OP5_ELIMINAR_APOSTA = "5. Eliminar una aposta\n";
    public static final String OP6_ELIMINAR_EVENT = "6. Eliminar un event\n";
    public static final String OP0_SORTIR = "0. Sortir\n";
    public static final String INVALID_OP = "Opció no vàlida";
    public static final String FILTER_ALL_BETS = "1. Veure totes les apostes\n";
    public static final String FILTER_TYPE_SPORT = "2. Filtrar per tipus d'esport";
    public static final String ASK_FILTER_OPTION = "Escull una opció:";
    public static final String EDIT_BET_MENU = "1. Editar descripció\n2. Editar odds\n3. Editar quantitat\n4. Editar tots\n0. Cancelar";
    public static final String CONFIRM_DELETE_OPTIONS = "1. Sí, eliminar\n0. No, cancelar";
    public static final String MESSAGE_DEFAULT_EXIT = "Fins aviat!";
    
    //QUERIES
    public static final String QUERY_DELETE_ID = "DELETE FROM Bet b WHERE b.id = :id";
    public static final String QUERY_SEARCH_BETTORNAME = "FROM Bet b WHERE LOWER(b.bettorName) LIKE LOWER(:name)";
    public static final String QUERY_SEARCH_EVENTID = "FROM Bet b WHERE b.event.id = :eventId";

}


