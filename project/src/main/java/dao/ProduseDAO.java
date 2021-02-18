

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
	import model.Produse;
	import model.clients;

	// TODO: Auto-generated Javadoc
	/**
	 * The Class ProduseDAO.
	 */
	public class ProduseDAO {

		/** The Constant LOGGER. */
		protected static final Logger LOGGER = Logger.getLogger(clientsDAO.class.getName());
		
		/** The Constant insertStatementS. */
		private static final String insertStatementS = "INSERT INTO Produse (idProdus, numeProdus, cantitate, pret)"
				+ " VALUES (?,?,?,?)";
		
		/** The Constant updateStatementS. */
		private static final String updateStatementS = "UPDATE Produse SET numeProdus = ?, cantitate = ?, pret = ? WHERE idProdus = ?";
		
		/** The Constant deleteStatementS. */
		private static final String deleteStatementS = "DELETE FROM Produse WHERE idProdus = ?";

		/**
		 * Insert product.
		 *
		 * @param produs the produs
		 */
		public void insertProduct(Produse produs) {

			Connection dbConnect = ConnectionFactory.getConnection();
			PreparedStatement insertStatement = null;
			int x = 0;

			try {
				insertStatement = dbConnect.prepareStatement(insertStatementS);
				insertStatement.setInt(1, produs.getIdProdus());
				insertStatement.setString(2, produs.getNumeProdus());
				insertStatement.setInt(3, produs.getCantitate());
				insertStatement.setInt(4, produs.getPret());
				insertStatement.executeUpdate();

			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "ERROR INSERT PRODUCTS" + e.getMessage());
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
		 * Update products.
		 *
		 * @param idToUpdate the id to update
		 * @param productToUpdate the product to update
		 */
		public void updateProducts(int idToUpdate, Produse productToUpdate) {
			Connection dbConnect = ConnectionFactory.getConnection();
			PreparedStatement updateStatement = null;
			int x = 0;
			int adevarat = findById(idToUpdate);
			if (adevarat == 0) {
				JOptionPane.showMessageDialog(null, "Produsul cu ID-ul " + idToUpdate + " nu exista !");
				return;
			}

			try {
				updateStatement = dbConnect.prepareStatement(updateStatementS);
				updateStatement.setString(1, productToUpdate.getNumeProdus());
				updateStatement.setInt(2, productToUpdate.getCantitate());
				updateStatement.setInt(3, productToUpdate.getPret());
				updateStatement.setInt(4, idToUpdate);
				updateStatement.executeUpdate();

			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "ERROR UPDATE PRODUCTS " + e.getMessage());
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
		 * Delete product with id.
		 *
		 * @param id the id
		 */
		public void deleteProductWithId(int id) {
			Connection dbConnect = ConnectionFactory.getConnection();
			PreparedStatement deleteStatement = null;
			int x = 0;
			int adevarat = findById(id);
			if (adevarat == 0) {
				JOptionPane.showMessageDialog(null, "Produsul cu ID-ul " + id + " nu se afla pe stoc !");
				return;
			}

			try {
				deleteStatement = dbConnect.prepareStatement(deleteStatementS);
				deleteStatement.setInt(1, id);
				deleteStatement.executeUpdate();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "ERROR DELETE PRODUCT WITH ID " + e.getMessage());
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
			Produse returnProd = null;
			String s = "SELECT * FROM produse WHERE idProdus = ?";
			try {
				findById = dbConnect.prepareStatement(s);
				findById.setLong(1, id);
				rs = findById.executeQuery();
				rs.next();

				int idProdus = rs.getInt("idProdus");
				String den = rs.getString("numeProdus");
				int cant = rs.getInt("cantitate");
				int pret = rs.getInt("pret");
				returnProd = new Produse(idProdus, den, cant, pret);

			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "Problema la cautare produs " + e.getMessage());

			} finally {
				ConnectionFactory.close(findById);
				ConnectionFactory.close(dbConnect);
			}
			if (returnProd != null) {
				return 1;
			} else
				return 0;
		}

		/**
		 * Pret produs.
		 *
		 * @param id the id
		 * @return the int
		 */
		public int pretProdus(int id) {
			Connection dbConnection = ConnectionFactory.getConnection();
			ResultSet rs = null;
			PreparedStatement pretul = null;
			int pret = 0;

			try {

				pretul = dbConnection.prepareStatement("SELECT pret FROM Produse WHERE idProdus = ?");
				pretul.setInt(1, id);
				rs = pretul.executeQuery();
				rs.next();
				pret = rs.getInt("pret");

			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "Probleme aflare pret produs " + e.getMessage());
			} finally {
				ConnectionFactory.close(pretul);
				ConnectionFactory.close(dbConnection);
				ConnectionFactory.close(rs);
			}
			return pret;
		}
		
		/**
		 * Gets the cantitate.
		 *
		 * @param id the id
		 * @return the cantitate
		 */
		public int getCantitate(int id) {
			Connection dbConnection = ConnectionFactory.getConnection();
			ResultSet rs = null;
			PreparedStatement query = null;
			int cate = 0;

			try {

				query = dbConnection.prepareStatement("SELECT cantitate FROM Produse WHERE idProdus = ?");
				query.setInt(1, id);
				rs = query.executeQuery();
				rs.next();
				cate = rs.getInt("cantitate");

			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "Probleme aflare cantitate produs " + e.getMessage());
			} finally {
				ConnectionFactory.close(query);
				ConnectionFactory.close(dbConnection);
				ConnectionFactory.close(rs);
			}
			return cate;
		}
		
		/**
		 * Sets the cantitate.
		 *
		 * @param id the id
		 * @param cantity the cantity
		 */
		public void setCantitate(int id,int cantity) {
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement query = null;
			try {

				query = dbConnection.prepareStatement("UPDATE Produse SET cantitate = ? WHERE idProdus = ?");
				query.setInt(1, cantity);
				query.setInt(2, id);
				query.executeUpdate();

			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "Probleme update cantitate produs " + e.getMessage());
			} finally {
				ConnectionFactory.close(query);
				ConnectionFactory.close(dbConnection);
			}
			
		}
		
		public static ArrayList<Produse> getProduse() throws Exception
		{
			try
			{
				Connection con= ConnectionFactory.getConnection();
				PreparedStatement statement= con.prepareStatement("SELECT * FROM Produse");
			
				ResultSet result= statement.executeQuery();
				
				ArrayList<Produse> clienti= new ArrayList<Produse>();
			
				while(result.next())
				{
					int id=Integer.parseInt(result.getString("idProdus"));
					String nume= result.getString("numeProdus");
					int cantitate = Integer.parseInt(result.getString("cantitate"));
					int pret = Integer.parseInt(result.getString("pret"));
					
					
					Produse produs = new Produse(id,nume,cantitate,pret);
					clienti.add(produs);
					produs.toString();
					
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

