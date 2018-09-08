package udpSender;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {
	// Choose a reserved multicast ip address to broadcast to
	public static final String MC_IP_STR = "224.168.2.9";
	public static final int MC_PORT = 1600;
	
	// This is the data that will be sent, need to change to use Google Protocol Buffer
	public static String message = "Sender test...";
	
	public static void main(String[] args) throws Exception {

		DatagramSocket udpSocket = new DatagramSocket();

		InetAddress mcIPAddress = InetAddress.getByName(MC_IP_STR);
		
		byte[] msg = message.getBytes();
		DatagramPacket packet = new DatagramPacket(msg, msg.length);
		
		packet.setAddress(mcIPAddress);
		packet.setPort(MC_PORT);
		udpSocket.send(packet);

		System.out.println("Sent a multicast message.");
		System.out.println("Exiting application");
		udpSocket.close();
	}
}