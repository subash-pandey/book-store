import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptographicHash {

    public static String hash(String input) {
        try {
            // 1. Choose a strong hashing algorithm (SHA-256 in this case)
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 2. Convert the input string to bytes
            byte[] inputBytes = input.getBytes();

            // 3. Compute the hash value
            byte[] hashBytes = digest.digest(inputBytes);

            // 4. Convert the byte array to a hexadecimal string representation
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            // Handle the exception if the algorithm is not available
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String input = "This is a subash.";
        String hashValue = hash(input);
        System.out.println("Input: " + input);
        System.out.println("Hash: " + hashValue);
    }
}
