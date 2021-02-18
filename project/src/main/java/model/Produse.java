package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Produse.
 */
public class Produse {

	/** The id produs. */
	private int idProdus;
	
	/** The nume produs. */
	private String numeProdus;
	
	/** The cantitate. */
	private int cantitate;
	
	/** The pret. */
	private int pret;
	
	/**
	 * Instantiates a new produse.
	 *
	 * @param idProdus the id produs
	 * @param numeProdus the nume produs
	 * @param cantitate the cantitate
	 * @param pret the pret
	 */
	public Produse(int idProdus, String numeProdus, int cantitate, int pret) {
		super();
		this.idProdus = idProdus;
		this.numeProdus = numeProdus;
		this.cantitate = cantitate;
		this.pret = pret;
	}
	
	public Produse() {
		
	}

	/**
	 * Gets the id produs.
	 *
	 * @return the id produs
	 */
	public int getIdProdus() {
		return idProdus;
	}

	/**
	 * Sets the id produs.
	 *
	 * @param idProdus the new id produs
	 */
	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}

	/**
	 * Gets the nume produs.
	 *
	 * @return the nume produs
	 */
	public String getNumeProdus() {
		return numeProdus;
	}

	/**
	 * Sets the nume produs.
	 *
	 * @param numeProdus the new nume produs
	 */
	public void setNumeProdus(String numeProdus) {
		this.numeProdus = numeProdus;
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
	 * Gets the pret.
	 *
	 * @return the pret
	 */
	public int getPret() {
		return pret;
	}

	/**
	 * Sets the pret.
	 *
	 * @param pret the new pret
	 */
	public void setPret(int pret) {
		this.pret = pret;
	}
	
	
	
	
}
