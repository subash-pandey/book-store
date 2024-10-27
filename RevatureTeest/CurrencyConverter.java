import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Exchange rates (you can update these)
        double usdToEur = 0.85;
        double usdToGbp = 0.73;
        double usdToJpy = 109.50;

        System.out.println("Welcome to the Currency Converter!");

        while (true) {
            System.out.println("\nAvailable currencies:");
            System.out.println("1. USD (US Dollar)");
            System.out.println("2. EUR (Euro)");
            System.out.println("3. GBP (British Pound)");
            System.out.println("4. JPY (Japanese Yen)");
            System.out.println("5. Exit");

            System.out.print("Enter the number of your starting currency: ");
            int fromCurrency = scanner.nextInt();

            if (fromCurrency == 5) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter the number of your target currency: ");
            int toCurrency = scanner.nextInt();

            System.out.print("Enter the amount to convert: ");
            double amount = scanner.nextDouble();

            double convertedAmount = convertCurrency(fromCurrency, toCurrency, amount, usdToEur, usdToGbp, usdToJpy);

            if (convertedAmount != -1) {
                System.out.println(amount + " " + getCurrencyCode(fromCurrency) + " is equal to " + convertedAmount + " " + getCurrencyCode(toCurrency));
            }
        }

        scanner.close();
    }

    static double convertCurrency(int fromCurrency, int toCurrency, double amount, double usdToEur, double usdToGbp, double usdToJpy) {
        double convertedAmount = -1;

        // Convert everything to USD first
        double amountInUsd = 0;
        switch (fromCurrency) {
            case 1: // USD
                amountInUsd = amount;
                break;
            case 2: // EUR
                amountInUsd = amount / usdToEur;
                break;
            case 3: // GBP
                amountInUsd = amount / usdToGbp;
                break;
            case 4: // JPY
                amountInUsd = amount / usdToJpy;
                break;
            default:
                System.out.println("Invalid starting currency selection.");
                return convertedAmount;
        }

        // Convert from USD to target currency
        switch (toCurrency) {
            case 1: // USD
                convertedAmount = amountInUsd;
                break;
            case 2: // EUR
                convertedAmount = amountInUsd * usdToEur;
                break;
            case 3: // GBP
                convertedAmount = amountInUsd * usdToGbp;
                break;
            case 4: // JPY
                convertedAmount = amountInUsd * usdToJpy;
                break;
            default:
                System.out.println("Invalid target currency selection.");
        }

        return convertedAmount;
    }

    static String getCurrencyCode(int currencyNumber) {
        switch (currencyNumber) {
            case 1:
                return "USD";
            case 2:
                return "EUR";
            case 3:
                return "GBP";
            case 4:
                return "JPY";
            default:
                return "";
        }
    }
}