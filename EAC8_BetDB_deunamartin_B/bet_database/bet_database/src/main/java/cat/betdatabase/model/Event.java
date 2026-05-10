package cat.betdatabase.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;


/**
 * Represents a sporting event on which bets can be placed.
 * 
 * This JPA entity stores information about events including:
 * - Type of sport (e.g., "Soccer", "Basketball", "Tennis")
 * - Event name or description
 * - Event timestamp (must be in the future)
 * - Collection of associated Bets (one-to-many relationship)
 * 
 * Events enforce validation:
 * - Type and name must not be empty
 * - Timestamp must be in the future
 * - New bets must be added via addBet() method for proper relationship management
 * 
 * @author User
 * @version 1.0
 * @see Bet
 */
@Entity
@Table(name = "events")
public class Event {
    /** The unique identifier for this event (auto-generated primary key). */
    
    /** The type of sport for this event (e.g., "Soccer", "Basketball", "Tennis"). */

    /** The name or description of the event. */

    /** The date and time of the event (must be in the future). */
    
    /** The collection of bets placed on this event (one-to-many relationship). */
    
    /** Transient map for backward compatibility with legacy API. Not persisted to database. */

    /**
     * No-argument constructor required by JPA for entity instantiation.
     * Creates an Event with empty collections.
     */
    public Event() {

    }

    /**
     * Constructs an Event with the specified details.
     * 
     * Validates that type and name are not empty,
     * and that the timestamp is in the future.
     * 
     * @param type the type of sport (must not be empty)
     * @param name the event name (must not be empty)
     * @param timestamp the event timestamp (must not be null and must be in the future)
     * @throws IllegalArgumentException if type is empty
     * @throws IllegalArgumentException if name is empty
     * @throws IllegalArgumentException if timestamp is in the past or present
     */
    public Event(String type, String name, LocalDateTime timestamp) {

    }

    /**
     * Retrieves the unique identifier for this event.
     * 
     * @return the event ID (auto-generated), or null if not yet persisted
     */
    public Long getId() {

    }
    
    /**
     * Sets the unique identifier for this event.
     * 
     * @param id the event ID to set
     */
    public void setId(Long id) {

    }

    /**
     * Retrieves the type of sport for this event.
     * 
     * @return the event type (e.g., "Soccer", "Basketball", "Tennis")
     */
    public String getType() {

    }    

    /**
     * Retrieves the name or description of this event.
     * 
     * @return the event name
     */
    public String getName() {

    }

    /**
     * Retrieves the date and time of this event.
     * 
     * @return the event timestamp
     */
    public LocalDateTime getTimestamp() {

    }

    /**
     * Sets the type of sport for this event.
     * 
     * @param type the new event type (must not be empty)
     * @throws IllegalArgumentException if type is empty
     */
    public void setType(String type) {

    }

    /**
     * Sets the name or description of this event.
     * 
     * @param name the new event name (must not be empty)
     * @throws IllegalArgumentException if name is empty
     */
    public void setName(String name) {

    }

    /**
     * Sets the date and time of this event.
     * 
     * @param timestamp the new timestamp (must not be null and must be in the future)
     * @throws IllegalArgumentException if timestamp is null
     * @throws IllegalArgumentException if timestamp is not in the future
     */
    public void setTimestamp(LocalDateTime timestamp) {

    }   

    /**
     * Adds a bet to this event using the legacy API pattern.
     * 
     * Creates a new Bet with the specified parameters and associates it with this event.
     * The bet is added to both the transient map and the persistent list.
     * 
     * @param betDescription the description of the bet (must not be empty)
     * @param bettor the name of the bettor (must not be empty)
     * @param odds the odds for the bet (must be greater than 0)
     * @param amount the amount staked (must be greater than 0)
     * @throws IllegalArgumentException if bettor or description is empty
     * @throws IllegalArgumentException if bettor already has a bet on this event
     * @throws IllegalArgumentException if odds or amount is not positive
     */
    public void addBet(String betDescription, String bettor, float odds, float amount) {

    }

    /**
     * Retrieves a bet from this event using the legacy API pattern.
     * 
     * @param bettor the name of the bettor whose bet to retrieve (must not be empty)
     * @return the Bet object associated with the bettor
     * @throws IllegalArgumentException if bettor name is empty
     * @throws IllegalArgumentException if no bet exists for the given bettor
     */
    public Bet getBet(String bettor) {

    }

    /**
     * Checks if a bettor has placed a bet on this event.
     * 
     * @param bettor the name of the bettor to check (must not be empty)
     * @return true if the bettor has a bet on this event, false otherwise
     * @throws IllegalArgumentException if bettor name is empty
     */
    public boolean bettorExists(String bettor) {

    }   

    /**
     * Retrieves all bets placed on this event using the legacy API pattern.
     * 
     * Returns a defensive copy of the internal map to prevent external modification.
     * Note: This returns only the transient map, not the persistent JPA List.
     *
     * @return a new map containing all bets keyed by bettor name
     */
    public Map<String, Bet> getAllBets() {

    }

    /**
     * Generates a string representation of all bets on this event.
     * 
     * Each bet is formatted according to Constants.BET_TO_STRING_FORMAT,
     * with bets separated by newlines.
     *
     * @return a multi-line string with all bets, or an empty string if no bets exist
     */
    public String betsToString() {

    }

    /**
     * Generates a CSV representation of this event.
     * 
     * Format: type,name,timestamp
     * 
     * @return a CSV string with the event data
     */
    public String eventToString() {

    }

    /**
     * Generates a string representation of this event and all its bets.
     * 
     * Each line contains the event data followed by a single bet's data.
     * If the event has no bets, returns just the event data.
     * 
     * @return a formatted string with event and bet information
     */
    @Override
    public String toString() {
    }

}