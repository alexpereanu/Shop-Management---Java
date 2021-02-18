package presentation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.CommandDAO;
import dao.ProduseDAO;
import model.Comanda;
import model.Invoice;
import model.Produse;

// TODO: Auto-generated Javadoc
/**
 * The Class CommandInterface.
 */
public class CommandInterface {

	/** The Constant EXIT_ON_CLOSE. */
	private static final int EXIT_ON_CLOSE = 1;
	
	/** The frame. */
	static JFrame frame = new JFrame();
	
	/** The panel. */
	JPanel panel = new JPanel();
	
	/**
	 * Edits the command.
	 */
	public void editCommand() {
		final JFrame clientFrame = new JFrame("Comenzi");
		JPanel panelClient = new JPanel();
		clientFrame.setSize(500, 400);
		panelClient.setLayout(null);
		clientFrame.add(panelClient);
		clientFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		clientFrame.setVisible(true);
		frame.setVisible(false);

		JButton backButton = new JButton("BACK");
		panelClient.add(backButton);
		backButton.setBounds(375, 275, 70, 50);
		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				clientFrame.setVisible(false);
			}
		});
		
		JLabel label0 = new JLabel("Faceti o COMANDA");
		panelClient.add(label0);
		label0.setBounds(110, 20, 300, 20);
		label0.setFont(new Font("arial",Font.CENTER_BASELINE,25));
		
		JLabel label = new JLabel("ID client:");
		panelClient.add(label);
		label.setBounds(10, 80, 60, 30);
		label.setFont(new Font("arial",Font.CENTER_BASELINE,13));
		
		final JTextField text = new JTextField();
		panelClient.add(text);
		text.setBounds(70, 87, 30, 20);
		
		JLabel label1 = new JLabel("ID produs:");
		panelClient.add(label1);
		label1.setBounds(10, 160, 100, 30);
		label1.setFont(new Font("arial",Font.CENTER_BASELINE,13));
		
		final JTextField text2 = new JTextField();
		panelClient.add(text2);
		text2.setBounds(79, 167, 30, 20);
		
		JLabel label2 = new JLabel("Cantitate:");
		panelClient.add(label2);
		label2.setBounds(10, 240, 100, 30);
		label2.setFont(new Font("arial",Font.CENTER_BASELINE,13));
		
		final JTextField text3 = new JTextField();
		panelClient.add(text3);
		text3.setBounds(77, 245, 30, 20);
		
		JButton plaseaza = new JButton("PLASEAZA COMANDA");
		panelClient.add(plaseaza);
		plaseaza.setBounds(160, 275, 200, 50);
		
		plaseaza.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int idClient,idProdus,cantitate,pret;
				idClient = Integer.parseInt(text.getText());
				idProdus = Integer.parseInt(text2.getText());
				cantitate = Integer.parseInt(text3.getText());
				ProduseDAO produs = new ProduseDAO();
				int pretFinal = produs.pretProdus(idProdus) * cantitate;
				int cantitateBazaDeDate = produs.getCantitate(idProdus);
				if(cantitate > cantitateBazaDeDate) {
					JOptionPane.showMessageDialog(null, "Nu avem atatea produse in stoc !");
					return;
				}
				CommandDAO comanda = new CommandDAO();
				comanda.insertCommand(new Comanda(idClient,idProdus,cantitate,pretFinal));
				produs.setCantitate(idProdus, cantitateBazaDeDate - cantitate); 
				Invoice chitanta = new Invoice();
				chitanta.createInvoice(idClient, pretFinal);
				
					
			}
		});
		
		
	}

}
