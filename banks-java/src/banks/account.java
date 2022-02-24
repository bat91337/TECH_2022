package banks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class account {
    public account(client client)
    {
        Scores = new ArrayList<bankAccount>();
        Client = client;
        Id = UUID.randomUUID().toString();
    }
    private List<bankAccount> Scores;
    private client Client;
    private String Id;

    public List<bankAccount> getScores() {
        return Scores;
    }

    public void setScores(List<bankAccount> scores) {
        Scores = scores;
    }


    public client getClient() {
        return Client;
    }

    public void setClient(client client) {
        Client = client;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

}
