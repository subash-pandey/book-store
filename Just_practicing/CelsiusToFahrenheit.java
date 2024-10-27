public class CelsiusToFahrenheit {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a Celsius temperature as a command line argument.");
           return;
        }

        try {
            double celsius = Double.parseDouble(args[0]);
            double fahrenheit = (celsius * 9 / 5) + 32;
            System.out.println(celsius + " Celsius is " + fahrenheit + " Fahrenheit.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide a valid number for Celsius temperature.");
        }
    }
}