public class AutoboxingUnboxingExample {

    public static void main(String[] args) {

        // Autoboxing: Converting primitive to wrapper object
        int primitiveInt = 10;
        Integer boxedInt = primitiveInt; // Autoboxing

        System.out.println("Primitive int: " + primitiveInt);
        System.out.println("Boxed Integer: " + boxedInt);

        // Unboxing: Converting wrapper object to primitive
        Integer boxedDouble = (int) 20.5;
        double primitiveDouble = boxedDouble; // Unboxing

        System.out.println("Boxed Double: " + boxedDouble);
        System.out.println("Primitive double: " + primitiveDouble);

        // Example demonstrating autoboxing and unboxing in operations
        int sum = boxedInt + 5; // Unboxing of boxedInt, then addition
        System.out.println("Sum: " + sum);

        Integer result = (int) (primitiveDouble * 2); // Autoboxing of result
        System.out.println("Result: " + result);
    }
}