package saveourplanet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author jamienevin
 * 
 *         A class to store all information pertaining to each Player, and their
 *         respective owned Squares / Fields.
 *
 */
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int ID_LENGTH = 1;
	private static final int BANKRUPT = 0;

	private String playerName;
	private int playerID, playerBalance;
	private boolean takingTurn;
	/**
	 * Stores all Squares that Player owns
	 */
	private ArrayList<Square> playerPortfolio;
	private int currentSquare;
	/**
	 * Stores field ID of any fields Player owns
	 */
	private ArrayList<Integer> entirelyOwnedFields;

	/**
	 * Default Constructor
	 */
	public Player() {
	}

	/**
	 * Constructor with parameter arguments
	 * 
	 * @param playerName
	 * @param playerID
	 * @param playerBalance
	 * @param currentSquare
	 * @param portfolio
	 * @param entirelyOwnedFields
	 */
	public Player(String playerName, int playerID, int playerBalance, int currentSquare, ArrayList<Square> portfolio,
			ArrayList<Integer> entirelyOwnedFields) {

		ArrayList<Square> emptyPortfolio = new ArrayList<Square>();
		ArrayList<Integer> emptyOwnedFields = new ArrayList<Integer>();

		this.setPlayerName(playerName);
		this.setPlayerID(playerID);
		this.setPlayerBalance(playerBalance);
		this.setCurrentSquare(currentSquare);
		this.setPlayerPortfolio(emptyPortfolio);
		this.setEntirelyOwnedFields(emptyOwnedFields);
	}

	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) throws IllegalArgumentException {
		if (playerName.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			this.playerName = playerName;
		}

	}

	/**
	 * @return the playerID
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * @param playerID the playerID to set including validation for minimum length.
	 */
	public void setPlayerID(int playerID) throws IllegalArgumentException {
		if (Integer.toString(playerID).length() != ID_LENGTH || playerID < 1 || playerID > 4) {
			throw new IllegalArgumentException();
		} else {
			this.playerID = playerID;
		}
	}

	/**
	 * @return the playerBalance
	 */
	public int getPlayerBalance() {
		return playerBalance;
	}

	/**
	 * @param playerBalance the playerBalance to set
	 */
	public void setPlayerBalance(int playerBalance) {
		if (playerBalance <= BANKRUPT) {
			this.takingTurn = false;
		} 
		this.playerBalance = playerBalance;
	}

	/**
	 * @return the takingTurn
	 */
	public boolean isTakingTurn() {
		return takingTurn;
	}

	/**
	 * @param takingTurn the takingTurn to set
	 */
	public void setTakingTurn(boolean takingTurn) {
		this.takingTurn = takingTurn;
	}

	/**
	 * @return the playerPortfolio
	 */
	public ArrayList<Square> getPlayerPortfolio() {
		return playerPortfolio;
	}

	/**
	 * @param playerPortfolio the playerPortfolio to set
	 */
	public void setPlayerPortfolio(ArrayList<Square> playerPortfolio) {
		this.playerPortfolio = playerPortfolio;

	}

	

	/**
	 * 
	 * @param currentSquare the currentSquare to set
	 */
	public void setCurrentSquare(int currentSquare) {
		if ((currentSquare) >= Square.SQUARE_NUMBER_MIN && (currentSquare) <= Square.SQUARE_NUMBER_MAX) {
			this.currentSquare = currentSquare;
		} else {
			throw new IllegalArgumentException("Problem with input of current square");
		}
	}
	
	/**
	 * 
	 * @return the currentSquare
	 */
	public int getCurrentSquare() {
		return currentSquare;
	}

	/**
	 * 
	 * @return the entirelyOwnedFields
	 */
	public ArrayList<Integer> getEntirelyOwnedFields() {
		return entirelyOwnedFields;
	}

	/**
	 * 
	 * @param entirelyOwnedFields the entirelyOwnedFields to set
	 */
	public void setEntirelyOwnedFields(ArrayList<Integer> entirelyOwnedFields) {
		this.entirelyOwnedFields = entirelyOwnedFields;
	}

	/**
	 * 
	 * @param sets the new position of the Player
	 */
	public void movePlayer(int nextSquare) {
		if (((this.getCurrentSquare() + nextSquare) % 12) == 0) {
			this.setCurrentSquare(0);

		} else {
			this.setCurrentSquare((this.getCurrentSquare() + nextSquare) % 12);
		}
	}

}
