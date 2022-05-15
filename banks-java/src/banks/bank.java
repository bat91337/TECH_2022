package banks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import banks.exception.banksException;
import banks.bankAccounts.creditscore;
import banks.bankAccounts.debitscore;
import banks.bankAccounts.depositscore;
import banks.observer.iobserver;
import banks.observer.iobserevable;


public class bank implements iobserevable, iobserver{
    public bank(double percentDebitScore, double percentCreditScore, String name, double limit, double percentDepositScore)
    {
        client = new ArrayList<client>();
        observers = new ArrayList<iobserver>();
        this.percentDebitScore = percentDebitScore;
        this.percentCreditScore = percentCreditScore;
        message = new ArrayList<String>();
        this.name = name;
        id = UUID.randomUUID().toString();
        accounts = new ArrayList<account>();
        limitCreditScore = limit;
        currentDate = LocalDateTime.now();
        dictionaryDeposits = new ArrayList<dictionaryDeposit>();
        transactions = new ArrayList<transactions>();
        this.percentDepositScore = percentDepositScore;
    }

    private List<String> message;
    private double percentDebitScore;
    private double percentCreditScore;
    private double percentDepositScore;
    private double limitCreditScore;
    private String name;
    private String id;
    private List<account> accounts;
    private LocalDateTime currentDate;
    private List<dictionaryDeposit> dictionaryDeposits;
    private List<transactions> transactions;
    private List<client> client;

    public List<iobserver> getObservers() {
        return observers;
    }

    public void setObservers(List<iobserver> observers) {
        this.observers = observers;
    }

    private List<iobserver> observers;

    public List<client> getClient() {
        return client;
    }

    public void setClient(List<client> client) {
        this.client = client;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public double getPercentDebitScore() {
        return percentDebitScore;
    }

    public void setPercentDebitScore(double percentDebitScore) {
        this.percentDebitScore = percentDebitScore;
    }

    public double getPercentCreditScore() {
        return percentCreditScore;
    }

    public void setPercentCreditScore(double percentCreditScore) {
        this.percentCreditScore = percentCreditScore;
    }

    public double getPercentDepositScore() {
        return percentDepositScore;
    }

    public void setPercentDepositScore(double percentDepositScore) {
        this.percentDepositScore = percentDepositScore;
    }

    public double getLimitCreditScore() {
        return limitCreditScore;
    }

    public void setLimitCreditScore(double limitCreditScore) {
        this.limitCreditScore = limitCreditScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<account> accounts) {
        this.accounts = accounts;
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDateTime currentDate) {
        this.currentDate = currentDate;
    }

    public List<dictionaryDeposit> getDictionaryDeposits() {
        return dictionaryDeposits;
    }

    public void setDictionaryDeposits(List<dictionaryDeposit> dictionaryDeposits) {
        this.dictionaryDeposits = dictionaryDeposits;
    }

    public List<transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<transactions> transactions) {
        this.transactions = transactions;
    }

    public void changePercentCreditScore(double newPercent)
    {
        percentCreditScore = newPercent;
        String message = "your percent has changed to" + percentDebitScore;
        notifyObservers(message);
    }

    public void changePercentDebitScore(double newPercent)
    {
        percentDebitScore = newPercent;
        String message = "your percent has changed to" + percentDebitScore;
        notifyObservers(message);
    }

    public void changePercentDepositScore(double newPercent)
    {
        percentDepositScore = newPercent;
        String message = "your percent has changed to" + percentDepositScore;
        notifyObservers(message);
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

    public void update(String str)
    {
        message.add(str);
    }

    public client createClient(client client)
    {
        addObserver(client);
        this.client.add(client);
        return client;
    }

    public account createAccount(client client)
    {
        var account = new account(client);
        accounts.add(account);
        return account;
    }

    public void addAddress(String passport, String address) throws banksException {

        Stream<client> clientStream = client.stream();
        clientStream = clientStream.filter(client1 -> client1.getPassport().equals(passport));
        if (address.isBlank())
        {
            throw new banksException("incorrect data entered");
        }
        clientStream.forEach(client1 -> client1.setAddress(address));
    }

    public void addNumberPhone(String passport, String numberPhone) throws banksException {
        Stream<client> clientStream = client.stream();
        clientStream = clientStream.filter(client1 -> client1.getPassport().equals(passport));
        if (numberPhone.isBlank())
        {
            throw new banksException("incorrect data entered");
        }

        clientStream.forEach(client1 -> client1.setNumberPhone(numberPhone));
    }

    public creditscore createCreditScore(String id, double money) throws banksException {
        account account = accounts.stream()
                .filter(account1 -> account1.getId().equals(id))
                .findFirst()
                .orElse(null);
        money += limitCreditScore;
        if (account != null)
        {
            var creditScore = new creditscore(money, percentCreditScore, limitCreditScore, account.getClient(), LocalDateTime.now());
            account.getScores().add(creditScore);
            return creditScore;
        }

        throw new banksException("failed to create credit score");
    }

    public debitscore createDebitScore(String id, double money) throws banksException {
        account account = accounts.stream()
                .filter(account1 -> account1.getId().equals(id))
                .findFirst()
                .orElse(null);
        final double limit = 0;
        if (account != null)
        {
            var debitScore = new debitscore(money, percentDebitScore, limit, account.getClient(), LocalDateTime.now());
            account.getScores().add(debitScore);
            return debitScore;
        }

        throw new banksException("failed to create debit score");
    }

    public depositscore createDepositScore(String account, double money) throws banksException {
        for (account account1 : accounts)
        {
            if (account.equals(account1.getId()))
            {
                double limit = money;
                for (var pair : dictionaryDeposits)
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

    public void chargePercent(int days){
        var scores = accounts.stream()
                .flatMap(account -> account.getScores().stream());
        scores.forEach(score -> {
            score.chargePercent(days);
        });

    }

    public void raiseMoney(String id, double money) {
        Stream<account> clientStream = accounts.stream().filter(account1 -> account1.getId().equals(id));
        clientStream = clientStream.filter(account1 -> account1.getId().equals(id));
        Stream<bankAccount> bankAccount = clientStream.flatMap(account1 -> account1.getScores().stream());
        bankAccount.forEach(score -> {

            score.raiseMoney(money);
        });
        var transaction = new transactions(id, money);
        transactions.add(transaction);
    }

    public void putMoney(String idAccount, double money, String idBankAccount) throws banksException {
        account account = accounts.stream()
                .filter(account1 -> account1.getId().equals(idAccount))
                .findFirst()
                .orElse(null);
        bankAccount score = account.getScores().stream()
                .filter(score1 -> score1.getNumberScore().equals(idBankAccount))
                .findFirst()
                .orElse(null);
        var transaction = new transactions(idBankAccount, money);
        transactions.add(transaction);
        score.putMoney(money);
    }

    public bankAccount searchScore(String id)
    {
        for (account account : accounts)
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

    public transactions transaction(String numberScoreSender, String numberScoreBeneficiary, double sum)
    {
        bankAccount score = searchScore(numberScoreSender);
        bankAccount score1 = searchScore(numberScoreBeneficiary);
        double scoreMoney1 = score.getScoreMoney();
        double scoreMoney2 = score1.getScoreMoney();
        scoreMoney2 += sum;
        scoreMoney1 -= sum;
        score.setScoreMoney(scoreMoney1);
        score1.setScoreMoney(scoreMoney2);
        var transaction = new transactions(numberScoreSender, sum, numberScoreBeneficiary);
        transactions.add(transaction);
        return transaction;
    }

    public void cancelTransaction(String id) throws banksException {
        for (transactions transaction : transactions)
        {
            if (transaction.getNumberScoreBeneficiary().isBlank())
            {
                throw new banksException("number score beneficiary is empty");
            }

            if (transaction.getId().equals(id))
            {
                bankAccount score = searchScore(transaction.getNumberScoreSender());
                bankAccount score1 = searchScore(transaction.getNumberScoreBeneficiary());
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

    public void cancelNotify(iobserver observer)
    {
        observers.remove(observer);
    }

    public LocalDateTime addDays(int days)
    {
        currentDate = currentDate.plusDays(days);
        return currentDate;
    }
}
