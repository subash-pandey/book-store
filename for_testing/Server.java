import java.io.*;
import java.net.*;
import java.util.Random;

public class Server {

    public static void main(String[] args) throws IOException {
        // Server setup
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server started on port 8080...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

            // Game logic
            try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                Random rand = new Random();
                int secretNumber = rand.nextInt(100) + 1; // Number between 1 and 100
                int attempts = 0;

                out.println("Welcome to the Guess the Number game! I'm thinking of a number between 1 and 100.");

                while (true) {
                    String clientInput = in.readLine();
                    if (clientInput == null) break;

                    attempts++;
                    try {
                        int guess = Integer.parseInt(clientInput);
                        if (guess < secretNumber) {
                            out.println("Too low. Try again.");
                        } else if (guess > secretNumber) {
                            out.println("Too high. Try again.");
                        } else {
                            out.println("You guessed it! The number was " + secretNumber + " and it took you " + attempts + " attempts.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        out.println("Invalid input. Please enter a number.");
                    }
                }
            } catch (IOException e) {
                System.err.println("Error handling client connection: " + e.getMessage());
            } finally {
                socket.close();
                System.out.println("Client disconnected.");
            }
        }
    }
}