package banks;

import java.util.ArrayList;
import java.util.List;
import banks.observer.iobserver;

public class client implements iobserver {
    public client(String firsNameClient, String lastNameClient, String passport, String address, String numberPhone)
    {
        firstNameClient = firsNameClient;
        this.lastNameClient = lastNameClient;
        this.address = address;
        this.passport = passport;
        message = new ArrayList<String>();
        this.numberPhone = numberPhone;
    }
    public client(String firsNameClient, String lastNameClient, String passport)
    {
        firstNameClient = firsNameClient;
        this.lastNameClient = lastNameClient;
        this.passport = passport;
        message = new ArrayList<String>();
    }

    private String lastNameClient;
    private String address;
    private String passport;
    private String firstNameClient;
    private List<String> message;

    public String getLastNameClient() {
        return lastNameClient;
    }

    public void setLastNameClient(String lastNameClient) {
        this.lastNameClient = lastNameClient;
    }

    public String getAddress() {
        return address;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getFirstNameClient() {
        return firstNameClient;
    }

    public void setFirstNameClient(String firstNameClient) {
        this.firstNameClient = firstNameClient;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    private String numberPhone;

    public void update(String str)
    {
        message.add(str);
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public void setNumberPhone(String numberPhone)
    {
        this.numberPhone = numberPhone;
    }
}
