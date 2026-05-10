package cat.betdatabase.dao;
import java.util.List;
import cat.betdatabase.model.Event;


/**
 * Data Access Object (DAO) for Event entities.
 * 
 * This class provides CRUD (Create, Read, Update, Delete) operations
 * and query methods for Event objects in the database using Hibernate ORM.
 * 
 * Each method manages its own Hibernate session lifecycle to ensure
 * proper transaction handling and resource cleanup.
 * 
 * @author User
 * @version 1.0
 * @see Event
 */
public class EventDAO {

    /**
     * Persists a new Event to the database.
     * 
     * Opens a new Hibernate session, begins a transaction, saves the event,
     * and commits the transaction. If an error occurs, the transaction is rolled back.
     * 
     * @param event the Event entity to persist (must not be null)
     * @return the generated ID for the persisted event
     * @throws RuntimeException if the save operation fails
     */
    public Long save(Event event) {

    }

    /**
     * Retrieves an Event from the database by its unique identifier.
     * 
     * Opens a read-only session to fetch the event.
     * 
     * @param id the unique identifier of the event to retrieve
     * @return the Event entity, or null if not found
     * @throws RuntimeException if the database query fails
     */
    public Event getById(Long id) {

    }

    /**
     * Retrieves all Events from the database.
     * 
     * Executes a query to fetch all event records.
     * 
     * @return a list of all Event entities, empty list if none exist
     * @throws RuntimeException if the database query fails
     */
    public List<Event> getAll() {

    }

    /**
     * Updates an existing Event in the database.
     * 
     * Opens a new session, begins a transaction, updates the event,
     * and commits the transaction. If an error occurs, the transaction is rolled back.
     * 
     * @param event the Event entity with updated data (must exist in database)
     * @throws RuntimeException if the update operation fails
     */
    public void update(Event event) {

    }

    /**
     * Deletes an Event from the database.
     * 
     * Opens a new session, begins a transaction, deletes the event,
     * and commits the transaction. If an error occurs, the transaction is rolled back.
     * All associated bets are also deleted due to cascade configuration.
     * 
     * @param event the Event entity to delete (must exist in database)
     * @throws RuntimeException if the delete operation fails
     */
    public void delete(Event event) {

    }

    /**
     * Finds Events by name using a LIKE query.
     * 
     * Searches for events where the name contains the given string (case-insensitive).
     * Uses wildcard matching, so "soccer" will match "Soccer Match 1" and "Match Soccer".
     * 
     * @param name the name fragment to search for
     * @return a list of matching Event entities, empty list if none match
     * @throws RuntimeException if the database query fails
     */
    public List<Event> findByName(String name) {

    }

    /**
     * Finds Events by type using an exact match query.
     * 
     * Searches for events with the exact specified type.
     * Type must match exactly (e.g., "Soccer" will not match "soccer").
     * 
     * @param type the exact type to search for (e.g., "Soccer", "Basketball", "Tennis")
     * @return a list of matching Event entities, empty list if none match
     * @throws RuntimeException if the database query fails
     */
    public List<Event> findByType(String type) {

    }
}
