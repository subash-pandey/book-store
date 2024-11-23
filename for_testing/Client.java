import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        // Client setup
        Socket socket = new Socket("localhost", 8080);
        System.out.println("Connected to server.");

        // Game logic
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            Scanner scanner = new Scanner(System.in);

            while (true) {
                String serverMessage = in.readLine();
                if (serverMessage == null) break;
                System.out.println(serverMessage);

                if (serverMessage.startsWith("You guessed it!")) {
                    break;
                }

                System.out.print("Enter your guess: ");
                String guess = scanner.nextLine();
                out.println(guess);
            }
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
        } finally {
            socket.close();
            System.out.println("Disconnected from server.");
        }
    }
}