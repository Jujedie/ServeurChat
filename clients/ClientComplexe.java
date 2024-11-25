package clients;
import java.io.*;
import java.net.Socket;

public class ClientComplexe
{
	public static void main(String[] args)
	{
		if (args.length != 2) {
			System.err.println("Usage: java ClientComplexe <host name> <port number>");
			return;
		}
		Socket toServer;
		String hostName = args[0];
		int    portNum  = Integer.parseInt(args[1]);
		
		try{		
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