package data.implementation;

import data.service.CartDAO;
import models.Cart;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class CartDAOImpl implements CartDAO {

    private static SessionFactory factory;

    public CartDAOImpl() {
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public List<Cart> getUserCart(Integer userID) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Cart> cart = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Cart WHERE userID = :userID");
            query.setInteger("userID", userID);
            cart = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return cart;
    }

    @Override
    public Cart findByIDs(Integer userID, Integer dealID) {
        Session session = factory.openSession();
        Transaction tx = null;
        Cart cart = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Cart WHERE userID = :userID AND dealID = :dealID");
            query.setInteger("userID", userID);
            query.setInteger("dealID", dealID);
            cart = (Cart) query.list().get(0);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return cart;
    }
}
