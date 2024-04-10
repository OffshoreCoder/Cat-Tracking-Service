import org.example.DAO.OwnerDaoImpl;
import org.example.Entities.Owner;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Owner owner = new Owner();
        owner.setName("John Doe");
        owner.setBirthDate(new Date());
        OwnerDaoImpl ownerDao = new OwnerDaoImpl();
        ownerDao.save(owner);
    }
}
