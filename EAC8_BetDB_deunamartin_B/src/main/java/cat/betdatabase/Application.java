package cat.betdatabase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import cat.betdatabase.dao.BetDAO;
import cat.betdatabase.dao.EventDAO;
import cat.betdatabase.model.Bet;
import cat.betdatabase.model.Event;
import cat.betdatabase.util.Constants;
import cat.betdatabase.util.HibernateUtil;
import cat.betdatabase.util.UtilsIO;

/**
 * Main application class for the Betting Database System.
 *
 * @author User
 * @version 1.0
 */
public class Application {

    private static final UtilsIO io = new UtilsIO();
    private static final EventDAO eventDAO = new EventDAO();
    private static final BetDAO betDAO = new BetDAO();

    public static void main(String[] args) {
        int option = -1;
        while (option != 0) {
            String menu = Constants.OP1_AFEGIR_DESENVOLUPAMENT +
                          Constants.OP2_AFEGIR_APOSTA +
                          Constants.OP3_VEURE_APOSTES +
                          Constants.OP0_SORTIR;
            io.showMenu(menu);
            option = io.askForInteger(Constants.MESSAGE_DEFAULT_ASK_INTEGER, Constants.INVALID_OP);

            switch (option) {
                case 1 -> addEvent();
                case 2 -> addBet();
                case 3 -> showBets();
                case 0 -> io.showInfo(Constants.MESSAGE_DEFAULT_EXIT);
                default -> io.showError(Constants.INVALID_OP);
            }
        }
        HibernateUtil.shutdown();
    }

    // --- Opció 1: Afegir un esdeveniment ---

    private static void addEvent() {
        String type = io.askForTextOnlyLetters(Constants.MESSAGE_DEFAULT_SPORT, Constants.ERROR_SPORT_TYPE);
        String name = io.askForTextWithLetters(Constants.MESSAGE_DEFAULT_EVENT, Constants.ERROR_EVENT_NAME);

        LocalDateTime timestamp = null;
        while (timestamp == null) {
            String dateStr = io.askForNotEmptyString(
                Constants.MESSAGE_DEFAULT_ASK_DATETIME,
                Constants.EMPTY_STRING);
            try {
                timestamp = LocalDateTime.parse(dateStr,
                    DateTimeFormatter.ofPattern(Constants.DATETIME_FORMAT));
                if (!timestamp.isAfter(LocalDateTime.now())) {
                    io.showError(Constants.ERROR_DATE_FUTURE);
                    timestamp = null;
                }
            } catch (DateTimeParseException e) {
                io.showError(Constants.ERROR_DATATIME_PARSE);
            }
        }

        try {
            Event event = new Event(type, name, timestamp);
            eventDAO.save(event);
            io.showInfo(Constants.ID_EVENT_OK + event.getId());
        } catch (IllegalArgumentException e) {
            io.showError(e.getMessage());
        }
    }

    // --- Opció 2: Afegir una aposta ---

    private static void addBet() {
        List<Event> events = eventDAO.getAll();
        if (events.isEmpty()) {
            io.showInfo(Constants.MESSAGE_DEFAULT_EMPTY);
            return;
        }

        // Mostrar llista d'esdeveniments
        StringBuilder eventList = new StringBuilder();
        for (Event e : events) {
            String datetime = e.getTimestamp()
                .format(DateTimeFormatter.ofPattern(Constants.DATETIME_FORMAT));
            eventList.append("ID: ").append(e.getId())
                     .append(" | ").append(e.getType())
                     .append(" | ").append(e.getName())
                     .append(" | ").append(datetime)
                     .append("\n");
        }
        io.showInfo(eventList.toString().trim());

        // Seleccionar esdeveniment per ID
        Long eventId = (long) io.askForInteger(Constants.MESSAGE_DEFAULT_EVENT_ID, Constants.ERROR_ID_INVALID);
        Event selectedEvent = eventDAO.getById(eventId);
        if (selectedEvent == null) {
            io.showError(Constants.ERROR_ID_INVALID + eventId);
            return;
        }

        // Dades de l'aposta
        String bettorName = io.askForTextWithLetters(Constants.BETTOR_NAME, Constants.ERROR_BETTOR_NAME);

        // Comprovar que l'apostant no té ja una aposta en aquest esdeveniment
        List<Bet> existingBets = betDAO.findByEventId(eventId);
        for (Bet b : existingBets) {
            if (b.getBettorName().equals(bettorName)) {
                io.showError("L'apostant '" + bettorName + "' " + Constants.ERROR_BET_EXISTS);
                return;
            }
        }

        String description = io.askForTextWithLetters(Constants.MESSAGE_DEFAULT_BET_DESCRIPTION, Constants.ERROR_BET_DESCRIPTION);
        float odds = io.askForFloat(Constants.MESSAGE_DEFAULT_ASK_ODDS, Constants.MESSAGE_DEFAULT_ERROR_FLOAT);
        float amount = io.askForFloat(Constants.MESSAGE_DEFAULT_ASK_AMOUNT, Constants.MESSAGE_DEFAULT_ERROR_FLOAT);

        try {
            Bet bet = new Bet(bettorName, description, odds, amount);
            bet.setEvent(selectedEvent);
            betDAO.save(bet);
            io.showInfo(Constants.ID_BET_OK + bet.getId());
        } catch (IllegalArgumentException e) {
            io.showError(e.getMessage());
        }
    }

    // --- Opció 3: Veure el llistat d'apostes ---

    private static void showBets() {
        String filterMenu = Constants.FILTER_ALL_BETS + 
                            Constants.FILTER_TYPE_SPORT;
                            
        io.showMenu(filterMenu);
        int filterOption = io.askForInteger(Constants.ASK_FILTER_OPTION, Constants.INVALID_OP);

        List<Event> events;
        if (filterOption == 2) {
            String type = io.askForTextOnlyLetters(Constants.MESSAGE_DEFAULT_FILTER_TYPE, Constants.ERROR_SPORT_TYPE);
            events = eventDAO.findByType(type);
        } else {
            events = eventDAO.getAll();
        }

        // Construir el string de totes les apostes
        StringBuilder allBets = new StringBuilder();
        for (Event event : events) {
            List<Bet> bets = betDAO.findByEventId(event.getId());
            String datetime = event.getTimestamp()
                .format(DateTimeFormatter.ofPattern(Constants.DATETIME_FORMAT));
            for (Bet bet : bets) {
                allBets.append(event.getType()).append(Constants.SEPARATOR)
                       .append(event.getName()).append(Constants.SEPARATOR)
                       .append(datetime).append(Constants.SEPARATOR)
                       .append(bet.getBettorName()).append(Constants.SEPARATOR)
                       .append(bet.getBetDescription()).append(Constants.SEPARATOR)
                       .append(bet.getOdds()).append(Constants.SEPARATOR)
                       .append(bet.getAmount()).append(Constants.NEWLINE);
            }
        }

        if (allBets.isEmpty()) {
            io.showInfo(Constants.ERROR_NO_BETS);
        } else {
            io.showBets(allBets.toString().trim());
        }
    }
}
