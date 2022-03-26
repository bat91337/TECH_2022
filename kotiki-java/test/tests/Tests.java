import Services.KotikiService;
import Services.OwnerService;
import Entitites.Colors;
import Entitites.Kotiki;
import Entitites.Owner;
import jdbc.*;
import org.junit.*;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class Tests {
    private KotikiDAO kotikiDAO;
    private KotikiService kotikiService;
    private OwnerDAO ownerDAO;
    private OwnerService ownerService;
    @Before
    public void before() {
        kotikiDAO = Mockito.mock(KotikiDAO.class);
        kotikiService = new KotikiService(kotikiDAO);
        ownerDAO = Mockito.mock(OwnerDAO.class);
        ownerService = new OwnerService(ownerDAO);
    }
    @Test
    public void readById() {
        Long key = Long.valueOf(1);
        Mockito.when(kotikiDAO.read(key)).thenReturn(getKotikiTest());
        var result = kotikiService.read(key);
        Assert.assertEquals(getKotikiTest().getId(), result.getId());
        Mockito.verify(kotikiDAO).read(key);

    }
    @Test
    public void createKotik()
    {
        Long key = Long.valueOf(1);
        LocalDateTime date = LocalDateTime.now();
        Owner owner = new Owner(key, "vasya", date);
        Kotiki kotik = new Kotiki(key, "vasya", date, "l", owner, Colors.RED);
        Mockito.when(kotikiDAO.read(key)).thenReturn(kotik);
        Mockito.when(ownerDAO.read(key)).thenReturn(owner);
        Kotiki kotiki = kotikiService.read(key);
        Owner owner1 = ownerService.read(key);
        owner1.getKotiks().add(kotiki);
        Mockito.verify(kotikiDAO).read(key);
        Mockito.verify(ownerDAO).read(key);
        Assert.assertEquals(getKotikiTest().getId(), kotiki.getId());
        Assert.assertEquals(owner1.getKotiks().size(), 1);
    }


    private Kotiki getKotikiTest() {
        Long key = Long.valueOf(1);
        LocalDateTime date = LocalDateTime.now();
        Owner owner = new Owner(key, "vasya", date);
        return new Kotiki(key, "vasya", date, "l", owner, Colors.RED);
    }



}
