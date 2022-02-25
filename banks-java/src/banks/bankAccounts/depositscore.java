package banks.bankAccounts;
import banks.bankAccount;
import banks.exception.banksException;
import banks.client;
import java.time.LocalDateTime;

public class depositscore extends bankAccount{
    public depositscore(double scoreMoney, double percent, double limit, client client, LocalDateTime dateTime)
    {
        super(scoreMoney, percent, limit, client, dateTime);
    }

    public @Override void raiseMoney(double money)throws banksException
    {
        double ScoreMoney1 = getScoreMoney();
        double scoreMoneyInScore = ScoreMoney1 - money;
        double Limit1 = getLimit();
        if (scoreMoneyInScore >= Limit1)
        {
            ScoreMoney1 -= money;
            setScoreMoney(ScoreMoney1);
        }
        else
        {
            throw new banksException("you cannot raise money");
        }
    }

    public @Override void putMoney(double money)
    {
        if (money <= 0)
        {
            double ScoreMoney1 = getScoreMoney();
            ScoreMoney1 += money;
            setScoreMoney(ScoreMoney1);
        }
    }

    public @Override void chargePercent(int days)
    {
        double ScoreMoney1 = getScoreMoney();
        double Percent1 = getPercent();
        double commissionMonth = ScoreMoney1 * Percent1 * days;
        ScoreMoney1 += commissionMonth;
        setScoreMoney(ScoreMoney1);
    }
}
