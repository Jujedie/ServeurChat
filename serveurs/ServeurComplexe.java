package serveurs;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ServeurComplexe {
	public ServeurComplexe(int port){
		try {
			HashMap<String,PrintWriter> lstClients = new HashMap<>();
			ArrayList<String[]> lstMessages = new ArrayList<>();

			Thread tgdc = new Thread(new GerantDeChat(lstClients, lstMessages));
			tgdc.start();
			
			ServerSocket ss = new ServerSocket(port);
			while (true) { // on boucle
				// attendre patiemment un client
				Socket s = ss.accept();
				// créer un GerantDeClient pour traiter ce nouveau client
				GerantDeClientChat gdcc = new GerantDeClientChat(s,lstClients,lstMessages);
				// mettre ce gérant de client dans une Thread
				Thread tgdcc = new Thread(gdcc);
				// lancer la thread qui gérera ce client
				tgdcc.start();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java ServeurComplexe <port number>");
			return;
		}
		new ServeurComplexe(Integer.parseInt(args[0]));
	}
}
