package banks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import banks.bankAccounts.creditscore;
import banks.bankAccounts.depositscore;
import banks.bankAccounts.debitscore;
import banks.observer.iobserevable;
import banks.observer.iobserver;

public class centralBank implements iobserevable {

    private static centralBank _instance;
    private List<bank> _banks;
    private List<iobserver> Observers;
    public centralBank()
    {
        _banks = new ArrayList<bank>();
        CurrentDate = LocalDateTime.now();
        Observers = new ArrayList<iobserver>();
    }

    public LocalDateTime CurrentDate;
    public static centralBank GetInstance(String name, double percentDebitScore, double percentCreditScore, double percentDepositScore)
    {
        if (_instance == null)
            _instance = new centralBank();
        return _instance;
    }

    public void AddObserver(iobserver iObserver)
    {
        Observers.add(iObserver);
    }

    public void RemoveObserver(iobserver iObserver)
    {
        Observers.remove(iObserver);
    }

    public void NotifyObservers(String message)
    {
        for (iobserver observer : Observers)
        {
            observer.Update(message);
        }
    }

    public bank CreateBank(String name, double percentDebitScore, double percentCreditScore, double limit, double key, double value, double percentDepositScore)
    {
        var bank = new bank(percentDebitScore, percentCreditScore, name, limit, percentDepositScore);
        var dictionaryDeposit = new dictionaryDeposit(key, value);
        bank.getDictionaryDeposit().add(dictionaryDeposit);
        _banks.add(bank);
        AddObserver(bank);
        return bank;
    }

    public void ChangePercentDebitScore(double percent, debitscore debitScore)
    {
        debitScore.setPercent(percent);
        String message = "your percent has changed to" + debitScore.getPercent();
        NotifyObservers(message);
    }

    public void ChangePercentDepositScore(double key, double value)
    {
        for (bank bank : _banks)
        {
            var dictionaryDeposit = new dictionaryDeposit(key, value);
            bank.getDictionaryDeposit().add(dictionaryDeposit);
        }

        String message = "your percent has changed to";
        NotifyObservers(message);
    }

    public void ChangePercentCreditScore(double percent, creditscore score)
    {
        score.setPercent(percent);
        String message = "your percent has changed to" + score.getPercent();
        NotifyObservers(message);
    }

    public LocalDateTime AddDays(int days)
    {
        CurrentDate = CurrentDate.plusDays(days);
        return CurrentDate;
    }
}
