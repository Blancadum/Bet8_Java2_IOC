package cat.betdatabase.model;

import cat.betdatabase.util.Constants;
import jakarta.persistence.*;

/**
 * Represents a bet placed by a bettor on an event.
 *
 * @author User
 * @version 1.0
 * @see Event
 */
@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bettorName;
    private String betDescription;
    private float odds;
    private float amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    // Constructor vacío obligatorio para JPA
    public Bet() {
    }

    // Constructor con validaciones
    public Bet(String bettorName, String betDescription, float odds, float amount) {
        setBettorName(bettorName);
        setBetDescription(betDescription);
        setOdds(odds);
        setAmount(amount);
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getBettorName() {
        return bettorName;
    }

    public void setBettorName(String bettorName) {
        if (bettorName == null || bettorName.isEmpty()) {
            throw new IllegalArgumentException("El nom de l'apostant no pot ser buit");
        }
        this.bettorName = bettorName;
    }

    public String getBetDescription() {
        return betDescription;
    }

    public void setBetDescription(String betDescription) {
        if (betDescription == null || betDescription.isEmpty()) {
            throw new IllegalArgumentException("La descripció no pot ser buida");
        }
        this.betDescription = betDescription;
    }

    public float getOdds() {
        return odds;
    }

    public void setOdds(float odds) {
        if (odds <= 0) {
            throw new IllegalArgumentException("Les quotes han de ser majors que 0");
        }
        this.odds = odds;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("L'import ha de ser major que 0");
        }
        this.amount = amount;
    }

    @Override
    public String toString() {
        return bettorName + Constants.SEPARATOR +
               betDescription + Constants.SEPARATOR +
               String.format(Constants.FLOAT_TWO_DECIMALS, odds) + Constants.SEPARATOR +
               String.format(Constants.FLOAT_TWO_DECIMALS, amount);
    }
}
