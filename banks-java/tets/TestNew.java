import banks.centralBank;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import banks.bank;
import banks.Exception.banksException;
import banks.builder.clientBuilder;
import banks.dictionaryDeposit;
import banks.client;
import banks.account;
import banks.bankAccount;
public class TestNew {
    private centralBank _centralBank;
    @Before
    public void Setup()
    {
        _centralBank = new centralBank();
    }

    @Test
    public void ChargePercentForCreditScore() throws banksException {
        bank bank = _centralBank.CreateBank("сбер", 1, 2, 7000, 50000, 3, 1);
        dictionaryDeposit dictionaryDeposit1 = new dictionaryDeposit(10000, 4);
        dictionaryDeposit dictionaryDeposit2 = new dictionaryDeposit(10001, 5);
        bank.getDictionaryDeposit().add(dictionaryDeposit1);
        bank.getDictionaryDeposit().add(dictionaryDeposit2);
        var clientBuilder = new clientBuilder();
        clientBuilder
                .SetAddress("dasd")
                .SetName("dasdaw")
                .SetPassport("dasda")
                .SetNumberPhone("dawdawd")
                .SetFirstNAme("dwdsdsffd");
        client client =clientBuilder.Build();
        bank.CreateClient(client);
        account account = bank.CreateAccount(client);
        bankAccount bankAccount = bank.CreateCreditScore(account.getId(), 0);
        bank.RaiseMoney(account.getId(), 100);
        _centralBank.AddDays(30);
        bank.AddDays(30);
        bank.ChargePercent(30);
        Assert.assertEquals( bankAccount.getScoreMoney() , 900, 01);
    }

    @Test
    public void Transaction() throws banksException {
        bank bank = _centralBank.CreateBank("сбер", 1, 2, 7000, 50000, 3, 1);
        dictionaryDeposit dictionaryDeposit1 = new dictionaryDeposit(10000, 4);
        dictionaryDeposit dictionaryDeposit2 = new dictionaryDeposit(10001, 5);
        bank.getDictionaryDeposit().add(dictionaryDeposit1);
        bank.getDictionaryDeposit().add(dictionaryDeposit2);
        var clientBuilder = new clientBuilder();
        clientBuilder
                .SetAddress("dasd")
                .SetName("dasdaw")
                .SetPassport("dasda")
                .SetNumberPhone("dawdawd")
                .SetFirstNAme("dwdsdsffd");
        client client =clientBuilder.Build();
        bank.CreateClient(client);
        account account = bank.CreateAccount(client);
        bankAccount bankAccount = bank.CreateDebitScore(account.getId(), 1000);
        var clientBuilder1 = new clientBuilder();
        clientBuilder1
                .SetAddress("dasd")
                .SetName("dasdaw")
                .SetPassport("dasda")
                .SetNumberPhone("dawdawd")
                .SetFirstNAme("dwdsdsffd");
        client client1 =clientBuilder1.Build();
        bank.CreateClient(client1);
        account account1 = bank.CreateAccount(client);
        bankAccount bankAccount1 = bank.CreateDebitScore(account.getId(), 0);
        bank.Transaction(bankAccount.getNumberScore(), bankAccount1.getNumberScore(), 600 );
        Assert.assertEquals(bankAccount.getScoreMoney(), 400, 01);
    }
}
