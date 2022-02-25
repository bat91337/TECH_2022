package banks.bankAccounts;
import banks.bankAccount;
import banks.client;
import banks.exception.banksException;

import java.time.LocalDateTime;

public class debitscore extends bankAccount{
    public debitscore(double scoreMoney, double percent, double limit, client client, LocalDateTime dateTime)
    {
        super(scoreMoney, percent, limit, client, dateTime);
    }

    public @Override void raiseMoney(double money)throws banksException
    {
        if (getScoreMoney() >= money)
        {
            double ScoreMoney1 = getScoreMoney();
            ScoreMoney1 -= money;
            setScoreMoney(ScoreMoney1);
        }
        else
        {
            throw new banksException("no money");
        }
    }

    public @Override void putMoney(double money)
    {
        double ScoreMoney1 = getScoreMoney();
        ScoreMoney1 += money;
        setScoreMoney(ScoreMoney1);
    }

    public @Override void chargePercent(int days)
    {
        double ScoreMoney1 = getScoreMoney();
        double Limit1 = getLimit();
        double commission = Limit1 - ScoreMoney1;
        if (commission > 0)
        {
            double percent1 = getPercent();
            double ScoreMoney2 = getScoreMoney();
            double commissionMonth = commission * percent1 * days;
            ScoreMoney2 += commissionMonth;
            setScoreMoney(ScoreMoney2);
        }
    }
}
