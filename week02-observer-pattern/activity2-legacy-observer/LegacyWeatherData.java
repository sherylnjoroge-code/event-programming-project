import java.util.Observable;

// LEGACY SUBJECT using java.util.Observable (deprecated)
// We must EXTEND Observable. Java allows only one parent class,
// so this is a big limitation.
@SuppressWarnings("deprecation")
public class LegacyWeatherData extends Observable {

    private float temperature;
    private float humidity;

    public void setMeasurements(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;

        // Required before notifying (marks the object as changed)
        setChanged();

        // Notify all observers (no data passed, so pull model)
        notifyObservers();
    }

    public float getTemperature() { return temperature; }
    public float getHumidity() { return humidity; }
}