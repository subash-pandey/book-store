public class Main {
    public static void main(String[] args) {
        Singleton object = Singleton.getInstance();
        object.showMessage(); // Output: Hello from Singleton instance!
    }
}
