/**
 * 
 */
package saveourplanet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Luke
 *
 */
public class Square implements Serializable {

	// Constants

	private static final long serialVersionUID = 1L;

	protected final static int SQUARE_NUMBER_MIN = 0;
	protected final static int SQUARE_NUMBER_MAX = 11;
	protected final static int SQUARE_FIELDS_MIN = 0;
	protected final static int SQUARE_FIELDS_MAX = 4;
	
	// *** Note that SQUARE_FIELD_X_SQUARE_IDS stores the FieldID of the subsequent SquareIDs at index 0 ***

	public final static int[] SQUARE_FIELD_1_IDS = {1,2,3};
	public final static int[] SQUARE_FIELD_2_IDS = {2,4,5,6};
	public final static int[] SQUARE_FIELD_3_IDS = {3,8,9,10};
	public final static int[] SQUARE_FIELD_4_IDS = {4,11,12};
	public final static int[][] SQUARE_FIELD_IDS = {SQUARE_FIELD_1_IDS, SQUARE_FIELD_2_IDS, SQUARE_FIELD_3_IDS, SQUARE_FIELD_4_IDS };

	// Vars

	private String squareName;
	private int squareOwnerID;
	private int squareNumber;
	private int squareFieldID;
	private int squarePurchaseCost;
	private int squareDevCost;
	private int squareDevStatus; // indicates the development status of square. 0 = base, 1-3 = 1-3 houses
									// respectively, 4 = hotel
	private int squareRentAmount;
	private ArrayList<Integer> squareRentValues;

	// Constructors

	/**
	 * 
	 * Square class constructor with all parameters
	 * 
	 * @param squareNamee4
	 * @param squareOwnerName
	 * @param squareNumber
	 * @param squareFieldID
	 * @param squarePurchaseCost
	 * @param squareDevCost
	 * @param squareDevStatus
	 * @param squareRentAmount
	 */
	public Square(String squareName, int squareOwnerID, int squareNumber, int squareFieldID, int squarePurchaseCost,
			int squareDevCost, int squareDevStatus, int squareRentAmount, ArrayList<Integer> squareRentValues) {
		super();
		this.setSquareName(squareName);
		this.setSquareOwnerID(squareOwnerID);
		this.setSquareNumber(squareNumber);
		this.setSquareFieldID(squareFieldID);
		this.setSquarePurchaseCost(squarePurchaseCost);
		this.setSquareDevCost(squareDevCost);
		this.setSquareDevStatus(squareDevStatus);
		this.setSquareRentAmount(squareRentAmount);
		this.setSquareRentValues(squareRentValues);
	}
	

	/**
	 * Default Constructor
	 */
	public Square() {
	}

	// Getters and Setters

	/**
	 * @return the squareNameString
	 */
	public String getSquareName() {
		return squareName;
	}

	/**
	 * @param squareNameString the squareNameString to set
	 */
	public void setSquareName(String squareName) {
		if (squareName.length() >= 1) {
			this.squareName = squareName;
		} else {
			System.err.println("Error setting square name, must be at least 1 char in length");
		}
	}

	/**
	 * @return the squareNumber
	 */
	public int getSquareNumber() {
		return squareNumber;
	}

	/**
	 * Allows squareNumber to bet set to a value between 0 and 11 (i.e. within range
	 * of total number of squares)
	 * 
	 * @param squareNumber the squareNumber to set
	 */
	public void setSquareNumber(int squareNumber) {
		if (squareNumber >= SQUARE_NUMBER_MIN && squareNumber <= SQUARE_NUMBER_MAX) {
			this.squareNumber = squareNumber;
		} else {
			System.out.println("Error setting Square Number");
		}
	}

	/**
	 * @return the squareFieldID
	 */
	public int getSquareFieldID() {
		return squareFieldID;
	}

	/**
	 * Allows squareFieldID to be set to a value between 0 and 4
	 * 
	 * @param squareFieldID the squareFieldID to set
	 */
	public void setSquareFieldID(int squareFieldID) {
		if (squareFieldID >= SQUARE_FIELDS_MIN && squareFieldID <= SQUARE_FIELDS_MAX) {
			this.squareFieldID = squareFieldID;
		} else {
			System.err.println("squareFieldID must be within range 0-4");
		}
	}

	/**
	 * @return the squareOwnerID
	 */
	public int getSquareOwnerID() {
		return squareOwnerID;
	}

	/**
	 * @param squareOwnerID the squareOwnerID to set
	 */
	public void setSquareOwnerID(int squareOwnerID) {
		if (squareOwnerID >= -1 && squareOwnerID <= 4 ) {
			this.squareOwnerID = squareOwnerID;
		} else {
			System.err.println("SquareOwnerID must be within range -1 -> 4");
		}
	}

	/**
	 * @return the squarePurchaseCost
	 */
	public int getSquarePurchaseCost() {
		return squarePurchaseCost;
	}

	/**
	 * @param squarePurchaseCost the squarePurchaseCost to set
	 */
	public void setSquarePurchaseCost(int squarePurchaseCost) {
		if (squarePurchaseCost >= 0) {
			this.squarePurchaseCost = squarePurchaseCost;
		} else {
			System.out.println("Error setting square purchase cost");
		}
	}

	/**
	 * @return the squareDevCost
	 */
	public int getSquareDevCost() {
		return squareDevCost;
	}

	/**
	 * @param squareDevCost the squareDevCost to set
	 */
	public void setSquareDevCost(int squareDevCost) {
		if (squareDevCost >= 0) {
			this.squareDevCost = squareDevCost;
		} else {
			System.out.println("Error setting square dev cost");
		}
	}

	/**
	 * @return the squareDevStatus
	 */
	public int getSquareDevStatus() {
		return squareDevStatus;
	}

	/**
	 * @param squareDevStatus the squareDevStatus to set
	 */
	public void setSquareDevStatus(int squareDevStatus) {
		if (squareDevStatus >=0 && squareDevStatus <= 4) {
			this.squareDevStatus = squareDevStatus;
		} else {
			System.out.println("Error setting dev status - must be within range 0-4");
		}
	}

	/**
	 * @return the squareRentAmount
	 */
	public int getSquareRentAmount() {
		return squareRentAmount;
	}

	/**
	 * @param squareRentAmount the squareRentAmount to set
	 */
	public void setSquareRentAmount(int squareRentAmount) {

		if (squareRentAmount >= 0) {
			this.squareRentAmount = squareRentAmount;
		} else {
			System.out.println(" Error setting Square Rent Amount");
		}

	}

	/**
	 * Gets squareRentValues
	 * @return
	 */
	public ArrayList<Integer> getSquareRentValues() {
		return squareRentValues;
	}

	/**
	 * Sets the SquareRentValues to the passed Integer type ArrayList
	 * @param squareRentValues
	 */
	public void setSquareRentValues(ArrayList<Integer> squareRentValues) {
		for (int val : squareRentValues) {
			if (val >= 0) {
				this.squareRentValues = squareRentValues;
			} else {
				System.out.println("Error setting rent rates array");
			}
		}
	}
	
	public static String fieldNames(int fieldID) {
		String fieldName;

		switch (fieldID) {
		case 0:
			fieldName = "Free Square";
			break;
		case 1:
			fieldName = "Recycling";
			break;
		case 2:
			fieldName = "Global Disaster";
			break;
		case 3:
			fieldName = "Current Tech";
			break;
		case 4:
			fieldName = "Future Tech";
			break;
		default:
			fieldName = "Error getting field name";
		}

		return fieldName;

	}

}
