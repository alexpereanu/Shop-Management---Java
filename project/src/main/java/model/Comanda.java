package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Comanda.
 */
public class Comanda {

	/** The id client. */
	private int id_client;
	
	/** The id produs. */
	private int id_produs;
	
	/** The cantitate. */
	private int cantitate;
	
	/** The suma. */
	private int suma;
	
	/**
	 * Instantiates a new comanda.
	 *
	 * @param id_client the id client
	 * @param id_produs the id produs
	 * @param cantitate the cantitate
	 * @param suma the suma
	 */
	public Comanda(int id_client, int id_produs, int cantitate, int suma) {
		super();
		this.id_client = id_client;
		this.id_produs = id_produs;
		this.cantitate = cantitate;
		this.suma = suma;
	}


	/**
	 * Gets the id client.
	 *
	 * @return the id client
	 */
	public int getId_client() {
		return id_client;
	}

	/**
	 * Sets the id client.
	 *
	 * @param id_client the new id client
	 */
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	/**
	 * Gets the id produs.
	 *
	 * @return the id produs
	 */
	public int getId_produs() {
		return id_produs;
	}

	/**
	 * Sets the id produs.
	 *
	 * @param id_produs the new id produs
	 */
	public void setId_produs(int id_produs) {
		this.id_produs = id_produs;
	}

	/**
	 * Gets the cantitate.
	 *
	 * @return the cantitate
	 */
	public int getCantitate() {
		return cantitate;
	}

	/**
	 * Sets the cantitate.
	 *
	 * @param cantitate the new cantitate
	 */
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	/**
	 * Gets the suma.
	 *
	 * @return the suma
	 */
	public int getSuma() {
		return suma;
	}

	/**
	 * Sets the suma.
	 *
	 * @param suma the new suma
	 */
	public void setSuma(int suma) {
		this.suma = suma;
	}
	
	
	
	
	
	
}
