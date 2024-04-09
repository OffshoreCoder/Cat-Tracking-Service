package DAO;

import Entities.Owner;
import org.hibernate.Session;

import java.util.List;

public class OwnerDaoImpl implements OwnerDao {
    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public Owner findById(Long id) {
        return null;
    }

    @Override
    public void save(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(owner);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteById(Long id) {

    }
}
