import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EventManagerCLI {
    private EventManager eventManager;

    public EventManagerCLI() {
        eventManager = EventManager.getInstance();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Tapahtumakirjuri v.0.1.0 by Topias");

        while (true) {
            System.out.println("\nEnter a command (type 'help' for available commands):");
            input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "help":
                    displayHelp();
                    break;
                case "list":
                    displayAllEvents();
                    break;
                case "add":
                    addNewEvent(scanner);
                    break;
                case "remove":
                    removeEventsByCategory(scanner);
                    break;
                case "quit":
                    System.out.println("Exiting Event Manager CLI...");
                    return;
                default:
                    System.out.println("Invalid command! Type 'help' for available commands.");
            }
        }
    }

    private void displayHelp() {
        System.out.println("Available commands:");
        System.out.println("- list: Display all events");
        System.out.println("- add: Add a new event");
        System.out.println("- remove: Remove events by category");
        System.out.println("- quit: Exit the program");
    }

    private void displayAllEvents() {
        List<Event> events = eventManager.getEvents();
        System.out.println("All Events:");
        for (Event event : events) {
            System.out.println(event);
        }
    }

    private void addNewEvent(Scanner scanner) {
        System.out.println("Enter date (yyyy-MM-dd):");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        System.out.println("Enter description:");
        String description = scanner.nextLine();

        System.out.println("Enter category:");
        String category = scanner.nextLine();

        eventManager.addNewEvent(date, description, category);
        System.out.println("Event added successfully!");
    }

    private void removeEventsByCategory(Scanner scanner) {
        System.out.println("Enter category to remove:");
        String category = scanner.nextLine();
        eventManager.removeEventsByCategory(category);
        System.out.println("Events with category '" + category + "' removed successfully!");
    }

    public static void main(String[] args) {
        EventManagerCLI cli = new EventManagerCLI();
        cli.start();
    }
}
