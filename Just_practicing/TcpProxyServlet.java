import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TcpProxyServlet extends HttpServlet {

    private final Random random = new Random();

    @Override
    public void init() throws ServletException {
        super.init();
        // Start a thread to listen for incoming connections on port 8080
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(8080)) {
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    // Handle the connection in a new thread
                    new Thread(() -> handleConnection(clientSocket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void handleConnection(Socket clientSocket) {
        try {
            // Generate a random port above 9000
            int targetPort = random.nextInt(65535 - 9000) + 9000;

            // Connect to the target port
            Socket targetSocket = new Socket("localhost", targetPort);

            // Create streams for bidirectional communication
            Thread clientToTargetThread = new Thread(() -> {
                try {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = clientSocket.getInputStream().read(buffer)) != -1) {
                        targetSocket.getOutputStream().write(buffer, 0, bytesRead);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread targetToClientThread = new Thread(() -> {
                try {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = targetSocket.getInputStream().read(buffer)) != -1) {
                        clientSocket.getOutputStream().write(buffer, 0, bytesRead);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Start the threads
            clientToTargetThread.start();
            targetToClientThread.start();

            // Wait for the threads to complete
            clientToTargetThread.join();
            targetToClientThread.join();

            // Close the sockets
            clientSocket.close();
            targetSocket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("TCP Proxy Servlet is running.");
    }
}