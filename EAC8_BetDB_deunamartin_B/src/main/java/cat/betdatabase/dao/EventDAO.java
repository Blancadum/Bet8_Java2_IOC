package cat.betdatabase.dao;

import java.util.List;
import cat.betdatabase.model.Event;
import cat.betdatabase.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Data Access Object (DAO) for Event entities.
 *
 * This class provides CRUD (Create, Read, Update, Delete) operations and query
 * methods for Event objects in the database using Hibernate ORM.
 *
 * Each method manages its own Hibernate session lifecycle to ensure proper
 * transaction handling and resource cleanup.
 *
 * @author User
 * @version 1.0
 * @see Event
 */
public class EventDAO {

    public Long save(Event event) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(event);
            tx.commit();
            return event.getId();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException(e);
        }
    }

    public Event getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Event.class, id);
        }
    }

    public List<Event> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Event", Event.class).list();
        }
    }

    public void update(Event event) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(event);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException(e);
        }
    }

    public void delete(Event event) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Event managed = session.merge(event);
            session.remove(managed);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException(e);
        }
    }

    public List<Event> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Event e WHERE LOWER(e.name) LIKE LOWER(:name)", Event.class)
                .setParameter("name", "%" + name + "%")
                .list();
        }
    }

    public List<Event> findByType(String type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Event e WHERE LOWER(e.type) LIKE LOWER(:type)", Event.class)
                .setParameter("type", "%" + type + "%")
                .list();
        }
    }
}
