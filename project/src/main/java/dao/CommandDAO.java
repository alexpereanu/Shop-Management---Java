package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Comanda;

// TODO: Auto-generated Javadoc
/**
 * The Class CommandDAO.
 */
public class CommandDAO {

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = Logger.getLogger(clientsDAO.class.getName());
	
	/** The Constant insertStatementS. */
	private static final String insertStatementS = "INSERT INTO Comanda (id_client, id_produs, cantitate, suma)"
			+ " VALUES (?,?,?,?)";

	/**
	 * Insert command.
	 *
	 * @param comanda the comanda
	 */
	public void insertCommand(Comanda comanda) {

		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		int x = 0;

		try {
			insertStatement = dbConnect.prepareStatement(insertStatementS);
			insertStatement.setInt(1, comanda.getId_client());
			insertStatement.setInt(2, comanda.getId_produs());
			insertStatement.setInt(3, comanda.getCantitate());
			insertStatement.setInt(4, comanda.getSuma());
			insertStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ERROR INSERT PRODUCTS" + e.getMessage());
			x = 1;
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnect);
			if (x == 0) {
				JOptionPane.showMessageDialog(null, "Comanda dvs. a fost inregistrata ! ");
			} else
				JOptionPane.showMessageDialog(null, "FAIL");
		}
	}

}
