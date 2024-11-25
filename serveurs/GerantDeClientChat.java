package serveurs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class GerantDeClientChat implements Runnable {

	private Socket socket;
	private HashMap<String, PrintWriter> lstClients;
	private ArrayList<String[]> lstMessages;
	private PrintWriter out;
	private BufferedReader in;

	public GerantDeClientChat(Socket socket, HashMap<String,PrintWriter> lstClients, ArrayList<String[]> lstMessages) {
		try {
			this.socket     = socket;
			this.lstClients = lstClients;
			this.lstMessages= lstMessages;

			this.out = new PrintWriter(this.socket.getOutputStream(), true); // pour écrire au client
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream())); // pour lire à partir du client
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(true) { // boucle sans fin
			
			System.out.println("Un nouveau client est connecté");
			try{ 
				out.println("Server : Veuillez entrer votre pseudo : ");

				String pseudo = "";
				while (pseudo == null || pseudo.compareTo("")==0 ){
					pseudo = in.readLine();
				}
				out.println("Server : Bienvenue sur le chat "+pseudo+".");

				this.lstClients.put(pseudo, this.out);

				Boolean bOk=true;

				String message;
				String messagePrec ="";
				while( bOk){
					if (in.ready()) {
						message = in.readLine();
						if (message.compareTo(messagePrec)!=0){
							this.lstMessages.add(new String[]{pseudo,message});
							messagePrec = message;
						}
					}
					bOk = !out.checkError();// si le client est déconnecté, checkError retourne true
				}

				System.out.println("Le client "+pseudo+" s'est déconnecté");
				this.lstClients.remove(pseudo);

				this.socket.close();
				
				return;
			} catch (IOException ie){
				ie.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}