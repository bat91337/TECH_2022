package banks;

import java.time.LocalDateTime;
import java.util.UUID;
import banks.Exception.banksException;

public abstract class bankAccount {
    public bankAccount(double scoreMoney, double percent, double limit, client client, LocalDateTime dateTime)
    {
        ScoreMoney = scoreMoney;
        Percent = percent;
        Limit = limit;
        NumberScore = UUID.randomUUID().toString();
        Client = client;
        Date = dateTime;
    }

    private double Percent;
    private double ScoreMoney;
    private double Limit;
    private String NumberScore;
    private client Client;

    public double getPercent() {
        return Percent;
    }

    public void setPercent(double percent) {
        Percent = percent;
    }

    public double getScoreMoney() {
        return ScoreMoney;
    }

    public void setScoreMoney(double scoreMoney) {
        ScoreMoney = scoreMoney;
    }

    public double getLimit() {
        return Limit;
    }

    public void setLimit(double limit) {
        Limit = limit;
    }

    public String getNumberScore() {
        return NumberScore;
    }

    public void setNumberScore(String numberScore) {
        NumberScore = numberScore;
    }

    public client getClient() {
        return Client;
    }

    public void setClient(client client) {
        Client = client;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }

    private LocalDateTime Date;

    public abstract void RaiseMoney(double money)throws banksException;

    public abstract void PutMoney(double money)throws banksException;

    public abstract void ChargePercent(int days)throws banksException;
}
