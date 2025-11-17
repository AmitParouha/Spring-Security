import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TodayDate {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        String formatted = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println("Today's Date: " + formatted);
    }
}
