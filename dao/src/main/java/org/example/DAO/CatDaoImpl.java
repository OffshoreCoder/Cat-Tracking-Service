package org.example.DAO;

import org.example.Entities.Cat;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CatDaoImpl implements CatDao{
    @Override
    public List findAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Cat", Cat.class).list();
    }

    @Override
    public Cat findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Cat.class, id);
    }

    @Override
    public void save(Cat cat) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(cat);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(cat);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(session.get(Cat.class, id));
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Cat> findFriendsByCatId(Long catId) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {

            List<Cat> friendsInitiatedByCat = session.createNativeQuery(
                            "SELECT * FROM cats WHERE cat_id IN " +
                                    "(SELECT friend_id FROM catfriends WHERE cat_id = :catId)", Cat.class)
                    .setParameter("catId", catId)
                    .getResultList();

            List<Cat> friendsReceivedByCat = session.createNativeQuery(
                            "SELECT * FROM Cats WHERE cats.cat_id IN " +
                                    "(SELECT cat_id FROM catfriends WHERE friend_id = :catId)", Cat.class)
                    .setParameter("catId", catId)
                    .getResultList();

            Set<Cat> friends = new HashSet<>(friendsInitiatedByCat);
            friends.addAll(friendsReceivedByCat);
            return friends;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }
}
