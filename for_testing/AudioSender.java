import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class AudioSender {

    private static final int PORT = 50005;
    private static final String HOST = "127.0.0.1"; // Replace with the receiver's IP address

    public static void main(String[] args) {

        try {
            // Get the default audio input mixer and target line info
            AudioFormat format = new AudioFormat(44100, 16, 2, true, false); // 44.1kHz, 16-bit, stereo
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            // Create a DatagramSocket to send data over UDP
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(HOST);

            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while (true) {
                int bytesRead = line.read(buffer, 0, buffer.length);
                baos.write(buffer, 0, bytesRead);

                // Encode the audio data (Optional: Use an encoder for FLAC or ALAC here)
                byte[] encodedData = baos.toByteArray();
                baos.reset();

                // Send the data packet
                DatagramPacket packet = new DatagramPacket(encodedData, encodedData.length, address, PORT);
                socket.send(packet);
            }

        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
