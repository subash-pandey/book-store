import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SimpleHashFunction {

    public static String hash(String input) {
        try {
            // Use SHA-256 for the underlying hashing (one-way property)
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());

            // Convert byte array to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String input = "This is a test string.";
        String hashValue = hash(input);
        System.out.println("Input: " + input);
        System.out.println("Hash: " + hashValue);
    }
}