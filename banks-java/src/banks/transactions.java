package banks;

import java.util.UUID;

public class transactions {
    public transactions(String numberScoreSender, double sum, String numberScoreBeneficiary)
    {
        id = UUID.randomUUID().toString();
        this.numberScoreSender = numberScoreSender;
        this.numberScoreBeneficiary = numberScoreBeneficiary;
        Sum = sum;
    }

    public transactions(String numberScoreSender, double sum)
    {
        id = UUID.randomUUID().toString();
        this.numberScoreSender = numberScoreSender;
        Sum = sum;
    }
    private String id;
    private String numberScoreSender;
    private String numberScoreBeneficiary;
    private double Sum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumberScoreSender() {
        return numberScoreSender;
    }

    public void setNumberScoreSender(String numberScoreSender) {
        this.numberScoreSender = numberScoreSender;
    }

    public String getNumberScoreBeneficiary() {
        return numberScoreBeneficiary;
    }

    public void setNumberScoreBeneficiary(String numberScoreBeneficiary) {
        this.numberScoreBeneficiary = numberScoreBeneficiary;
    }

    public double getSum() {
        return Sum;
    }

    public void setSum(double sum) {
        Sum = sum;
    }

}
