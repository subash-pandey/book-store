import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class AudioReceiver {

    private static final int PORT = 50005;

    public static void main(String[] args) {

        try {
            // Create a DatagramSocket to receive the data
            DatagramSocket socket = new DatagramSocket(PORT);

            byte[] buffer = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Get the default audio output mixer and source line info
            AudioFormat format = new AudioFormat(44100, 16, 2, true, false); // 44.1kHz, 16-bit, stereo
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            while (true) {
                socket.receive(packet);
                byte[] receivedData = packet.getData();

                // Decode the received audio data (Optional: Use a decoder for FLAC or ALAC here)
                ByteArrayInputStream bais = new ByteArrayInputStream(receivedData);

                // Save audio to file (Implementation for saving is not included here)
                // You can utilize a library like JLayer for MP3 encoding or a suitable FLAC library
                saveAudioToFile(bais, format);

                // Play the sound directly (for testing)
                line.write(receivedData, 0, receivedData.length);
            }

        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveAudioToFile(ByteArrayInputStream bais, AudioFormat format) {
        // Implement logic to save the audio stream to a file (e.g., using JLayer for MP3 or a FLAC library)
        System.out.println("Saving audio data..."); // Placeholder
    }
}