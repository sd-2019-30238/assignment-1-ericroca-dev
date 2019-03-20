package data;

import models.Feedback;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class FeedbackDAO {

    private static SessionFactory factory;

    public FeedbackDAO() {
        try {
            factory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Feedback.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Integer addFeedback(Integer orderID, Integer userID, String details) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer feedbackID = null;

        try {
            tx = session.beginTransaction();
            Feedback feedback = new Feedback();
            feedback.setOrderID(orderID);
            feedback.setUserID(userID);
            feedback.setDetails(details);
            feedbackID = (Integer) session.save(feedback);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return feedbackID;
    }

    public void listFeedbacks() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List feedbacks = session.createQuery("FROM Feedback ").list();
            for (Iterator iterator = feedbacks.iterator(); iterator.hasNext();) {
                Feedback feedback = (Feedback) iterator.next();
                System.out.println("OrderID: " + feedback.getOrderID());
                System.out.println("UserID: " + feedback.getUserID());
                System.out.println("Details: " + feedback.getDetails());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateFeedback(Integer feedbackID, Integer orderID, Integer userID, String details) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Feedback feedback = (Feedback) session.get(Feedback.class, feedbackID);
            feedback.setOrderID(orderID);
            feedback.setUserID(userID);
            feedback.setDetails(details);
            session.update(feedback);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteFeedback(Integer feedbackID) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Feedback feedback = (Feedback) session.get(Feedback.class, feedbackID);
            session.delete(feedback);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
