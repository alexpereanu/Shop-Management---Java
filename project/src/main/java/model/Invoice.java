package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import dao.clientsDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class Invoice.
 */
public class Invoice {
	
	/**
	 * Creates the invoice.
	 *
	 * @param Id the id
	 * @param sumaDeAchitat the suma de achitat
	 */
	public static void createInvoice(int Id, int sumaDeAchitat) {
		String fileName = "Chitanta.txt";
		try {
			PrintWriter outputStream = new PrintWriter(fileName);
			outputStream.println("CHITANTA");
			clientsDAO client = new clientsDAO();
			String nume = client.findName(Id);
			outputStream.println("Domnul/-a " + nume + " a cumparat produse in valoare de " + sumaDeAchitat + " lei");
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
