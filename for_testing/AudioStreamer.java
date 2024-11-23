
import javax.sound.sampled.*;
import java.io.*;
import java.net.*;

public class AudioStreamer {

    private static final int PORT = 50005; // Choose a suitable port
    private static final AudioFormat AUDIO_FORMAT = new AudioFormat(44100f, 16, 2, true, false); // High-quality format

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java AudioStreamer [send|receive]");
            return;
        }

        if (args[0].equals("send")) {
            sendAudio();
        } else if (args[0].equals("receive")) {
            receiveAudio();
        } else {
            System.err.println("Invalid argument. Use 'send' or 'receive'.");
        }
    }

    private static void sendAudio() {
        try {
            // Capture audio from the microphone
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(new DataLine.Info(TargetDataLine.class, AUDIO_FORMAT));
            line.open(AUDIO_FORMAT);
            line.start();

            // Create a socket and connect to the receiver
            Socket socket = new Socket("receiver_ip_address", PORT); // Replace with the receiver's IP address
            OutputStream outputStream = socket.getOutputStream();

            // Continuously send audio data
            byte[] buffer = new byte[1024];
            int bytesRead;
            while (true) {
                bytesRead = line.read(buffer, 0, buffer.length);
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void receiveAudio() {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Waiting for connection...");

            // Accept connection from the sender
            Socket socket = serverSocket.accept();
            System.out.println("Connected to sender.");

            // Create an audio input stream from the socket
            InputStream inputStream = socket.getInputStream();
            AudioInputStream audioInputStream = new AudioInputStream(inputStream, AUDIO_FORMAT, AudioSystem.NOT_SPECIFIED);

            // Save the audio to a file in WAV format
            File outputFile = new File("received_audio.wav");
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, outputFile);
            System.out.println("Audio saved to " + outputFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}