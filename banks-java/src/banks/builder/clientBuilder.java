package banks.builder;
import banks.client;

public class clientBuilder {
    private String _nameClient;
    private String _address;
    private String _passport;
    private String _firstNameClient;
    private String _numberPhone;
    public clientBuilder setFirstName(String firstNAme)
    {
        _firstNameClient = firstNAme;
        return this;
    }

    public clientBuilder setName(String name)
    {
        _nameClient = name;
        return this;
    }

    public clientBuilder setAddress(String address)
    {
        _address = address;
        return this;
    }

    public clientBuilder setPassport(String passport)
    {
        _passport = passport;
        return this;
    }

    public clientBuilder setNumberPhone(String numberPhone)
    {
        _numberPhone = numberPhone;
        return this;
    }

    public client build()
    {
        var newClient = new client(_firstNameClient, _nameClient, _passport, _address, _numberPhone);
        return newClient;
    }
}
