import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventManager {
    private static final EventManager INSTANCE = new EventManager();
    private List<Event> events;

    private EventManager() {
        events = new ArrayList<>();
    }

    public static EventManager getInstance() {
        return INSTANCE;
    }

    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public void setEventsPath(String path) {
        this.eventsPath = path;
    }

    private String eventsPath;

    public boolean readEvents() {
        if (eventsPath == null || eventsPath.isEmpty()) {
            System.err.println("No events file path");
            return false;
        }

        File eventsFile = new File(eventsPath);
        if (!eventsFile.exists()) {
            System.err.println("Event file " + eventsPath + " not found");
            return false;
        }

        events.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(eventsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.err.println("Invalid event format: " + line);
                    continue;
                }

                String dateStr = parts[0];
                LocalDate date;
                try {
                    date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
                } catch (Exception ex) {
                    System.err.println("Invalid date format: " + dateStr);
                    continue;
                }

                String description = parts[1];
                String category = parts[2];

                events.add(new Event(date, description, category));
            }
        } catch (IOException e) {
            System.err.println("Error reading events file: " + e.getMessage());
            return false;
        }

        return true;
    }

    public List<Event> filterEventsByCategory(String category) {
        return events.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // methodi ennen päivämäärää -filtteröinriin
    public List<Event> filterEventsBeforeDate(LocalDate date) {
        return events.stream()
                .filter(e -> e.getDate().isBefore(date))
                .collect(Collectors.toList());
    }

    // metodi jälkeen päivämäärän -filtteröinriin
    public List<Event> filterEventsAfterDate(LocalDate date) {
        return events.stream()
                .filter(e -> e.getDate().isAfter(date))
                .collect(Collectors.toList());
    }

    // metodi tämän päiväisille tapahtumille
    public List<Event> filterEventsToday() {
        LocalDate today = LocalDate.now();
        return events.stream()
                .filter(e -> e.getDate().isEqual(today))
                .collect(Collectors.toList());
    }

    // metodi kategorioille, jotka eivät ole listan mukaisia
    public List<Event> filterEventsByExcludedCategories(List<String> excludedCategories) {
        return events.stream()
                .filter(e -> !excludedCategories.contains(e.getCategory()))
                .collect(Collectors.toList());
    }

    // metodi uusien kategorioiden lisäämiseen
    public void addNewEvent(LocalDate date, String description, String category) {
        events.add(new Event(date, description, category));
    }

    // metodi eventtien poistoa varten
    public void removeEventsByCategory(String category) {
        events.removeIf(e -> e.getCategory().equalsIgnoreCase(category));
    }
}
