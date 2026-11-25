// Any class that wants updates from the Subject must implement this
public interface Observer {
    // The Subject calls this when its data changes (push model)
    void update(float temperature, float humidity, float pressure);
}