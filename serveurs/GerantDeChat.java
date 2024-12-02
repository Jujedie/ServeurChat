package serveurs;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GerantDeChat implements Runnable {

	private HashMap<String, PrintWriter> lstClients;
	private ArrayList<String[]> lstMessages;

	public GerantDeChat(HashMap<String, PrintWriter> lstClients, ArrayList<String[]> lstMessages) {
		this.lstClients = lstClients;
		this.lstMessages = lstMessages;
	}

	public void run() {
		int nbElt = 0;
		while (true) {
			System.out.print("");// Sans cette ligne ,le programme ne MARCHE pas je ne sais pas pourquoi, mais
									// elle doit rester.
			synchronized (lstMessages) {
				if (nbElt < this.lstMessages.size()) {
					for (int i = nbElt; i < this.lstMessages.size(); i++) {
						if (this.lstMessages.get(i)[1].split("-", 2)[0].length() < this.lstMessages.get(i)[1]
								.length()) {
							synchronized (lstClients) {
								this.lstClients.get(this.lstMessages.get(i)[1].split("-",2)[0]).println(
										this.lstMessages.get(i)[0] + " : " + this.lstMessages.get(i)[1].split("-",2)[1]);
							}
						} else {
							synchronized (lstClients) {
								for (String pKey : this.lstClients.keySet()) {
									if (this.lstMessages.get(i)[0].compareTo(pKey) != 0) {
										this.lstClients.get(pKey).println(
												this.lstMessages.get(i)[0] + " : " + this.lstMessages.get(i)[1]);
									}
								}
							}
						}
					}
					nbElt = this.lstMessages.size();
				}
			}
		}
	}
}
