package banks.observer;

public interface iobserevable {
    public void addObserver(iobserver observer);
    public void removeObserver(iobserver observer);
    public void notifyObservers(String message);
}
