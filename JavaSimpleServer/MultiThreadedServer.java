import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 */

/**
 * @author shashank
 *
 */
public class MultiThreadedServer implements Runnable {
	Socket client;
	private static final int PORT = 8080;
	private static BufferedReader br;
	private static String line;
	
	public MultiThreadedServer(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		System.out.println("New thread craeted");
		try {
			br = new BufferedReader(
					new InputStreamReader(
							client.getInputStream()
							)
					);
			// Get the client message
			while((line = br.readLine()) != null) {
				System.out.println(line);
				// We r closing the client on receiving "stop". So thn close this thread as well
				if(line.equals("stop") == true) return;
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Listening on port " + PORT);
			
			// Create a new thread everytime a new client connects
			while(true) {
				Socket socket = server.accept();
				System.out.println("Conencted " + socket);
				new Thread(new MultiThreadedServer(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
