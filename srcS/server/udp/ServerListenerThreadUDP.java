

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;



public class ServerListenerThreadUDP extends Thread{
    private static DatagramSocket UDPsocket;
    private int port;
    private int MAX_PACKET_SIZE;
    
    private Deserialization NetTools = new Deserialization();
    
    ServerListenerThreadUDP(int port, int packetSize) {
    	this.port = port;
    	MAX_PACKET_SIZE = packetSize;
    }
    
    public void run() {
    	ServerUDPManager ServerQueue = new ServerUDPManager();
    	try {
			UDPsocket = new DatagramSocket(port);
			System.out.println("SERVER UDP LISTENER Activated.");
		} catch (SocketException e1) {
			System.err.println("Server UDP Listener unable to start: Choose another port.");
			e1.printStackTrace();
			return;
		}
    	
    	while(true) {
    		byte[] data = new byte[MAX_PACKET_SIZE];
    		DatagramPacket receivePacket = new DatagramPacket(data, data.length);
    		try {
				UDPsocket.receive(receivePacket);
				System.out.println("Received Packet");
				
				//-------------Receiving Objects---------
				byte[] gameByte = receivePacket.getData();
				System.out.println("Received game bytes.");
				udpMessage gameMessage = (udpMessage) NetTools.deserialize(gameByte);
				ServerQueue.addToQueueMessages((udpMessage) gameMessage);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }
    
    
}
