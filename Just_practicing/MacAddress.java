import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class MacAddress {

    private static String getMacAddress() throws Exception {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface ni = networkInterfaces.nextElement();
            byte[] hardwareAddress = ni.getHardwareAddress();
            if (hardwareAddress != null) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < hardwareAddress.length; i++) {
                    sb.append(String.format("%02X%s", hardwareAddress[i], (i < hardwareAddress.length - 1) ? "-" : ""));
                }
                return sb.toString();
            }
        }
        throw new Exception("Could not obtain MAC address.");
    }
    public static void main(String[] args) throws Exception {
        System.out.println(getMacAddress());
    }
}