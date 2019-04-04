package data.implementation;

import data.service.OrderDAO;
import models.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private static SessionFactory factory;

    public OrderDAOImpl() {
        try {
            factory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Order.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public Integer addOrder(Integer userID, String details, String status) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer orderID = null;

        try {
            tx = session.beginTransaction();
            Order order = new Order();
            order.setUserID(userID);
            order.setDetails(details);
            order.setStatus(status);
            orderID = (Integer) session.save(order);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return orderID;
    }

    @Override
    public void listOrders() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List orders = session.createQuery("FROM Order").list();
            for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
                Order order = (Order) iterator.next();
                System.out.println("UserID: " + order.getUserID());
                System.out.println("Details: " + order.getDetails());
                System.out.println("Status: " + order.getStatus());
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
    public void updateOrder(Integer orderID, Integer userID, String details, String status) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Order order = (Order) session.get(Order.class, orderID);
            order.setUserID(userID);
            order.setDetails(details);
            order.setStatus(status);
            session.update(order);
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
    public void deleteOrder(Integer orderID) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Order order = (Order) session.get(Order.class, orderID);
            session.delete(order);
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
    public List<Order> getOrders() {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Order> orders = null;

        try {
            tx = session.beginTransaction();
            orders = session.createQuery("FROM Order").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return orders;
    }

    @Override
    public List<Order> getUserOrders(Integer userID) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Order> orders = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Order WHERE userID = :userID");
            query.setInteger("userID", userID);
            orders = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return orders;
    }

    @Override
    public List<Order> getUserDeliveredOrders(Integer userID) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Order> orders = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Order WHERE userID = :userID AND status = 'delivered'");
            query.setInteger("userID", userID);
            orders = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return orders;
    }

    @Override
    public Order findById(Integer ID) {
        Session session = factory.openSession();
        Transaction tx = null;
        Order order = null;

        try {
            tx = session.beginTransaction();
            order = (Order) session.get(Order.class, ID);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return order;
    }
}
