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
import dao.ProduseDAO;
import dao.clientsDAO;
import model.Produse;
import model.clients;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductInterface.
 */
public class ProductInterface extends JFrame {

	/** The frame. */
	static JFrame frame = new JFrame();
	
	/** The panel. */
	JPanel panel = new JPanel();

	/**
	 * Edits the product.
	 */
	public void editProduct() {

		final JFrame clientFrame = new JFrame("Produse");
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
				//frame.setVisible(true);
			}
		});

		JButton viewProducts = new JButton("Vizualizare produse");
		panelClient.add(viewProducts);
		viewProducts.setBounds(50, 50, 150, 50);

		JButton addProduct = new JButton("Adauga produs");
		addProduct.setBounds(280, 50, 150, 50);
		panelClient.add(addProduct);

		JButton updateProduct = new JButton("Update produs");
		panelClient.add(updateProduct);
		updateProduct.setBounds(50, 150, 150, 50);

		JButton deleteProduct = new JButton("Delete produs");
		panelClient.add(deleteProduct);
		deleteProduct.setBounds(280, 150, 150, 50);

		addProduct.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				clientFrame.setVisible(false);
				final JFrame vClients = new JFrame("Adaugare produs");
				JPanel vPanel = new JPanel();
				vPanel.setLayout(null);
				vClients.add(vPanel);
				vClients.setSize(500, 400);
				vClients.setDefaultCloseOperation(EXIT_ON_CLOSE);
				vClients.setVisible(true);

				JLabel label = new JLabel("Id produs:");
				label.setBounds(10, 20, 75, 30);
				vPanel.add(label);
				label.setFont(new Font("Courier", Font.BOLD, 15));

				final JTextField text = new JTextField();
				text.setBounds(85, 27, 25, 20);
				vPanel.add(text);

				JLabel label2 = new JLabel("Denumire:");
				label2.setBounds(10, 70, 110, 30);
				vPanel.add(label2);
				label2.setFont(new Font("Courier", Font.BOLD, 15));

				final JTextField text2 = new JTextField();
				text2.setBounds(90, 75, 200, 25);
				vPanel.add(text2);

				JLabel label3 = new JLabel("Cantitate:");
				vPanel.add(label3);
				label3.setFont(new Font("Courier", Font.BOLD, 15));
				label3.setBounds(10, 120, 100, 30);

				final JTextField text3 = new JTextField();
				vPanel.add(text3);
				text3.setBounds(80, 126, 25, 20);

				JLabel label4 = new JLabel("Pret:");
				vPanel.add(label4);
				label4.setFont(new Font("Courier", Font.BOLD, 15));
				label4.setBounds(10, 170, 100, 30);

				final JTextField text4 = new JTextField();
				vPanel.add(text4);
				text4.setBounds(46, 176, 25, 20);

				JButton OK = new JButton("Submit");
				vPanel.add(OK);
				OK.setBounds(200, 220, 75, 30);

				OK.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						String den;
						int id, pret, cantitate;

						den = text2.getText();
						id = Integer.parseInt(text.getText());
						pret = Integer.parseInt(text3.getText());
						cantitate = Integer.parseInt(text4.getText());
						ProduseDAO produs = new ProduseDAO();
						produs.insertProduct(new Produse(id, den, pret, cantitate));

					}
				});

				JButton backButton = new JButton("BACK");
				vPanel.add(backButton);
				backButton.setBounds(375, 275, 70, 50);
				backButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						vClients.setVisible(false);
						clientFrame.setVisible(true);
					}
				});
			}
		});

		viewProducts.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				clientFrame.setVisible(false);
				final JFrame vClients = new JFrame("Vizualizare produse");
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

				Connection dbConnect = ConnectionFactory.getConnection();
				PreparedStatement afisare = null;
				ResultSet rs = null;
				clients c = null;
				String s = "SELECT * FROM Produse";
				ArrayList<Produse> clienti = new ArrayList<Produse>();
				try {
					clienti = ProduseDAO.getProduse();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				DefaultTableModel tableModel = new DefaultTableModel(null, ReflectionTech.retrieveHeader(new Produse()));
				JTable table = new JTable(tableModel);
				table.setBounds(5, 5, 495, 300);
				
				JScrollPane pane = new JScrollPane(table);
				vClients.setContentPane(pane);
				
				Vector<Object> data;
				for (Produse produs: clienti) {
					data = ReflectionTech.retrieveProperties(produs);
					tableModel.addRow(data);
				}
				
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

		updateProduct.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				clientFrame.setVisible(false);
				final JFrame vClients = new JFrame("Update product");
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
				text.setBounds(35, 25, 30, 20);
				vPanel.add(text);

				JLabel label2 = new JLabel("Denumire:");
				label2.setBounds(5, 60, 90, 30);
				vPanel.add(label2);
				label2.setFont(new Font("Courier", Font.BOLD, 15));

				final JTextField text2 = new JTextField();
				text2.setBounds(85, 62, 200, 25);
				vPanel.add(text2);

				JLabel label3 = new JLabel("Cantitate:");
				vPanel.add(label3);
				label3.setFont(new Font("Courier", Font.BOLD, 15));
				label3.setBounds(5, 100, 100, 30);

				final JTextField text3 = new JTextField();
				vPanel.add(text3);
				text3.setBounds(77, 105, 30, 23);
				
				JLabel label4 = new JLabel("Pret:");
				vPanel.add(label4);
				label4.setFont(new Font("Courier", Font.BOLD, 15));
				label4.setBounds(5, 140, 70, 30);
				
				final JTextField text4 = new JTextField();
				vPanel.add(text4);
				text4.setBounds(45, 147, 30, 23);
				

				JButton OK = new JButton("Submit");
				vPanel.add(OK);
				OK.setBounds(170, 230, 75, 30);

				OK.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						String denumire;
						int id,pret,cantitate;
						
						denumire = text2.getText();
						id = Integer.parseInt(text.getText());
						cantitate = Integer.parseInt(text3.getText());
						pret = Integer.parseInt(text4.getText());

						ProduseDAO produse = new ProduseDAO();
						produse.updateProducts(id, new Produse(id,denumire,cantitate,pret));

					}
				});

				JButton backButton = new JButton("BACK");
				vPanel.add(backButton);
				backButton.setBounds(375, 275, 70, 50);
				backButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						vClients.setVisible(false);
						clientFrame.setVisible(true);
					}
				});

			}

		});
		
		deleteProduct.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				clientFrame.setVisible(false);
				final JFrame vClients = new JFrame("Stergere produs");
				JPanel vPanel = new JPanel();
				vPanel.setLayout(null);
				vClients.add(vPanel);
				vClients.setSize(500, 400);
				vClients.setDefaultCloseOperation(EXIT_ON_CLOSE);
				vClients.setVisible(true);

				JLabel label = new JLabel("ID-ul produsului ce va fi sters:");
				label.setBounds(40, 40, 250, 30);
				vPanel.add(label);
				label.setFont(new Font("Courier", Font.BOLD, 15));

				final JTextField text = new JTextField();
				text.setBounds(255, 45, 30, 20);
				vPanel.add(text);

				JButton OK = new JButton("Submit");
				vPanel.add(OK);
				OK.setBounds(200, 200, 75, 30);

				OK.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						int id;
						id = Integer.parseInt(text.getText());
						ProduseDAO produs = new ProduseDAO();
						produs.deleteProductWithId(id);
					}
				});

				JButton backButton = new JButton("BACK");
				vPanel.add(backButton);
				backButton.setBounds(375, 275, 70, 50);
				backButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						vClients.setVisible(false);
						clientFrame.setVisible(true);
					}
				});

			}

		});
	}

}
