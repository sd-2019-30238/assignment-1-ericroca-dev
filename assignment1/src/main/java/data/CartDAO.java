package data;

import models.Cart;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class CartDAO {

    private static SessionFactory factory;

    public CartDAO() {
        try {
            factory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Cart.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Integer addCart(Integer userID, Integer dealID) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer cartID = null;

        try {
            tx = session.beginTransaction();
            Cart cart = new Cart();
            cart.setUserID(userID);
            cart.setDealID(dealID);
            cartID = (Integer) session.save(cart);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return cartID;
    }

    public void listCarts() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List carts = session.createQuery("FROM Cart").list();
            for (Iterator iterator = carts.iterator(); iterator.hasNext();) {
                Cart cart = (Cart) iterator.next();
                System.out.println("UserID: " + cart.getUserID());
                System.out.println("DealID: " + cart.getDealID());
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

    public void updateCart(Integer cartID, Integer userID, Integer dealID) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Cart cart = (Cart) session.get(Cart.class, cartID);
            cart.setUserID(userID);
            cart.setDealID(dealID);
            session.update(cart);
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

    public void deleteCart(Integer cartID) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Cart cart = (Cart) session.get(Cart.class, cartID);
            session.delete(cart);
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
