package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Connection objects.
 *
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source: http://theopentutorials.com/tutorials/java/jdbc/jdbc-mysql-create-database-example/
 */

/**
*
* Aceasta clasa este folosita pentru conectarea la baza de date.
*
*/
public class ConnectionFactory {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	
	/** The Constant DRIVER. */
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	/** The Constant DBURL. */
	private static final String DBURL = "jdbc:mysql://localhost:3306/schooldb?useTimezone=true&serverTimezone=UTC";
	
	/** The Constant USER. */
	private static final String USER = "root";
	
	/** The Constant PASS. */
	private static final String PASS = "root123";

	/** The single instance. */
	private static ConnectionFactory singleInstance = new ConnectionFactory();

	/**
	 * Instantiates a new connection factory.
	 */
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new Connection object.
	 *
	 * @return the connection
	 */
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}

	/**
	 * Close.
	 *
	 * @param connection the connection
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
	}

	/**
	 * Close.
	 *
	 * @param statement the statement
	 */
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
	}

	/**
	 * Close.
	 *
	 * @param resultSet the result set
	 */
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
			}
		}
	}
}
