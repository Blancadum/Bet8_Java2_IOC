package cat.betdatabase.dao;

import java.util.List;
import cat.betdatabase.model.Bet;
import cat.betdatabase.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Data Access Object (DAO) for Bet entities.
 *
 * This class provides CRUD (Create, Read, Update, Delete) operations and query
 * methods for Bet objects in the database using Hibernate ORM.
 *
 * Each method manages its own Hibernate session lifecycle to ensure proper
 * transaction handling and resource cleanup.
 *
 * @author User
 * @version 1.0
 * @see Bet
 */
public class BetDAO {

    public Long save(Bet bet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(bet);
            tx.commit();
            return bet.getId();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException(e);
        }
    }

    public Bet getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Bet.class, id);
        }
    }

    public List<Bet> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Bet", Bet.class).list();
        }
    }

    public void update(Bet bet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(bet);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException(e);
        }
    }

    public void delete(Bet bet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Bet managed = session.merge(bet);
            session.remove(managed);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException(e);
        }
    }

    public List<Bet> findByBettorName(String bettorName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Bet b WHERE LOWER(b.bettorName) LIKE LOWER(:name)", Bet.class)
                .setParameter("name", "%" + bettorName + "%")
                .list();
        }
    }

    public List<Bet> findByEventId(Long eventId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Bet b WHERE b.event.id = :eventId", Bet.class)
                .setParameter("eventId", eventId)
                .list();
        }
    }
}
