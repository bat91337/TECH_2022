import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.itmo.kotiki.Servicel.KotikServicel;
import ru.itmo.kotiki.Servicel.OwnerServicel;
import ru.itmo.kotiki.entitites.Colors;
import ru.itmo.kotiki.entitites.Kotiki;
import ru.itmo.kotiki.entitites.Owner;
import ru.itmo.kotiki.jdbc.KotikiDao;
import ru.itmo.kotiki.jdbc.OwnerDao;
import ru.itmo.kotiki.repository.KotikiRepository;
import ru.itmo.kotiki.service.KotikiService;
import ru.itmo.kotiki.service.KotikiServiceImpl;


@SpringBootTest
public class Tests {
    private KotikiDao kotikiDAO;
    private KotikServicel kotikiServicel;
    private OwnerDao ownerDAO;
    private OwnerServicel ownerServicel;
//    @Autowired
//    private KotikiService kotikiService;
//    @MockBean
//    private KotikiRepository kotikiRepository;
    @Before
    public void before() {
        kotikiDAO = Mockito.mock(KotikiDao.class);
        kotikiServicel = new KotikServicel(kotikiDAO);
        ownerDAO = Mockito.mock(OwnerDao.class);
        ownerServicel = new OwnerServicel(ownerDAO);
//        kotikiRepository = Mockito.mock(KotikiRepository.class);
//        kotikiService = new KotikiServiceImpl(kotikiRepository);

    }
    @Test
    public void readById() {
        Long key = Long.valueOf(1);
        Mockito.when(kotikiDAO.read(key)).thenReturn(getKotikiTest());
        Kotiki result = kotikiServicel.read(key);
        Assert.assertEquals(getKotikiTest().getId(), result.getId());
        Mockito.verify(kotikiDAO).read(key);

    }
    @Test
    public void createKotik()
    {
        Long key = Long.valueOf(1);
        String date = "2022-09-09";
        Owner owner = new Owner(key, "vasya", date);
        Kotiki kotik = new Kotiki(key, "vasya", date, "l", owner, Colors.RED);
        Mockito.when(kotikiDAO.read(key)).thenReturn(kotik);
        Mockito.when(ownerDAO.read(key)).thenReturn(owner);
        Kotiki kotiki = kotikiServicel.read(key);
        Owner owner1 = ownerServicel.read(key);
        owner1.getKotiks().add(kotiki);
        Mockito.verify(kotikiDAO).read(key);
        Mockito.verify(ownerDAO).read(key);
        Assert.assertEquals(getKotikiTest().getId(), kotiki.getId());
        Assert.assertEquals(owner1.getKotiks().size(), 1);
    }
//    @Test
//    public void createKotik1()
//    {
////        Long key = Long.valueOf(1);
////        String date = "2022-09-09";
////        Owner owner = new Owner(key, "vasya", date);
////        Kotiki kotik = new Kotiki(key, "vasya", date, "l", owner, Colors.RED);
////        Kotiki kotiki = kotikiService.create(kotik);
////        Mockito.doReturn(kotik).when(kotikiRepository).save(kotik);
////        Mockito.verify(kotikiRepository, Mockito.times(1)).save(kotik);
//    }

    private Kotiki getKotikiTest() {
        Long key = Long.valueOf(1);
        String date = "2022-09-09";
        Owner owner = new Owner(key, "vasya", date);
        return new Kotiki(key, "vasya", date, "l", owner, Colors.RED);
    }
}
