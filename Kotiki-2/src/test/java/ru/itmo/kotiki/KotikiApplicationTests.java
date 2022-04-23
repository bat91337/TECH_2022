//package ru.itmo.kotiki;
//
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import ru.itmo.kotiki.entitites.Colors;
//import ru.itmo.kotiki.entitites.Kotiki;
//import ru.itmo.kotiki.entitites.Owner;
//import ru.itmo.kotiki.repository.KotikiRepository;
//import ru.itmo.kotiki.service.KotikiServiceImpl;
//
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class KotikiApplicationTests {
//    @Autowired
//    private KotikiServiceImpl kotikiService;
//    @Spy
//    private KotikiRepository kotikiRepository;
//    @Test
//    public void createKotik() {
//        Long key = Long.valueOf(1);
//        Owner owner = new Owner(key, "vasya", "2020-03-02");
//        Kotiki kotik = new Kotiki(key, "vasya", "2020-03-02", "l",owner, Colors.RED);
//        when(kotikiRepository.save(kotik)).thenReturn(new Kotiki(key, "vasya", "2020-03-02", "l",owner, Colors.RED));
//        Kotiki kotiki  = kotikiService.readById(key);
//        Assert.assertEquals(key, kotiki.getId());
//    }
//}
