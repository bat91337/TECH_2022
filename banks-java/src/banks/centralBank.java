package banks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import banks.bankAccounts.creditscore;
import banks.bankAccounts.debitscore;
import banks.observer.iobserevable;
import banks.observer.iobserver;

public class centralBank implements iobserevable {

    private static centralBank _instance;
    private List<bank> _banks;
    private List<iobserver> observers;
    public centralBank()
    {
        _banks = new ArrayList<bank>();
        currentDate = LocalDateTime.now();
        observers = new ArrayList<iobserver>();
    }

    public LocalDateTime currentDate;
    public static centralBank GetInstance(String name, double percentDebitScore, double percentCreditScore, double percentDepositScore)
    {
        if (_instance == null)
            _instance = new centralBank();
        return _instance;
    }

    public void addObserver(iobserver iObserver)
    {
        observers.add(iObserver);
    }

    public void removeObserver(iobserver iObserver)
    {
        observers.remove(iObserver);
    }

    public void notifyObservers(String message)
    {
        for (iobserver observer : observers)
        {
            observer.update(message);
        }
    }

    public bank createBank(String name, double percentDebitScore, double percentCreditScore, double limit, double key, double value, double percentDepositScore)
    {
        var bank = new bank(percentDebitScore, percentCreditScore, name, limit, percentDepositScore);
        var dictionaryDeposit = new dictionaryDeposit(key, value);
        bank.getDictionaryDeposits().add(dictionaryDeposit);
        _banks.add(bank);
        addObserver(bank);
        return bank;
    }

    public void changePercentDebitScore(double percent, debitscore debitScore)
    {
        debitScore.setPercent(percent);
        String message = "your percent has changed to" + debitScore.getPercent();
        notifyObservers(message);
    }

    public void changePercentDepositScore(double key, double value)
    {
        for (bank bank : _banks)
        {
            var dictionaryDeposit = new dictionaryDeposit(key, value);
            bank.getDictionaryDeposits().add(dictionaryDeposit);
        }

        String message = "your percent has changed to";
        notifyObservers(message);
    }

    public void changePercentCreditScore(double percent, creditscore score)
    {
        score.setPercent(percent);
        String message = "your percent has changed to" + score.getPercent();
        notifyObservers(message);
    }

    public LocalDateTime addDays(int days)
    {
        currentDate = currentDate.plusDays(days);
        return currentDate;
    }
}
