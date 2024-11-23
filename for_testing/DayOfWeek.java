public enum DayOfWeek {
    MONDAY("Mon", 1),
    TUESDAY("Tue", 2),
    WEDNESDAY("Wed", 3),
    THURSDAY("Thu", 4),
    FRIDAY("Fri", 5),
    SATURDAY("Sat", 6),
    SUNDAY("Sun", 7);

    private final String abbreviation;
    private final int dayNumber;

    DayOfWeek(String abbreviation, int dayNumber) {
        this.abbreviation = abbreviation;
        this.dayNumber = dayNumber;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public static DayOfWeek fromDayNumber(int dayNumber) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getDayNumber() == dayNumber) {
                return day;
            }
        }
        throw new IllegalArgumentException("Invalid day number: " + dayNumber);
    }
}