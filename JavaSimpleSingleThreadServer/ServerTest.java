import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

	/**
	 * This class creates a basic single threaded server
	 * Since its single threaded it wil lconnect to only one client at a time
	 */
	
	private static ServerSocket serverSocket;
	private static Socket clientSocket; 
	private static BufferedReader br;
	private static String line;
	
	public static void main(String[] args) {
		try {
			serverSocket = new ServerSocket(8080);
			clientSocket = serverSocket.accept();
			
			br = new BufferedReader(
					new InputStreamReader(
							clientSocket.getInputStream()
							)
					);
			
			// Get the client message
			while((line = br.readLine()) != null)
				System.out.println(line);

			//The following lines send a response back to teh browser. This for example send stodays date
			/*
			System.out.println("Return response");
			Date today = new Date();
			String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
			clientSocket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
			*/
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
