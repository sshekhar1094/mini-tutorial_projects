import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientTest {

	/**
	 * Class to conenct to a singl threaded server running on localhost at 8080
	 */
	
	private static Socket socket;
	private static PrintWriter pr;
	
	public static void main(String[] args) {
		String line = "";
		try {
			socket = new Socket("localhost", 8080);
			pr = new PrintWriter(socket.getOutputStream(), true);
			pr.println("Hello Socket");
			pr.println("Booooooooyyeeeaaaahhhh");
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter stop to exit!!!");
			while(line.equals("stop") == false) {
				line = scan.nextLine();
				pr.println(line);
			}
			scan.close();
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
