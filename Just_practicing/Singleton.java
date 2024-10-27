public class Singleton {

    private static Singleton instance;

    private Singleton() {} // Private constructor prevents external instantiation

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton instance!");
    }
}

