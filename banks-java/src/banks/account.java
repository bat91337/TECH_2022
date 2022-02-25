package banks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class account {
    public account(client client)
    {
        scores = new ArrayList<bankAccount>();
        this.client = client;
        id = UUID.randomUUID().toString();
    }
    private List<bankAccount> scores;
    private client client;
    private String id;

    public List<bankAccount> getScores() {
        return scores;
    }

    public void setScores(List<bankAccount> scores) {
        this.scores = scores;
    }


    public client getClient() {
        return client;
    }

    public void setClient(client client) {
        this.client = client;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
