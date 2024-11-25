package clients;
import java.io.*;
import java.net.Socket;

public class ClientComplexe
{
	public static void main(String[] a)
	{
		Socket toServer;
		String hostName;

		try {
			System.out.print("Enter the server name: ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			hostName = reader.readLine();
		}catch (Exception e) {hostName = "localhost";}
		
		try{		
			int    portNum  = 6000;

			System.out.println("Connexion au serveur");

			toServer = new Socket(hostName, portNum);
			PrintWriter out = new PrintWriter(toServer.getOutputStream(), true); // pour écrire au serveur
			BufferedReader in = new BufferedReader(new InputStreamReader(toServer.getInputStream())); // pour lire à partir du serveur
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); // pour lire à partir du clavier

			while (true) {
				if (in.ready()) {
					System.out.println(in.readLine());
				}
				if (stdIn.ready()) {
					out.println(stdIn.readLine());
				}
			}
		}
		catch (Exception e) {e.printStackTrace();}
	}
}