import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TcpProxyServlet extends HttpServlet {

    private static final int MIN_TARGET_PORT = 9001;
    private static final int MAX_TARGET_PORT = 65535;
    private static final Random RANDOM = new Random();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                int targetPort = getRandomPort();
                Socket targetSocket = new Socket("localhost", targetPort);
                System.out.println("Connecting to target port: " + targetPort);

                Thread forwardThread = new ForwardingThread(clientSocket.getInputStream(), targetSocket.getOutputStream());
                Thread backwardThread = new ForwardingThread(targetSocket.getInputStream(), clientSocket.getOutputStream());

                forwardThread.start();
                backwardThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getRandomPort() {
        return RANDOM.nextInt(MAX_TARGET_PORT - MIN_TARGET_PORT + 1) + MIN_TARGET_PORT;
    }

    private static class ForwardingThread extends Thread {

        private final InputStream inputStream;
        private final OutputStream outputStream;

        public ForwardingThread(InputStream inputStream, OutputStream outputStream) {
            this.inputStream = inputStream;
            this.outputStream = outputStream;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int bytesRead;

            try {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
