package cat.betdatabase.model;

import cat.betdatabase.util.Constants;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a sporting event on which bets can be placed.
 *
 * @author User
 * @version 1.0
 * @see Bet
 */
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String name;
    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bet> betsList = new ArrayList<>();

    // Constructor vacío obligatorio para JPA
    public Event() {
    }

    // Constructor con validaciones
    public Event(String type, String name, LocalDateTime timestamp) {
        setType(type);
        setName(name);
        setTimestamp(timestamp);
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("El tipus no pot ser buit");
        }
        this.type = type;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El nom no pot ser buit");
        }
        this.name = name;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        if (timestamp == null) {
            throw new IllegalArgumentException("La data no pot ser nul·la");
        }
        if (!timestamp.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("La data ha de ser futura");
        }
        this.timestamp = timestamp;
    }

    // --- Lógica de apuestas ---

    public void addBet(String betDescription, String bettor, float odds, float amount) {
        if (bettor == null || bettor.isEmpty()) {
            throw new IllegalArgumentException("El nom de l'apostant no pot ser buit");
        }
        if (betDescription == null || betDescription.isEmpty()) {
            throw new IllegalArgumentException("La descripció no pot ser buida");
        }
        if (bettorExists(bettor)) {
            throw new IllegalArgumentException("L'apostant ja té una aposta en aquest esdeveniment");
        }
        Bet bet = new Bet(bettor, betDescription, odds, amount);
        bet.setEvent(this);
        betsList.add(bet);
    }

    public Bet getBet(String bettor) {
        if (bettor == null || bettor.isEmpty()) {
            throw new IllegalArgumentException("El nom de l'apostant no pot ser buit");
        }
        for (Bet bet : betsList) {
            if (bet.getBettorName().equals(bettor)) {
                return bet;
            }
        }
        throw new IllegalArgumentException("No existeix cap aposta per a l'apostant: " + bettor);
    }

    public boolean bettorExists(String bettor) {
        if (bettor == null || bettor.isEmpty()) {
            throw new IllegalArgumentException("El nom de l'apostant no pot ser buit");
        }
        for (Bet bet : betsList) {
            if (bet.getBettorName().equals(bettor)) {
                return true;
            }
        }
        return false;
    }

    public Map<String, Bet> getAllBets() {
        Map<String, Bet> map = new HashMap<>();
        for (Bet bet : betsList) {
            map.put(bet.getBettorName(), bet);
        }
        return map;
    }

    public List<Bet> getBetsList() {
        return betsList;
    }

    // --- Métodos de formato ---

    // Formato: sport,eventName,datetime,bettorName,betDescription,odds,amount
    public String betsToString() {
        if (betsList.isEmpty()) {
            return Constants.EMPTY_STRING;
        }
        String datetime = timestamp.format(DateTimeFormatter.ofPattern(Constants.DATETIME_FORMAT));
        StringBuilder sb = new StringBuilder();
        for (Bet bet : betsList) {
            sb.append(type).append(Constants.SEPARATOR)
              .append(name).append(Constants.SEPARATOR)
              .append(datetime).append(Constants.SEPARATOR)
              .append(bet.getBettorName()).append(Constants.SEPARATOR)
              .append(bet.getBetDescription()).append(Constants.SEPARATOR)
              .append(bet.getOdds()).append(Constants.SEPARATOR)
              .append(bet.getAmount()).append(Constants.NEWLINE);
        }
        return sb.toString().trim();
    }

    // Formato: type,name,timestamp
    public String eventToString() {
        String datetime = timestamp.format(DateTimeFormatter.ofPattern(Constants.DATETIME_FORMAT));
        return type + Constants.SEPARATOR + name + Constants.SEPARATOR + datetime;
    }

    @Override
    public String toString() {
        if (betsList.isEmpty()) {
            return eventToString();
        }
        return betsToString();
    }
}
