package DAO;

import Entities.Cat;
import Entities.Owner;
import org.hibernate.Session;

import java.util.List;

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
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(cat);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List findCatsByOwner(Owner owner) {
        return List.of();
    }

    @Override
    public List findFriendsByCatId(Long id) {
        return List.of();
    }
}
