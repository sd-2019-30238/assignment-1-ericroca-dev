package data.implementation;

import data.service.DealDAO;
import models.Deal;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class DealDAOImpl implements DealDAO {

    private static SessionFactory factory;

    public DealDAOImpl() {
        try {
            factory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Deal.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public Integer addDeal(Double price, String name, String type) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer dealID = null;

        try {
            tx = session.beginTransaction();
            Deal deal = new Deal();
            deal.setPrice(price);
            deal.setName(name);
            deal.setType(type);
            dealID = (Integer) session.save(deal);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return dealID;
    }

    @Override
    public void listDeals() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List deals = session.createQuery("FROM Deal").list();
            for (Iterator iterator = deals.iterator(); iterator.hasNext();) {
                Deal deal = (Deal) iterator.next();
                System.out.println("Price: " + deal.getPrice());
                System.out.println("Name: " + deal.getName());
                System.out.println("Type: " + deal.getType());
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
    public void updateDeal(Integer dealID, Double price, String name, String type) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Deal deal = (Deal) session.get(Deal.class, dealID);
            deal.setPrice(price);
            deal.setName(name);
            deal.setType(type);
            session.update(deal);
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
    public void deleteDeal(Integer dealID) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Deal deal = (Deal) session.get(Deal.class, dealID);
            session.delete(deal);
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
    public List<Deal> getDeals() {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Deal> deals = null;

        try {
            tx = session.beginTransaction();
            deals = session.createQuery("FROM Deal").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return deals;
    }
}
