package udpReciever;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Main {

	public static final int MC_PORT = 1600;
	public static final String MC_IP_STR = "224.168.2.9";

	
	public static void main(String[] args) throws Exception {
		MulticastSocket mcSocket = null;
		InetAddress mcIPAddress = null;
		
		mcIPAddress = InetAddress.getByName(MC_IP_STR);
		mcSocket = new MulticastSocket(MC_PORT);
		
		System.out.println("Multicast Receiver running at:"
				+ mcSocket.getLocalSocketAddress());
		
		mcSocket.joinGroup(mcIPAddress);

		DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

		System.out.println("Waiting for a  multicast message...");
		
		mcSocket.receive(packet);
		String msg = new String(packet.getData(), packet.getOffset(),
				packet.getLength());
		
		System.out.printf("[Multicast  Receiver] Received: \"%s\"\n",msg);

		mcSocket.leaveGroup(mcIPAddress);
		mcSocket.close();
	}
}