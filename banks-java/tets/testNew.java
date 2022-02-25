import banks.centralBank;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import banks.bank;
import banks.exception.banksException;
import banks.builder.clientBuilder;
import banks.dictionaryDeposit;
import banks.client;
import banks.account;
import banks.bankAccount;
public class testNew {
    private centralBank _centralBank;
    @Before
    public void Setup()
    {
        _centralBank = new centralBank();
    }

    @Test
    public void ChargePercentForCreditScore() throws banksException {
        bank bank = _centralBank.createBank("сбер", 1, 2, 7000, 50000, 3, 1);
        dictionaryDeposit dictionaryDeposit1 = new dictionaryDeposit(10000, 4);
        dictionaryDeposit dictionaryDeposit2 = new dictionaryDeposit(10001, 5);
        bank.getDictionaryDeposits().add(dictionaryDeposit1);
        bank.getDictionaryDeposits().add(dictionaryDeposit2);
        var clientBuilder = new clientBuilder();
        clientBuilder
                .setAddress("dasd")
                .setName("dasdaw")
                .setPassport("dasda")
                .setNumberPhone("dawdawd")
                .setFirstName("dwdsdsffd");
        client client =clientBuilder.build();
        bank.createClient(client);
        account account = bank.createAccount(client);
        bankAccount bankAccount = bank.createCreditScore(account.getId(), 0);
        bank.raiseMoney(account.getId(), 100);
        _centralBank.addDays(30);
        bank.addDays(30);
        bank.chargePercent(30);
        Assert.assertEquals( bankAccount.getScoreMoney() , 900, 01);
    }

    @Test
    public void Transaction() throws banksException {
        bank bank = _centralBank.createBank("сбер", 1, 2, 7000, 50000, 3, 1);
        dictionaryDeposit dictionaryDeposit1 = new dictionaryDeposit(10000, 4);
        dictionaryDeposit dictionaryDeposit2 = new dictionaryDeposit(10001, 5);
        bank.getDictionaryDeposits().add(dictionaryDeposit1);
        bank.getDictionaryDeposits().add(dictionaryDeposit2);
        var clientBuilder = new clientBuilder();
        clientBuilder
                .setAddress("dasd")
                .setName("dasdaw")
                .setPassport("dasda")
                .setNumberPhone("dawdawd")
                .setFirstName("dwdsdsffd");
        client client =clientBuilder.build();
        bank.createClient(client);
        account account = bank.createAccount(client);
        bankAccount bankAccount = bank.createDebitScore(account.getId(), 1000);
        var clientBuilder1 = new clientBuilder();
        clientBuilder1
                .setAddress("dasd")
                .setName("dasdaw")
                .setPassport("dasda")
                .setNumberPhone("dawdawd")
                .setFirstName("dwdsdsffd");
        client client1 =clientBuilder1.build();
        bank.createClient(client1);
        account account1 = bank.createAccount(client);
        bankAccount bankAccount1 = bank.createDebitScore(account.getId(), 0);
        bank.transaction(bankAccount.getNumberScore(), bankAccount1.getNumberScore(), 600 );
        Assert.assertEquals(bankAccount.getScoreMoney(), 400, 01);
    }
}
