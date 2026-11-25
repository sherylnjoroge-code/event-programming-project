import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// DEMO: PropertyChangeListener (from java.beans)
// PropertyChangeSupport is a helper that manages the listener list for us.
public class PropertyChangeDemo {

    static class Person {
        private final PropertyChangeSupport support =
                new PropertyChangeSupport(this);
        private String name;

        public void addPropertyChangeListener(PropertyChangeListener l) {
            support.addPropertyChangeListener(l);
        }

        public void setName(String newName) {
            String oldName = this.name;
            this.name = newName;
            // Fires a PropertyChangeEvent to all listeners
            support.firePropertyChange("name", oldName, newName);
        }
    }

    public static void main(String[] args) {
        Person person = new Person();

        // Add two listeners
        person.addPropertyChangeListener(evt ->
                System.out.println("Listener A: " + evt.getPropertyName()
                        + " changed from " + evt.getOldValue()
                        + " to " + evt.getNewValue()));

        person.addPropertyChangeListener(evt ->
                System.out.println("Listener B: noticed name is now "
                        + evt.getNewValue()));

        // Trigger the change
        person.setName("Alice");
        person.setName("Bob");
    }
}