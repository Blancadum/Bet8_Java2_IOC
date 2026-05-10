package cat.betdatabase.model;

import cat.betdatabase.util.Constants;
import jakarta.persistence.*;

/**
 * Represents a bet placed by a bettor on an event.
 * 
 * This JPA entity stores information about individual bets including:
 * - Bettor identifier
 * - Description of the bet (e.g., "Over 2.5 goals")
 * - Odds for the bet
 * - Amount staked on the bet
 * - Relationship to the associated Event
 * 
 * A Bet always belongs to exactly one Event (many-to-one relationship).
 * Each bet is persisted with an auto-generated primary key.
 * 
 * @author User
 * @version 1.0
 * @see Event
 */

public class Bet {
    /** The unique identifier for this bet (auto-generated primary key). */
    
    /** The name of the bettor placing this bet. */
    
    /** Description of the bet (e.g., "Osasuna win", "Over 2.5 goals"). */
    
    /** The odds for this bet (must be positive). */
    
    /** The amount of money staked on this bet (must be positive). */
    
    /** The event this bet is associated with (many-to-one relationship). */

    /**
     * No-argument constructor required by JPA for entity instantiation.
     * Creates a Bet with default values.
     */
    public Bet() {
    }

    /**
     * Constructs a Bet with the specified details.
     * 
     * Validates that bettor name and description are not empty,
     * and that odds and amount are greater than zero.
     * 
     * @param bettorName the name of the bettor (must not be empty)
     * @param betDescription description of the bet (must not be empty)
     * @param odds the odds for the bet (must be greater than 0)
     * @param amount the amount staked (must be greater than 0)
     * @throws IllegalArgumentException if bettorName is empty
     * @throws IllegalArgumentException if betDescription is empty
     * @throws IllegalArgumentException if odds is not positive
     * @throws IllegalArgumentException if amount is not positive
     */
    public Bet(String bettorName, String betDescription, float odds, float amount) {

    }
    
    /**
     * Retrieves the unique identifier for this bet.
     * 
     * @return the bet ID (auto-generated), or null if not yet persisted
     */
    public Long getId() {

    }
    
    /**
     * Sets the unique identifier for this bet.
     * 
     * @param id the bet ID to set
     */
    public void setId(Long id) {

    }
    
    /**
     * Retrieves the event this bet is associated with.
     * 
     * Note: This may trigger lazy loading if the Event entity hasn't been initialized.
     * 
     * @return the Event object this bet belongs to
     */
    public Event getEvent() {

    }

    /**
     * Sets the event this bet is associated with.
     * 
     * This establishes the many-to-one relationship with the Event.
     * The bidirectional relationship is typically managed by the Event class.
     * 
     * @param event the Event object to associate with this bet
     */
    public void setEvent(Event event) {

    }
    
    /**
     * Retrieves the name of the bettor who placed this bet.
     * 
     * @return the bettor's name
     */
    public String getBettorName() {

    }

    /**
     * Retrieves the description of this bet.
     * 
     * @return the bet description (e.g., "Osasuna win", "Over 2.5 goals")
     */
    public String getBetDescription() {

        
    /**
     * Retrieves the odds for this bet.
     * 
     * @return the bet odds
     */
    public float getOdds() {

    }
    
    /**
     * Retrieves the amount staked on this bet.
     * 
     * @return the bet amount
     */
    public float getAmount() {

    }
    
    /**
     * Returns a string representation of this bet.
     * 
     * Returns a formatted string containing all bet details:
     * bettor name, description, odds, and amount.
     * 
     * @return a formatted string representation of the bet
     */
    @Override
    public String toString() {

    }
}
