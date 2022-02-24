package banks;

import java.util.UUID;

public class transactions {
    public transactions(String numberScoreSender, double sum, String numberScoreBeneficiary)
    {
        Id = UUID.randomUUID().toString();
        NumberScoreSender = numberScoreSender;
        NumberScoreBeneficiary = numberScoreBeneficiary;
        Sum = sum;
    }

    public transactions(String numberScoreSender, double sum)
    {
        Id = UUID.randomUUID().toString();
        NumberScoreSender = numberScoreSender;
        Sum = sum;
    }
    private String Id;
    private String NumberScoreSender;
    private String NumberScoreBeneficiary;
    private double Sum;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNumberScoreSender() {
        return NumberScoreSender;
    }

    public void setNumberScoreSender(String numberScoreSender) {
        NumberScoreSender = numberScoreSender;
    }

    public String getNumberScoreBeneficiary() {
        return NumberScoreBeneficiary;
    }

    public void setNumberScoreBeneficiary(String numberScoreBeneficiary) {
        NumberScoreBeneficiary = numberScoreBeneficiary;
    }

    public double getSum() {
        return Sum;
    }

    public void setSum(double sum) {
        Sum = sum;
    }

}
