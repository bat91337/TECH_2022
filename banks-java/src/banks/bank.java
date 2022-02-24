package banks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import banks.Exception.banksException;
import banks.bankAccounts.creditscore;
import banks.bankAccounts.debitscore;
import banks.bankAccounts.depositscore;
import banks.observer.iobserver;
import banks.observer.iobserevable;


public class bank implements iobserevable, iobserver{
    public bank(double percentDebitScore, double percentCreditScore, String name, double limit, double percentDepositScore)
    {
        Client = new ArrayList<client>();
        Observers = new ArrayList<iobserver>();
        PercentDebitScore = percentDebitScore;
        PercentCreditScore = percentCreditScore;
        Message = new ArrayList<String>();
        Name = name;
        Id = UUID.randomUUID().toString();
        Accounts = new ArrayList<account>();
        LimitCreditScore = limit;
        CurrentDate = LocalDateTime.now();
        DictionaryDeposit = new ArrayList<dictionaryDeposit>();
        TransactionsList = new ArrayList<transactions>();
        PercentDepositScore = percentDepositScore;
    }

    private List<String> Message;
    private double PercentDebitScore;
    private double PercentCreditScore;
    private double PercentDepositScore;
    private double LimitCreditScore;
    private String Name;
    private String Id;
    private List<account> Accounts;
    private LocalDateTime CurrentDate;
    private List<dictionaryDeposit> DictionaryDeposit;
    private List<transactions> TransactionsList;
    private List<client> Client;

    public List<iobserver> getObservers() {
        return Observers;
    }

    public void setObservers(List<iobserver> observers) {
        Observers = observers;
    }

    private List<iobserver> Observers;

    public List<client> getClient() {
        return Client;
    }

    public void setClient(List<client> client) {
        Client = client;
    }

    public List<String> getMessage() {
        return Message;
    }

    public void setMessage(List<String> message) {
        Message = message;
    }

    public double getPercentDebitScore() {
        return PercentDebitScore;
    }

    public void setPercentDebitScore(double percentDebitScore) {
        PercentDebitScore = percentDebitScore;
    }

    public double getPercentCreditScore() {
        return PercentCreditScore;
    }

    public void setPercentCreditScore(double percentCreditScore) {
        PercentCreditScore = percentCreditScore;
    }

    public double getPercentDepositScore() {
        return PercentDepositScore;
    }

    public void setPercentDepositScore(double percentDepositScore) {
        PercentDepositScore = percentDepositScore;
    }

    public double getLimitCreditScore() {
        return LimitCreditScore;
    }

    public void setLimitCreditScore(double limitCreditScore) {
        LimitCreditScore = limitCreditScore;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public List<account> getAccounts() {
        return Accounts;
    }

    public void setAccounts(List<account> accounts) {
        Accounts = accounts;
    }

    public LocalDateTime getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(LocalDateTime currentDate) {
        CurrentDate = currentDate;
    }

    public List<dictionaryDeposit> getDictionaryDeposit() {
        return DictionaryDeposit;
    }

    public void setDictionaryDeposit(List<dictionaryDeposit> dictionaryDeposit) {
        DictionaryDeposit = dictionaryDeposit;
    }

    public List<transactions> getTransactionsList() {
        return TransactionsList;
    }

    public void setTransactionsList(List<transactions> transactionsList) {
        TransactionsList = transactionsList;
    }
    public void ChangePercentCreditScore(double newPercent)
    {
        PercentCreditScore = newPercent;
        String message = "your percent has changed to" + PercentDebitScore;
        NotifyObservers(message);
    }

    public void ChangePercentDebitScore(double newPercent)
    {
        PercentDebitScore = newPercent;
        String message = "your percent has changed to" + PercentDebitScore;
        NotifyObservers(message);
    }

    public void ChangePercentDepositScore(double newPercent)
    {
        PercentDepositScore = newPercent;
        String message = "your percent has changed to" + PercentDepositScore;
        NotifyObservers(message);
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

    public void Update(String str)
    {
        Message.add(str);
    }

    public client CreateClient(client client)
    {
        AddObserver(client);
        Client.add(client);
        return client;
    }

    public account CreateAccount(client client)
    {
        var account = new account(client);
        Accounts.add(account);
        return account;
    }

    public void AddAddress(String passport, String address) throws banksException {

        Stream<client> clientStream = Client.stream();
        clientStream = clientStream.filter(client1 -> client1.getPassport().equals(passport));
        if (address.isBlank())
        {
            throw new banksException("incorrect data entered");
        }
        clientStream.forEach(client1 -> client1.setAddress(address));
    }

    public void AddNumberPhone(String passport, String numberPhone) throws banksException {
        Stream<client> clientStream = Client.stream();
        clientStream = clientStream.filter(client1 -> client1.getPassport().equals(passport));
        if (numberPhone.isBlank())
        {
            throw new banksException("incorrect data entered");
        }

        clientStream.forEach(client1 -> client1.setNumberPhone(numberPhone));
    }

    public creditscore CreateCreditScore(String id, double money) throws banksException {
        account account = Accounts.stream()
                .filter(account1 -> account1.getId().equals(id))
                .findFirst()
                .orElse(null);
        money += LimitCreditScore;
        if (account != null)
        {
            var creditScore = new creditscore(money, PercentCreditScore, LimitCreditScore, account.getClient(), LocalDateTime.now());
            account.getScores().add(creditScore);
            return creditScore;
        }

        throw new banksException("failed to create credit score");
    }

    public debitscore CreateDebitScore(String id, double money) throws banksException {
        account account = Accounts.stream()
                .filter(account1 -> account1.getId().equals(id))
                .findFirst()
                .orElse(null);
        final double limit = 0;
        if (account != null)
        {
            var debitScore = new debitscore(money, PercentDebitScore, limit, account.getClient(), LocalDateTime.now());
            account.getScores().add(debitScore);
            return debitScore;
        }

        throw new banksException("failed to create debit score");
    }

    public depositscore CreateDepositScore(String account, double money) throws banksException {
        for (account account1 : Accounts)
        {
            if (account.equals(account1.getId()))
            {
                double limit = money;
                for (var pair : DictionaryDeposit)
                {
                    if (pair.getKey() > money)
                    {
                        var depositScore = new depositscore(money, pair.getValue(), limit, account1.getClient(), LocalDateTime.now());
                        account1.getScores().add(depositScore);
                        return depositScore;
                    }
                }
            }
        }

        throw new banksException("failed to create deposit score");
    }

    public void ChargePercent(int days){
        var scores = Accounts.stream()
                .flatMap(account -> account.getScores().stream());
        scores.forEach(score -> {
            score.ChargePercent(days);
        });

    }

    public void RaiseMoney(String id, double money) {
        Stream<account> clientStream = Accounts.stream().filter(account1 -> account1.getId().equals(id));
        clientStream = clientStream.filter(account1 -> account1.getId().equals(id));
        Stream<bankAccount> bankAccount = clientStream.flatMap(account1 -> account1.getScores().stream());
        bankAccount.forEach(score -> {

            score.RaiseMoney(money);
        });
        var transaction = new transactions(id, money);
        TransactionsList.add(transaction);
    }

    public void PutMoney(String idAccount, double money, String idBankAccount) throws banksException {
        account account = Accounts.stream()
                .filter(account1 -> account1.getId().equals(idAccount))
                .findFirst()
                .orElse(null);
        bankAccount score = account.getScores().stream()
                .filter(score1 -> score1.getNumberScore().equals(idBankAccount))
                .findFirst()
                .orElse(null);
        var transaction = new transactions(idBankAccount, money);
        TransactionsList.add(transaction);
        score.PutMoney(money);
    }

    public bankAccount SearchScore(String id)
    {
        for (account account : Accounts)
        {
            for (var score : account.getScores())
            {
                if (score.getNumberScore().equals(id))
                {
                    return score;
                }
            }
        }

        return null;
    }

    public transactions Transaction(String numberScoreSender, String numberScoreBeneficiary, double sum)
    {
        bankAccount score = SearchScore(numberScoreSender);
        bankAccount score1 = SearchScore(numberScoreBeneficiary);
        double scoreMoney1 = score.getScoreMoney();
        double scoreMoney2 = score1.getScoreMoney();
        scoreMoney2 += sum;
        scoreMoney1 -= sum;
        score.setScoreMoney(scoreMoney1);
        score1.setScoreMoney(scoreMoney2);
        var transaction = new transactions(numberScoreSender, sum, numberScoreBeneficiary);
        TransactionsList.add(transaction);
        return transaction;
    }

    public void CancelTransaction(String id) throws banksException {
        for (transactions transaction : TransactionsList)
        {
            if (transaction.getNumberScoreBeneficiary().isBlank())
            {
                throw new banksException("number score beneficiary is empty");
            }

            if (transaction.getId().equals(id))
            {
                bankAccount score = SearchScore(transaction.getNumberScoreSender());
                bankAccount score1 = SearchScore(transaction.getNumberScoreBeneficiary());
                if (score != null && score1 != null)
                {
                    double scoreMoney1 = score.getScoreMoney();
                    double scoreMoney2 = score.getScoreMoney();
                    scoreMoney1 += transaction.getSum();
                    scoreMoney2 -= transaction.getSum();
                    score.setScoreMoney(scoreMoney1);
                    score.setScoreMoney(scoreMoney2);
                }
                else
                {
                    throw new banksException("number score is empty");
                }
            }
        }
    }

    public void CancelNotify(iobserver observer)
    {
        Observers.remove(observer);
    }

    public LocalDateTime AddDays(int days)
    {
        CurrentDate = CurrentDate.plusDays(days);
        return CurrentDate;
    }
}
