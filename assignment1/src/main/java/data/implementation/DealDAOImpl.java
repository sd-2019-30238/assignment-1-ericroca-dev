package data.implementation;

import data.service.DealDAO;
import models.Deal;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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
    public Integer addDeal(Double price, String name, String type, Integer quantity) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer dealID = null;

        try {
            tx = session.beginTransaction();
            Deal deal = new Deal();
            deal.setPrice(price);
            deal.setName(name);
            deal.setType(type);
            deal.setQuantity(quantity);
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
    public void updateDeal(Integer dealID, Double price, String name, String type, Integer quantity) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Deal deal = (Deal) session.get(Deal.class, dealID);
            deal.setPrice(price);
            deal.setName(name);
            deal.setType(type);
            deal.setQuantity(quantity);
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

    @Override
    public List<Deal> getDealsByPrice(Double price) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Deal> deals = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Deal WHERE price = :price");
            query.setDouble("price", price);
            deals = query.list();
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

    @Override
    public List<Deal> getDealsByName(String name) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Deal> deals = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Deal WHERE name = :name");
            query.setString("name", name);
            deals = query.list();
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

    @Override
    public List<Deal> getDealsByType(String type) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Deal> deals = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Deal WHERE type = :type");
            query.setString("type", type);
            deals = query.list();
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

    @Override
    public List<Deal> getDealsByPriceAndName(Double price, String name) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Deal> deals = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Deal WHERE price = :price AND name = :name");
            query.setDouble("price", price);
            query.setString("name", name);
            deals = query.list();
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

    @Override
    public List<Deal> getDealsByPriceAndType(Double price, String type) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Deal> deals = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Deal WHERE price = :price AND type = :type");
            query.setDouble("price", price);
            query.setString("type", type);
            deals = query.list();
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

    @Override
    public List<Deal> getDealsByNameAndType(String name, String type) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Deal> deals = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Deal WHERE name = :name AND type = :type");
            query.setString("name", name);
            query.setString("type", type);
            deals = query.list();
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

    @Override
    public List<Deal> getDealsByAll(Double price, String name, String type) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Deal> deals = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Deal WHERE price = :price AND name = :name AND type = :type");
            query.setDouble("price", price);
            query.setString("name", name);
            query.setString("type", type);
            deals = query.list();
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

    @Override
    public Deal findByName(String name) {
        Session session = factory.openSession();
        Transaction tx = null;
        Deal deal = null;

        try {
            tx = session.beginTransaction();
            deal = session.byNaturalId(Deal.class)
                    .using("name", name)
                    .load();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return deal;
    }
}
