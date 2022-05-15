package banks;

import java.time.LocalDateTime;
import java.util.UUID;
import banks.exception.banksException;

public abstract class bankAccount {
    public bankAccount(double scoreMoney, double percent, double limit, client client, LocalDateTime dateTime)
    {
        this.scoreMoney = scoreMoney;
        this.percent = percent;
        this.limit = limit;
        numberScore = UUID.randomUUID().toString();
        this.client = client;
        date = dateTime;
    }

    private double percent;
    private double scoreMoney;
    private double limit;
    private String numberScore;
    private client client;

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public double getScoreMoney() {
        return scoreMoney;
    }

    public void setScoreMoney(double scoreMoney) {
        this.scoreMoney = scoreMoney;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public String getNumberScore() {
        return numberScore;
    }

    public void setNumberScore(String numberScore) {
        this.numberScore = numberScore;
    }

    public client getClient() {
        return client;
    }

    public void setClient(client client) {
        this.client = client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private LocalDateTime date;

    public abstract void raiseMoney(double money)throws banksException;

    public abstract void putMoney(double money)throws banksException;

    public abstract void chargePercent(int days)throws banksException;
}
