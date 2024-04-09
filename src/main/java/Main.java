import Entities.Cat;
import Entities.Owner;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Owner owner = new Owner();
        owner.setName("Egor");
        owner.setBirthDate(new Date());
        DAO.OwnerDao ownerDao = new DAO.OwnerDaoImpl();
        ownerDao.save(owner);
        Cat cat = new Cat();
        cat.setName("Barsik");
        cat.setBirthDate(new Date());
        cat.setBreed("Russian");
        cat.setColor("White");
        cat.setOwner(owner);

        DAO.CatDao catDao = new DAO.CatDaoImpl();
        catDao.save(cat);

        List<Cat> cats = catDao.findAll();

        for (Cat c : cats) {
            System.out.println(c.getName());
        }
    }
}
