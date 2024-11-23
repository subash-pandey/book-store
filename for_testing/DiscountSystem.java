import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiscountSystem {

    public static void main(String[] args) {
        // Create a product.
        Product product = new Product("Product A", 100, 3);

        // Create a list of discounts.
        List<Discount> discounts = new ArrayList<>();
        discounts.add(new FridayDiscount());
        discounts.add(new ThreeForTwoDiscount());

        // Chain the discounts together.
        Discount discount = new FridayThreeForTwoDiscount(new FridayDiscount());

        // Apply the discounts to the product.
        double discountedPrice = discount.apply(product.getPrice());

        // Print the discount descriptions and discount values.
        for (Discount discount1 : discounts) {
            System.out.println(discount1.getDescription());
            System.out.println("Original Price: $" + product.getPrice());
            System.out.println("Discounted Price: $" + discount1.apply(product.getPrice()));
        }

        System.out.println("\nCombined Discount:");
        System.out.println(discount.getDescription());
        System.out.println("Original Price: $" + product.getPrice());
        System.out.println("Discounted Price: $" + discount.apply(product.getPrice()));
    }
}

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

interface DiscountRule {
    boolean isApplicable(Product product, int quantity);
}

interface Discount {
    DiscountRule getRule();

    double apply(double price);

    String getDescription();
}

abstract class DiscountDecorator implements Discount {

    private Discount discount;

    public DiscountDecorator(Discount discount) {
        this.discount = discount;
    }

    @Override
    public DiscountRule getRule() {
        return discount.getRule();
    }

    @Override
    public String getDescription() {
        return discount.getDescription();
    }

    @Override
    public double apply(double price) {
        return discount.apply(price);
    }
}

class FridayDiscount implements Discount {

    @Override
    public DiscountRule getRule() {
        return (product, quantity) -> LocalDate.now().getDayOfWeek() == DayOfWeek.FRIDAY;
    }

    @Override
    public double apply(double price) {
        return price * 0.9;
    }

    @Override
    public String getDescription() {
        return "10% discount on Fridays";
    }
}

class ThreeForTwoDiscount implements Discount {

    @Override
    public DiscountRule getRule() {
        return (product, quantity) -> quantity >= 3;
    }

    @Override
    public double apply(double price) {
        return price * 2 / 3;
    }

    @Override
    public String getDescription() {
        return "3 for 2 discount";
    }
}

class FridayThreeForTwoDiscount extends DiscountDecorator {

    public FridayThreeForTwoDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public double apply(double price) {
        double discountedPrice = super.apply(price);
        return discountedPrice * 0.9;
    }
}