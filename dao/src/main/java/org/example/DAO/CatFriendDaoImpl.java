package org.example.DAO;

import org.example.Entities.CatFriend;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CatFriendDaoImpl implements CatFriendDao {

    public CatFriendDaoImpl() {
    }

    @Override
    public void addFriendship(Long catId, Long friendId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            CatFriend catFriend = new CatFriend(catId, friendId);
            session.save(catFriend);
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
    public void removeFriendship(Long catId, Long friendId) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(CatFriend.class, catId));
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
