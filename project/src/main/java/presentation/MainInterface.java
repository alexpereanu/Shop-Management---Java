package presentation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.PreparedStatement;

import ReflectionTechnique.ReflectionTech;
import connection.ConnectionFactory;
import dao.AbstractDAO;
import dao.clientsDAO;
import model.clients;

// TODO: Auto-generated Javadoc
/**
 * The Class MainInterface.
 */
public class MainInterface extends JFrame {

	/** The main frame. */
	public static JFrame mainFrame;
	
	/** The panel. */
	private JPanel panel;

	/**
	 * Creates the main interface.
	 */
	public void createMainInterface() {
		panel = new JPanel();
		mainFrame = new JFrame("Magazin");
		panel.setLayout(null);
		mainFrame.setSize(500, 400);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton buton = new JButton("Optiuni clienti");
		buton.setFont(new Font("Courier", Font.BOLD, 12));
		buton.setBounds(50, 50, 150, 50);

		JButton buton2 = new JButton("Optiuni produse");
		buton2.setFont(new Font("Courier", Font.BOLD, 12));
		buton2.setBounds(280, 50, 150, 50);

		JButton buton3 = new JButton("Comandati");
		buton3.setFont(new Font("Courier", Font.BOLD, 12));
		buton3.setBounds(160, 200, 150, 50);

		panel.add(buton);
		panel.add(buton2);
		panel.add(buton3);

		mainFrame.add(panel);
		mainFrame.setVisible(true);

		buton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				mainFrame.setVisible(false);
				editClients();
			}
		});
		
		buton2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				ProductInterface prod = new ProductInterface();
				prod.editProduct();
			}
		});
		
		buton3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				CommandInterface comanda = new CommandInterface();
				comanda.editCommand();
			}
		});
	}
	
	

	/**
	 * Edits the clients.
	 */
	public static void editClients() {
		final JFrame clientFrame = new JFrame("Clienti");
		JPanel panelClient = new JPanel();
		clientFrame.setSize(500, 400);
		panelClient.setLayout(null);
		clientFrame.add(panelClient);
		clientFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		clientFrame.setVisible(true);
		mainFrame.setVisible(false);

		JButton backButton = new JButton("BACK");
		panelClient.add(backButton);
		backButton.setBounds(375, 275, 70, 50);
		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				clientFrame.setVisible(false);
				mainFrame.setVisible(true);
			}
		});

		JButton viewClients = new JButton("Vizualizare clienti");
		panelClient.add(viewClients);
		viewClients.setBounds(50, 50, 150, 50);

		JButton addClient = new JButton("Adauga client");
		addClient.setBounds(280, 50, 150, 50);
		panelClient.add(addClient);

		JButton updateClient = new JButton("Update client");
		panelClient.add(updateClient);
		updateClient.setBounds(50, 150, 150, 50);

		JButton deleteClient = new JButton("Delete client");
		panelClient.add(deleteClient);
		deleteClient.setBounds(280, 150, 150, 50);

		viewClients.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				clientFrame.setVisible(false);
				final JFrame vClients = new JFrame("Vizualizare clienti");
				JPanel vPanel = new JPanel();
				vPanel.setLayout(null);
				vClients.add(vPanel);
				vClients.setSize(500, 400);
				vClients.setDefaultCloseOperation(EXIT_ON_CLOSE);
				vClients.setVisible(true);

				/*
				 * JButton back = new JButton("BACK"); vPanel.add(back); back.setBounds(100,
				 * 100, 70, 50);
				 */

				/*Connection dbConnect = ConnectionFactory.getConnection();
				PreparedStatement afisare = null;
				ResultSet rs = null;
				clients c = null;
				String s = "SELECT * FROM clients";*/
				ArrayList<clients> clienti = new ArrayList<clients>();
				try {
					clienti = clientsDAO.getClient();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				DefaultTableModel tableModel = new DefaultTableModel(null, ReflectionTech.retrieveHeader(new clients()));
				JTable table = new JTable(tableModel);
				table.setBounds(5, 5, 495, 300);
				
				
				
				JScrollPane pane = new JScrollPane(table);
				vClients.setContentPane(pane);
				
				Vector<Object> data;
				for (clients client: clienti) {
					data = ReflectionTech.retrieveProperties(client);
					tableModel.addRow(data);
				}
				/*AbstractDAO alex = new AbstractDAO();
				JTable tabel2 = alex.createTable(clienti);
				tabel2.setBounds(5,5,495,300);*/
				
				JButton backBut = new JButton("BACK");
				vPanel.add(backBut);
				backBut.setBounds(200,320,70,30);
				backBut.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						vClients.setVisible(false);
						clientFrame.setVisible(true);
					}
				});

			}
		});

		addClient.addActionListener(new ActionListener() {// pentru adaugare client

			public void actionPerformed(ActionEvent arg0) {
				clientFrame.setVisible(false);
				JFrame vClients = new JFrame("Adaugare client");
				JPanel vPanel = new JPanel();
				vPanel.setLayout(null);
				vClients.add(vPanel);
				vClients.setSize(500, 400);
				vClients.setDefaultCloseOperation(EXIT_ON_CLOSE);
				vClients.setVisible(true);

				JLabel label = new JLabel("ID:");
				label.setBounds(10, 20, 75, 30);
				vPanel.add(label);
				label.setFont(new Font("Courier", Font.BOLD, 15));

				final JTextField text = new JTextField();
				text.setBounds(35, 35, 30, 20);
				vPanel.add(text);

				JLabel label2 = new JLabel("Nume:");
				label2.setBounds(80, 30, 50, 30);
				vPanel.add(label2);
				label2.setFont(new Font("Courier", Font.BOLD, 15));

				final JTextField text2 = new JTextField();
				text2.setBounds(135, 32, 200, 25);
				vPanel.add(text2);

				JLabel label3 = new JLabel("Oras:");
				vPanel.add(label3);
				label3.setFont(new Font("Courier", Font.BOLD, 15));
				label3.setBounds(20, 75, 100, 30);

				final JTextField text3 = new JTextField();
				vPanel.add(text3);
				text3.setBounds(75, 75, 100, 30);

				JButton OK = new JButton("Submit");
				vPanel.add(OK);
				OK.setBounds(200, 200, 75, 30);

				OK.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						String i, nume, oras;
						i = text.getText();
						int id = Integer.parseInt(i);
						nume = text2.getText();
						oras = text3.getText();

						clientsDAO client = new clientsDAO();
						client.insertInto(new clients(id, nume, oras));
					}
				});

				JButton backButton = new JButton("BACK");
				vPanel.add(backButton);
				backButton.setBounds(375, 275, 70, 50);
				backButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						clientFrame.setVisible(false);
						mainFrame.setVisible(true);
					}
				});
			}
		});

		updateClient.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				clientFrame.setVisible(false);
				JFrame vClients = new JFrame("Update client");
				JPanel vPanel = new JPanel();
				vPanel.setLayout(null);
				vClients.add(vPanel);
				vClients.setSize(500, 400);
				vClients.setDefaultCloseOperation(EXIT_ON_CLOSE);
				vClients.setVisible(true);

				JLabel label = new JLabel("ID:");
				label.setBounds(10, 20, 75, 30);
				vPanel.add(label);
				label.setFont(new Font("Courier", Font.BOLD, 15));

				final JTextField text = new JTextField();
				text.setBounds(35, 35, 30, 20);
				vPanel.add(text);

				JLabel label2 = new JLabel("Nume:");
				label2.setBounds(80, 30, 50, 30);
				vPanel.add(label2);
				label2.setFont(new Font("Courier", Font.BOLD, 15));

				final JTextField text2 = new JTextField();
				text2.setBounds(135, 32, 200, 25);
				vPanel.add(text2);

				JLabel label3 = new JLabel("Oras:");
				vPanel.add(label3);
				label3.setFont(new Font("Courier", Font.BOLD, 15));
				label3.setBounds(20, 75, 100, 30);

				final JTextField text3 = new JTextField();
				vPanel.add(text3);
				text3.setBounds(75, 75, 100, 30);

				JButton OK = new JButton("Submit");
				vPanel.add(OK);
				OK.setBounds(200, 200, 75, 30);

				OK.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						String i, nume, oras;
						i = text.getText();
						int id = Integer.parseInt(i);
						nume = text2.getText();
						oras = text3.getText();

						clientsDAO client = new clientsDAO();
						client.updateClients(id, new clients(id, nume, oras));

					}
				});

				JButton backButton = new JButton("BACK");
				vPanel.add(backButton);
				backButton.setBounds(375, 275, 70, 50);
				backButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						clientFrame.setVisible(false);
						mainFrame.setVisible(true);
					}
				});

			}

		});
		
		deleteClient.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				clientFrame.setVisible(false);
				JFrame vClients = new JFrame("Stergere client");
				JPanel vPanel = new JPanel();
				vPanel.setLayout(null);
				vClients.add(vPanel);
				vClients.setSize(500, 400);
				vClients.setDefaultCloseOperation(EXIT_ON_CLOSE);
				vClients.setVisible(true);

				JLabel label = new JLabel("ID-ul clientului ce va fi sters:");
				label.setBounds(40, 40, 250, 30);
				vPanel.add(label);
				label.setFont(new Font("Courier", Font.BOLD, 15));

				final JTextField text = new JTextField();
				text.setBounds(245, 45, 30, 20);
				vPanel.add(text);

				JButton OK = new JButton("Submit");
				vPanel.add(OK);
				OK.setBounds(200, 200, 75, 30);

				OK.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						String i;
						i = text.getText();
						int id = Integer.parseInt(i);
						clientsDAO client = new clientsDAO();
						client.deleteClientWithId(id);

					}
				});

				JButton backButton = new JButton("BACK");
				vPanel.add(backButton);
				backButton.setBounds(375, 275, 70, 50);
				backButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						clientFrame.setVisible(false);
						mainFrame.setVisible(true);
					}
				});

			}

		});
	}

}
