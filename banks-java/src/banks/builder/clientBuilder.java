package banks.builder;
import banks.client;

public class clientBuilder {
    private String _nameClient;
    private String _address;
    private String _passport;
    private String _firstNameClient;
    private String _numberPhone;
    public clientBuilder SetFirstNAme(String firstNAme)
    {
        _firstNameClient = firstNAme;
        return this;
    }

    public clientBuilder SetName(String name)
    {
        _nameClient = name;
        return this;
    }

    public clientBuilder SetAddress(String address)
    {
        _address = address;
        return this;
    }

    public clientBuilder SetPassport(String passport)
    {
        _passport = passport;
        return this;
    }

    public clientBuilder SetNumberPhone(String numberPhone)
    {
        _numberPhone = numberPhone;
        return this;
    }

    public client Build()
    {
        var newClient = new client(_firstNameClient, _nameClient, _passport, _address, _numberPhone);
        return newClient;
    }
}
