package banks.observer;

public interface iobserevable {
    public void AddObserver(iobserver observer);
    public void RemoveObserver(iobserver observer);
    public void NotifyObservers(String message);
}
