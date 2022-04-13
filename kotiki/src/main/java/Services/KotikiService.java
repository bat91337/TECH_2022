package Services;
import Entitites.Kotiki;
import jdbc.KotikiDao;

public class KotikiService implements Service<Kotiki, Long>{
    private KotikiDao kotikDAO;

    public KotikiService(KotikiDao kotikDAO) {
        this.kotikDAO = kotikDAO;
    }

    @Override
    public void create(Kotiki kotiki) {
        kotikDAO.create(kotiki);
    }

    @Override
    public Kotiki read(Long aLong) {
        Kotiki result = kotikDAO.read(aLong);
        return result;
    }

    @Override
    public void update(Kotiki kotiki) {
        kotikDAO.update(kotiki);
    }

    @Override
    public void delete(Kotiki kotiki) {
        kotikDAO.delete(kotiki);
    }
}
