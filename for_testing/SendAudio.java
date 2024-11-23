import javax.sound.midi.Receiver;
import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendAudio {

    public static void main(String[] args) {
        // Network settings
        String serverAddress = "192.168.1.10"; // Replace with the receiver's IP address
        int serverPort = 5000;

        // Audio format settings
        AudioFormat format = new AudioFormat(44100, 16, 2, true, true); // 44.1 kHz, 16 bit, stereo, signed, big-endian

        try {
            // Get the default microphone
            TargetDataLine line = AudioSystem.getTargetDataLine(format);
            line.open(format);

            // Establish network connection
            Socket socket = new Socket(InetAddress.getByName(serverAddress), serverPort);
            OutputStream out = socket.getOutputStream();

            // Start capturing and sending audio
            line.start();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = line.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            // Clean up resources
            line.stop();
            line.close();
            socket.close();
            System.out.println("Audio transmission finished.");

        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
