package cat.betdatabase.util;



/**
 * Utility class for managing Hibernate SessionFactory.
 * 
 * This class is responsible for initializing and managing the Hibernate SessionFactory
 * for database operations. It follows the singleton pattern and ensures proper initialization
 * of the session factory with configuration from hibernate.cfg.xml and entity class annotations.
 * 
 * The class automatically configures:
 * - PostgreSQL database connection properties from hibernate.cfg.xml
 * - Entity classes (Event and Bet) with their annotations
 * - Service registry and session factory creation
 * 
 * Usage:
 * {@code
 *     SessionFactory factory = HibernateUtil.getSessionFactory();
 *     Session session = factory.openSession();
 *     // ... perform database operations ...
 *     HibernateUtil.shutdown(); // Call when application terminates
 * }
 * 
 * @author User
 * @version 1.0
 */
public class HibernateUtil {
}