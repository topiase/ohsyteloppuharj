import java.time.LocalDate;
import java.util.ArrayList;

public class Event {
    private LocalDate date;
    private String description;
    private String category;

    public Event(LocalDate date, String description, String category) {
        this.date = date;
        this.description = description;
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Description: " + description + ", Category: " + category;
    }
}
