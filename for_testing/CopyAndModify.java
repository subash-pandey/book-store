import java.lang.reflect.Constructor;

public class CopyAndModify {

    /**
     * Copies the value of 'a' to 'b', then modifies 'b' so they are not equal.
     * Works for Integer, String, and custom objects with a copy constructor.
     *
     * @param a The object to copy.
     * @param <T> The type of object.
     * @return A modified copy of 'a'.
     * @throws IllegalArgumentException If 'a' is null or of unsupported type.
     */
    public static <T> T copyAndModify(T a) {
        if (a == null) {
            throw new IllegalArgumentException("Input object 'a' cannot be null");
        }

        if (a instanceof Integer) {
            int value = (Integer) a;
            return (T) Integer.valueOf(value + 1); // Modify: Increment the integer
        } else if (a instanceof String) {
            String value = (String) a;
            return (T) (value + " (modified)"); // Modify: Append text to the string
        } else {
            try {
                // Attempt copy constructor (assuming it exists)
                Constructor<T> constructor = (Constructor<T>) a.getClass().getConstructor(a.getClass());
                T b = constructor.newInstance(a);
                // Modify: You'll need to implement type-specific modification logic here
                // For example, if 'b' has a 'setName' method:
                // Method setNameMethod = b.getClass().getMethod("setName", String.class);
                // setNameMethod.invoke(b, "Modified Name");
                return b;
            } catch (Exception e) {
                throw new IllegalArgumentException("Unsupported object type or missing copy constructor: " + a.getClass());
            }
        }
    }

    public static void main(String[] args) {
        Integer aInt = 10;
        Integer bInt = copyAndModify(aInt);
        System.out.println("aInt: " + aInt + ", bInt: " + bInt + ", Equal: " + aInt.equals(bInt));

        String aStr = "Hello";
        String bStr = copyAndModify(aStr);
        System.out.println("aStr: " + aStr + ", bStr: " + bStr + ", Equal: " + aStr.equals(bStr));

        // Example with a custom object (assuming it has a copy constructor and modification logic)
        // MyObject aObj = new MyObject("Original");
        // MyObject bObj = copyAndModify(aObj);
        // System.out.println("aObj: " + aObj + ", bObj: " + bObj + ", Equal: " + aObj.equals(bObj));
    }
}