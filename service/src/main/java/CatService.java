import org.example.DAO.CatDao;
import org.example.DAO.CatDaoImpl;
import org.example.DAO.CatFriendDaoImpl;
import org.example.Entities.Cat;
import java.util.Set;

public class CatService {
    private CatDao catDao = new CatDaoImpl();
    private CatFriendDaoImpl catFriendDao = new CatFriendDaoImpl();

    public CatService() {
    }

    public Cat getCatById(Long id) {
        return catDao.findById(id);
    }

    public void addCat(Cat cat) {
        catDao.save(cat);
    }

    public void updateCat(Cat cat) {
        catDao.update(cat);
    }

    public void deleteCat(Long id) {
        Cat cat = catDao.findById(id);
        if (cat != null) {
            catDao.deleteById(id);
        }
    }

    public Set<Cat> getFriendsByCatId(Long catId) {
        return catDao.findFriendsByCatId(catId);
    }

    public void createFriendship(Long catId, Long friendId) {
        catFriendDao.addFriendship(catId, friendId);
    }

    public void removeFriendship(Long catId, Long friendId) {
        catFriendDao.removeFriendship(catId, friendId);
    }
}
