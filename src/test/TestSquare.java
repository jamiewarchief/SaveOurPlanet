/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.DynAnyPackage.Invalid;

import saveourplanet.Player;
import saveourplanet.Square;

/**
 * @author lukem
 *
 */
class TestSquare {
	
	// Vars
	String validSquareName;
	String invalidSquareName;
	
	int squareOwnerIDValidHigh;
	int squareOwnerIDValidLow;
	int squareOwnerIDInvalidHigh;
	int squareOwnerIDInvalidLow;

	int squareNumberValidHigh;
	int squareNumberValidLow;
	int squareNumberInvalidHigh;
	int squareNumberInvalidLow;
	
	int squareFieldValidHigh;
	int squareFieldValidLow;
	int squareFieldInvalidHigh;
	int squareFieldInvalidLow;
	
	int squarePurchaseCostValid;
	int squarePurchaseCostInvalid;
	
	int squareDevCostValid;
	int squareDevCostInvalid;
	
	int squareDevStatusValidHigh;
	int squareDevStatusValidLow;
	int squareDevStatusInvalidHigh;
	int squareDevStatusInvalidLow;
	
	int squareRentAmountValid;
	int squareRentAmountInvalid;
	
	ArrayList<Integer> squareRentValuesValid;
	ArrayList<Integer> squareRentValuesInvalid;
	
	String fieldNameValid0;
	String fieldNameValid1;
	String fieldNameValid2;
	String fieldNameValid3;
	String fieldNameValid4;
	String fieldNameInvalid;
	
	int fieldIDValid0;
	int fieldIDValid1;
	int fieldIDValid2;
	int fieldIDValid3;
	int fieldIDValid4;
	
	int fieldIDInvalid;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		validSquareName = "valid_name";
		invalidSquareName = "";
		
		squareOwnerIDValidHigh = 4;
		squareOwnerIDValidLow = 0;
		squareOwnerIDInvalidHigh = 5;
		squareOwnerIDInvalidLow = -2;
		
		squareNumberValidHigh = 11;
		squareNumberValidLow = 0;
		squareNumberInvalidHigh = 12;
		squareNumberInvalidLow = -1;
		
		squareFieldValidHigh = 4;
		squareFieldValidLow = 0;
		squareFieldInvalidHigh = 5;
		squareFieldInvalidLow = -1;
		
		squarePurchaseCostValid = 0;
		squarePurchaseCostInvalid = -1;
		
		squareDevCostValid = 0;
		squareDevCostInvalid = -1;
		
		squareDevStatusValidHigh = 4;
		squareDevStatusValidLow = 0;
		squareDevStatusInvalidHigh = 5;
		squareDevStatusInvalidLow = -1;
		
		squareRentAmountValid = 0;
		squareRentAmountInvalid = -1;
		
		squareRentValuesValid = new ArrayList<Integer>();
		squareRentValuesValid.add(0);
		squareRentValuesValid.add(0);
		squareRentValuesValid.add(0);
		squareRentValuesValid.add(0);
		squareRentValuesValid.add(0);
		
		squareRentValuesInvalid = new ArrayList<Integer>();
		squareRentValuesInvalid.add(-1);
		squareRentValuesInvalid.add(-1);
		squareRentValuesInvalid.add(-1);
		squareRentValuesInvalid.add(-1);
		squareRentValuesInvalid.add(-1);
		
		fieldNameValid0 = "Free Square";
		fieldNameValid1 = "Recycling";
		fieldNameValid2 = "Global Disaster";
		fieldNameValid3 = "Current Tech";
		fieldNameValid4 = "Future Tech";
		
		fieldNameInvalid = "Error getting field name";
		
		fieldIDValid0 = 0;
		fieldIDValid1 = 1;
		fieldIDValid2 = 2;
		fieldIDValid3 = 3;
		fieldIDValid4 = 4;
		
		fieldIDInvalid = -1;
		
	}
	
	@Test
	void testDefaultConstructor() {
		Square testSquare = new Square();
		
		assertNotNull(testSquare);
	}
	
	@Test
	void testConstructorArgsValid() {
		Square testSquare1 = new Square(validSquareName, squareOwnerIDValidHigh, squareNumberValidHigh, squareFieldValidHigh, squarePurchaseCostValid, squareDevCostValid, squareDevStatusValidHigh, squareRentAmountValid, squareRentValuesValid);
		Square testSquare2 = new Square(validSquareName, squareOwnerIDValidLow, squareNumberValidLow, squareFieldValidLow, squarePurchaseCostValid, squareDevCostValid, squareDevStatusValidLow, squareRentAmountValid, squareRentValuesValid);
		
		assertEquals(validSquareName, testSquare1.getSquareName());
		assertEquals(validSquareName, testSquare2.getSquareName());
		
		assertEquals(squareOwnerIDValidHigh, testSquare1.getSquareOwnerID());
		assertEquals(squareOwnerIDValidLow, testSquare2.getSquareOwnerID());
		
		assertEquals(squareNumberValidHigh, testSquare1.getSquareNumber());
		assertEquals(squareNumberValidLow, testSquare2.getSquareNumber());
		
		assertEquals(squareFieldValidHigh, testSquare1.getSquareFieldID());
		assertEquals(squareFieldValidLow, testSquare2.getSquareFieldID());
		
		assertEquals(squarePurchaseCostValid, testSquare1.getSquarePurchaseCost());
		assertEquals(squarePurchaseCostValid, testSquare2.getSquarePurchaseCost());
		
		assertEquals(squareDevCostValid, testSquare1.getSquareDevCost());
		assertEquals(squareDevCostValid, testSquare2.getSquareDevCost());
		
		assertEquals(squareDevStatusValidHigh, testSquare1.getSquareDevStatus());
		assertEquals(squareDevStatusValidLow, testSquare2.getSquareDevStatus());
		
		assertEquals(squareRentAmountValid, testSquare1.getSquareRentAmount());
		assertEquals(squareRentAmountValid, testSquare2.getSquareRentAmount());
		
		assertEquals(squareRentValuesValid, testSquare1.getSquareRentValues());
		assertEquals(squareRentValuesValid, testSquare2.getSquareRentValues());
	}
	
	@Test
	void testConstructorArgsInvalid() {
		Square testSquare1 = new Square(invalidSquareName, squareOwnerIDInvalidHigh, squareNumberInvalidHigh, squareFieldInvalidHigh, squarePurchaseCostInvalid, squareDevCostInvalid, squareDevStatusInvalidHigh, squareRentAmountInvalid, squareRentValuesInvalid);
		Square testSquare2 = new Square(invalidSquareName, squareOwnerIDInvalidLow, squareNumberInvalidLow, squareFieldInvalidLow, squarePurchaseCostInvalid, squareDevCostInvalid, squareDevStatusInvalidLow, squareRentAmountInvalid, squareRentValuesInvalid);
	
		assertNotEquals(invalidSquareName, testSquare1.getSquareName());
		assertNotEquals(invalidSquareName, testSquare2.getSquareName());
		
		assertNotEquals(squareOwnerIDInvalidHigh, testSquare1.getSquareOwnerID());
		assertNotEquals(squareOwnerIDInvalidLow, testSquare2.getSquareOwnerID());
		
		assertNotEquals(squareNumberInvalidHigh, testSquare1.getSquareNumber());
		assertNotEquals(squareNumberInvalidLow, testSquare2.getSquareNumber());
		
		assertNotEquals(squareFieldInvalidHigh, testSquare1.getSquareFieldID());
		assertNotEquals(squareFieldInvalidLow, testSquare2.getSquareFieldID());
		
		assertNotEquals(squarePurchaseCostInvalid, testSquare1.getSquarePurchaseCost());
		assertNotEquals(squarePurchaseCostInvalid, testSquare2.getSquarePurchaseCost());
		
		assertNotEquals(squareDevCostInvalid, testSquare1.getSquareDevStatus());
		assertNotEquals(squareDevCostInvalid, testSquare2.getSquareDevCost());
		
		assertNotEquals(squareDevStatusInvalidHigh, testSquare1.getSquareDevStatus());
		assertNotEquals(squareDevStatusInvalidLow, testSquare2.getSquareDevStatus());
		
		assertNotEquals(squareRentAmountInvalid, testSquare1.getSquareRentAmount());
		assertNotEquals(squareRentAmountInvalid, testSquare2.getSquareRentAmount());
		
		assertNotEquals(squareRentValuesInvalid, testSquare1.getSquareRentValues());
		assertNotEquals(squareRentValuesInvalid, testSquare2.getSquareRentValues());
	}
	
	@Test
	void fieldNamesTestValid() {
		
		assertEquals(fieldNameValid0, Square.fieldNames(fieldIDValid0));
		assertEquals(fieldNameValid1, Square.fieldNames(fieldIDValid1));
		assertEquals(fieldNameValid2, Square.fieldNames(fieldIDValid2));
		assertEquals(fieldNameValid3, Square.fieldNames(fieldIDValid3));
		assertEquals(fieldNameValid4, Square.fieldNames(fieldIDValid4));
		
		assertEquals(fieldNameInvalid, Square.fieldNames(fieldIDInvalid));
	}

}
