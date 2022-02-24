package banks;

import java.util.ArrayList;
import java.util.List;
import banks.observer.iobserver;

public class client implements iobserver {
    public client(String firsNameClient, String lastNameClient, String passport, String address, String numberPhone)
    {
        FirstNameClient = firsNameClient;
        LastNameClient = lastNameClient;
        Address = address;
        Passport = passport;
        Message = new ArrayList<String>();
        NumberPhone = numberPhone;
    }
    public client(String firsNameClient, String lastNameClient, String passport)
    {
        FirstNameClient = firsNameClient;
        LastNameClient = lastNameClient;
        Passport = passport;
        Message = new ArrayList<String>();
    }

    private String LastNameClient;
    private String Address;
    private String Passport;
    private String FirstNameClient;
    private List<String> Message;

    public String getLastNameClient() {
        return LastNameClient;
    }

    public void setLastNameClient(String lastNameClient) {
        LastNameClient = lastNameClient;
    }

    public String getAddress() {
        return Address;
    }

    public String getPassport() {
        return Passport;
    }

    public void setPassport(String passport) {
        Passport = passport;
    }

    public String getFirstNameClient() {
        return FirstNameClient;
    }

    public void setFirstNameClient(String firstNameClient) {
        FirstNameClient = firstNameClient;
    }

    public List<String> getMessage() {
        return Message;
    }

    public void setMessage(List<String> message) {
        Message = message;
    }

    public String getNumberPhone() {
        return NumberPhone;
    }

    private String NumberPhone;

    public void Update(String str)
    {
        Message.add(str);
    }
    public void setAddress(String address)
    {
        Address = address;
    }
    public void setNumberPhone(String numberPhone)
    {
        NumberPhone = numberPhone;
    }
}
