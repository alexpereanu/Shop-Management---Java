package model;

// TODO: Auto-generated Javadoc
/**
 * The Class clients.
 */
public class clients {
	
	/** The id. */
	private int ID;
	
	/** The Nume. */
	private String Nume;
	
	/** The Oras. */
	private String Oras;
	
	/**
	 * Instantiates a new clients.
	 *
	 * @param iD the i D
	 * @param nume the nume
	 * @param oras the oras
	 */
	public clients(int iD, String nume, String oras) {
		super();
		ID = iD;
		Nume = nume;
		Oras = oras;
	}
	public clients() {
		
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Sets the id.
	 *
	 * @param iD the new id
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * Gets the nume.
	 *
	 * @return the nume
	 */
	public String getNume() {
		return Nume;
	}

	/**
	 * Sets the nume.
	 *
	 * @param nume the new nume
	 */
	public void setNume(String nume) {
		Nume = nume;
	}

	/**
	 * Gets the oras.
	 *
	 * @return the oras
	 */
	public String getOras() {
		return Oras;
	}

	/**
	 * Sets the oras.
	 *
	 * @param oras the new oras
	 */
	public void setOras(String oras) {
		Oras = oras;
	}
	
	
	
}
