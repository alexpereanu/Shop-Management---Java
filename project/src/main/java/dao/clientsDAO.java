package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.clients;

// TODO: Auto-generated Javadoc
/**
 * The Class clientsDAO.
 */
public class clientsDAO {

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = Logger.getLogger(clientsDAO.class.getName());
	
	/** The Constant updateStatementS. */
	private static final String updateStatementS = "UPDATE clients SET Nume = ?, Oras = ? WHERE ID = ?";
	
	/** The Constant deleteStatementS. */
	private static final String deleteStatementS = "DELETE FROM clients WHERE ID = ?";
	
	/** The Constant insertStatementS. */
	private static final String insertStatementS = "INSERT INTO clients (ID,Nume,Oras)" + " VALUES(?,?,?)";
	
	/** The Constant findStatementS. */
	private final static String findStatementS = "SELECT * FROM clients WHERE ID = ?";
	 private final static String aStatement = "SELECT * FROM clients";

	/**
	 * Insert into.
	 *
	 * @param client the client
	 */
	public void insertInto(clients client) {
		int x = 0;
		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;

		try {
			insertStatement = dbConnect.prepareStatement(insertStatementS);
			insertStatement.setInt(1, client.getID());
			insertStatement.setString(2, client.getNume());
			insertStatement.setString(3, client.getOras());
			insertStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ERROR INSERT CLIENTS " + e.getMessage());
			x = 1;
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnect);
			if (x == 0) {
				JOptionPane.showMessageDialog(null, "SUCCES");
			} else
				JOptionPane.showMessageDialog(null, "FAIL");
		}
	}

	/**
	 * Update clients.
	 *
	 * @param idToUpdate the id to update
	 * @param clientToUpdate the client to update
	 */
	public void updateClients(int idToUpdate, clients clientToUpdate) {
		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		int x = 0;
		int adevarat = findById(idToUpdate);
		if (adevarat == 0) {
			JOptionPane.showMessageDialog(null, "ID-ul " + idToUpdate + " nu se gaseste in baza de date ! ");
			return;
		}

		try {
			updateStatement = dbConnect.prepareStatement(updateStatementS);
			updateStatement.setString(1, clientToUpdate.getNume());
			updateStatement.setString(2, clientToUpdate.getOras());
			updateStatement.setInt(3, idToUpdate);
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ERROR UPDATE CLIENTS " + e.getMessage());
			x = 1;
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnect);
			if (x == 0) {
				JOptionPane.showMessageDialog(null, "SUCCES");
			} else
				JOptionPane.showMessageDialog(null, "FAIL");
		}
	}

	/**
	 * Delete client with id.
	 *
	 * @param id the id
	 */
	public void deleteClientWithId(int id) {
		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		int x = 0;
		int adevarat = findById(id);
		if (adevarat == 0) {
			JOptionPane.showMessageDialog(null, "ID-ul " + id + " nu se gaseste in baza de date ! ");
			return;
		}

		try {
			deleteStatement = dbConnect.prepareStatement(deleteStatementS);
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ERROR DELETE CLIENT WITH ID " + e.getMessage());
			x = 1;
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnect);
			if (x == 0) {
				JOptionPane.showMessageDialog(null, "SUCCES");
			} else
				JOptionPane.showMessageDialog(null, "FAIL");
		}
	}


	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the int
	 */
	public int findById(int id) {
		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement findById = null;
		ResultSet rs = null;
		clients returnClient = null;
		try {
			findById = dbConnect.prepareStatement(findStatementS);
			findById.setLong(1, id);
			rs = findById.executeQuery();
			rs.next();

			int idClient = rs.getInt("ID");
			String numeClient = rs.getString("Nume");
			String orasClient = rs.getString("Oras");
			returnClient = new clients(idClient, numeClient, orasClient);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Problema la cautare client " + e.getMessage());

		} finally {
			ConnectionFactory.close(findById);
			ConnectionFactory.close(dbConnect);
		}
		if (returnClient != null) {
			return 1;
		} else
			return 0;
	}
	
	/**
	 * Find name.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String findName(int id) {
		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement findById = null;
		ResultSet rs = null;
		String nume = null;
		try {
			findById = dbConnect.prepareStatement(findStatementS);
			findById.setLong(1, id);
			rs = findById.executeQuery();
			rs.next();

			int idClient = rs.getInt("ID");
			String numeClient = rs.getString("Nume");
			String orasClient = rs.getString("Oras");
			return numeClient;

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Problema la cautare nume client " + e.getMessage());

		} finally {
			ConnectionFactory.close(findById);
			ConnectionFactory.close(dbConnect);
		}
		return nume;
	}
	
	public static ArrayList<clients> getClient() throws Exception
	{
		try
		{
			Connection con= ConnectionFactory.getConnection();
			PreparedStatement statement= con.prepareStatement("SELECT * FROM clients");
		
			ResultSet result= statement.executeQuery();
			
			ArrayList<clients> clienti= new ArrayList<clients>();
		
			while(result.next())
			{
				int id=Integer.parseInt(result.getString("ID"));
				String nume= result.getString("Nume");
				String oras = result.getString("Oras");
				
				clients client= new clients(id,nume,oras);
				clienti.add(client);
				client.toString();
			}
			
			System.out.println("Toate bune !");
			return clienti;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return null;
	}

}