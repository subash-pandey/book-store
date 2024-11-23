import java.util.ArrayList;
import java.util.List;

public class Schedule {

    private List<Task> tasks;

    public Schedule() {
        tasks = new ArrayList<>();
    }

    public void addTask(String description, DayOfWeek dayOfWeek) {
        tasks.add(new Task(description, dayOfWeek));
    }

    public void printSchedule() {
        System.out.println("----- Weekly Schedule -----");
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println("\n" + day.name() + " (" + day.getAbbreviation() + ")");
            for (Task task : tasks) {
                if (task.getDayOfWeek() == day) {
                    System.out.println("- " + task.getDescription());
                }
            }
        }
    }

    private static class Task {
        private final String description;
        private final DayOfWeek dayOfWeek;

        public Task(String description, DayOfWeek dayOfWeek) {
            this.description = description;
            this.dayOfWeek = dayOfWeek;
        }

        public String getDescription() {
            return description;
        }

        public DayOfWeek getDayOfWeek() {
            return dayOfWeek;
        }
    }

    public static void main(String[] args) {
        Schedule schedule = new Schedule();
        schedule.addTask("Grocery shopping", DayOfWeek.SATURDAY);
        schedule.addTask("Meeting with John", DayOfWeek.WEDNESDAY);
        schedule.addTask("Submit project report", DayOfWeek.FRIDAY);
        schedule.addTask("Workout", DayOfWeek.MONDAY);
        schedule.addTask("Relaxing day", DayOfWeek.SUNDAY);

        schedule.printSchedule();
    }
}