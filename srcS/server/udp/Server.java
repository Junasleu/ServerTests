

public class Server {

	private static ServerUDPManager UDPManager;
	
	public Server() {
		UDPManager = new ServerUDPManager();
	}
	
	public static void main(String[] args) {
		
		Server server = new Server();
		
		System.out.println("Starting UDP Manager.");
		UDPManager.run();
		

	}

}
