package cat.betdatabase;

import cat.betdatabase.dao.BetDAO;
import cat.betdatabase.dao.EventDAO;
import cat.betdatabase.model.Bet;
import cat.betdatabase.model.Event;
import cat.betdatabase.util.Constants;
import cat.betdatabase.util.HibernateUtil;
import cat.betdatabase.util.UtilsIO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

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
            String menu = "1. Afegir un esdeveniment\n" +
                          "2. Afegir una aposta a un esdeveniment\n" +
                          "3. Veure el llistat d'apostes\n" +
                          "0. Sortir";
            io.showMenu(menu);
            option = io.askForInteger("Escull una opció:", "Opció no vàlida");

            switch (option) {
                case 1 -> addEvent();
                case 2 -> addBet();
                case 3 -> showBets();
                case 0 -> io.showInfo("Fins aviat!");
                default -> io.showError("Opció no vàlida");
            }
        }
        HibernateUtil.shutdown();
    }

    // --- Opció 1: Afegir un esdeveniment ---

    private static void addEvent() {
        String type = io.askForNotEmptyString("Introdueix el tipus d'esport:", "El tipus no pot ser buit");
        String name = io.askForNotEmptyString("Introdueix el nom de l'esdeveniment:", "El nom no pot ser buit");

        LocalDateTime timestamp = null;
        while (timestamp == null) {
            String dateStr = io.askForNotEmptyString(
                "Introdueix la data i hora (format: yyyyMMddHHmm):",
                "La data no pot ser buida");
            try {
                timestamp = LocalDateTime.parse(dateStr,
                    DateTimeFormatter.ofPattern(Constants.DATETIME_FORMAT));
                if (!timestamp.isAfter(LocalDateTime.now())) {
                    io.showError("La data ha de ser futura. Torna-ho a intentar.");
                    timestamp = null;
                }
            } catch (DateTimeParseException e) {
                io.showError("Format incorrecte. Utilitza: yyyyMMddHHmm (ex: 202605251430)");
            }
        }

        try {
            Event event = new Event(type, name, timestamp);
            eventDAO.save(event);
            io.showInfo("Esdeveniment afegit correctament amb ID: " + event.getId());
        } catch (IllegalArgumentException e) {
            io.showError(e.getMessage());
        }
    }

    // --- Opció 2: Afegir una aposta ---

    private static void addBet() {
        List<Event> events = eventDAO.getAll();
        if (events.isEmpty()) {
            io.showInfo("No hi ha esdeveniments disponibles. Afegeix-ne un primer.");
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
        Long eventId = (long) io.askForInteger("Introdueix l'ID de l'esdeveniment:", "ID no vàlid");
        Event selectedEvent = eventDAO.getById(eventId);
        if (selectedEvent == null) {
            io.showError("No existeix cap esdeveniment amb l'ID: " + eventId);
            return;
        }

        // Dades de l'aposta
        String bettorName = io.askForNotEmptyString("Nom de l'apostant:", "El nom no pot ser buit");

        // Comprovar que l'apostant no té ja una aposta en aquest esdeveniment
        List<Bet> existingBets = betDAO.findByEventId(eventId);
        for (Bet b : existingBets) {
            if (b.getBettorName().equals(bettorName)) {
                io.showError("L'apostant '" + bettorName + "' ja té una aposta en aquest esdeveniment.");
                return;
            }
        }

        String description = io.askForNotEmptyString("Descripció de l'aposta:", "La descripció no pot ser buida");
        float odds = io.askForFloat("Quotes (ex: 1.75):", "Les quotes han de ser un número");
        float amount = io.askForFloat("Import (ex: 50.0):", "L'import ha de ser un número");

        try {
            Bet bet = new Bet(bettorName, description, odds, amount);
            bet.setEvent(selectedEvent);
            betDAO.save(bet);
            io.showInfo("Aposta afegida correctament amb ID: " + bet.getId());
        } catch (IllegalArgumentException e) {
            io.showError(e.getMessage());
        }
    }

    // --- Opció 3: Veure el llistat d'apostes ---

    private static void showBets() {
        String filterMenu = "1. Veure totes les apostes\n" +
                            "2. Filtrar per tipus d'esport";
        io.showMenu(filterMenu);
        int filterOption = io.askForInteger("Escull una opció:", "Opció no vàlida");

        List<Event> events;
        if (filterOption == 2) {
            String type = io.askForNotEmptyString("Introdueix el tipus d'esport:", "El tipus no pot ser buit");
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
            io.showInfo("No hi ha apostes per mostrar.");
        } else {
            io.showBets(allBets.toString().trim());
        }
    }
}
