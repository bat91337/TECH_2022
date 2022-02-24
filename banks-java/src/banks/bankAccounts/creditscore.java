package banks.bankAccounts;
import banks.bankAccount;
import banks.client;
import banks.Exception.banksException;
import java.time.LocalDateTime;

public class creditscore extends bankAccount {
    public creditscore(double scoreMoney, double percent, double limit, client client, LocalDateTime dateTime)
    {
        super(scoreMoney, percent, limit, client, dateTime);
    }

    public @Override void RaiseMoney(double money)throws banksException
    {
        if (getScoreMoney() > money)
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

    public @Override void PutMoney(double money)
    {
        double ScoreMoney1 = getScoreMoney();
        ScoreMoney1 += money;
        setScoreMoney(ScoreMoney1);
    }

    public @Override void ChargePercent(int days)
    {
        double ScoreMoney1 = getScoreMoney();
        double Limit1 = getLimit();
        double commission = Limit1 - ScoreMoney1;
        if (commission > 0)
        {
            double percent1 = getPercent();
            double ScoreMoney2 = getScoreMoney();
            double commissionMonth = commission * percent1 * days;
            ScoreMoney2 -= commissionMonth;
            setScoreMoney(ScoreMoney2);
        }
    }
}
