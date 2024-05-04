import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class EventManagerTest {
    public void runTests() {
        EventManager eventManager = EventManager.getInstance();

        // lisää uusi eventti
        eventManager.addNewEvent(LocalDate.parse("2024-05-10"), "Conference X", "Conference");
        eventManager.addNewEvent(LocalDate.parse("2024-05-15"), "Workshop Y", "Workshop");

        // poista eventti kategorian mukaisesti
        eventManager.removeEventsByCategory("Product Launch");

        // filtteröi päivämäärän mukaisesti
        LocalDate specificDate = LocalDate.parse("2024-05-15");
        List<Event> eventsBeforeDate = eventManager.filterEventsBeforeDate(specificDate);
        List<Event> eventsAfterDate = eventManager.filterEventsAfterDate(specificDate);
        List<Event> eventsToday = eventManager.filterEventsToday();

        List<String> excludedCategories = List.of("Product Launch", "Technology");
        List<Event> eventsExcludedCategories = eventManager.filterEventsByExcludedCategories(excludedCategories);

        // printtaa lopputulos
        System.out.println("Events before " + specificDate + ":");
        for (Event event : eventsBeforeDate) {
            System.out.println(event);
        }

        System.out.println("\nEvents after " + specificDate + ":");
        for (Event event : eventsAfterDate) {
            System.out.println(event);
        }

        System.out.println("\nEvents happening today:");
        for (Event event : eventsToday) {
            System.out.println(event);
        }

        System.out.println("\nEvents excluding categories: " + excludedCategories);
        for (Event event : eventsExcludedCategories) {
            System.out.println(event);
        }
    }
}
