package cat.betdatabase.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cat.betdatabase.model.Bet;
import cat.betdatabase.util.Constants;
import cat.betdatabase.util.HibernateUtil;

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

    // QUERY PARA ELIMINAR UNA APUESTA POR ID DE EVENTO
    public void delete(Bet bet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.createMutationQuery(Constants.QUERY_DELETE_ID)
                .setParameter("id", bet.getId())
                .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException(e);
        }
    }

    // QUERY PARA BUSCAR APUESTAS POR NOMBRE DE APOSTADOR 
    public List<Bet> findByBettorName(String bettorName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                Constants.QUERY_SEARCH_BETTORNAME, Bet.class)
                .setParameter("name", "%" + bettorName + "%")
                .list();
        }
    }

    // QUERY PARA BUSCAR APUESTAS POR ID DE EVENTO
    public List<Bet> findByEventId(Long eventId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                Constants.QUERY_SEARCH_EVENTID, Bet.class)
                .setParameter("eventId", eventId)
                .list();
        }
    }
}
