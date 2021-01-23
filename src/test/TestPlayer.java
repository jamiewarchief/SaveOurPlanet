/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saveourplanet.Player;
import saveourplanet.Square;

/**
 * @author jamienevin
 *
 */
class TestPlayer {

	Player p, p1, p2, p3;
	String nameP1, nameP2, nameP3;
	int idP1, idP2, idP3, balanceP1, balanceP2, balanceP3, currentSquareP1, currentSquareP2, currentSquareP3;

	String playerName, invalidPlayerName;
	int playerID, invalidPlayerIDUpper, invalidPlayerIDLower, playerBalance,
			playerBalanceInvalidLower, currentSquare, invalidCurrentSquareLower, invalidCurrentSquareUpper;
	boolean takingTurnTrue, takingTurnFalse;
	ArrayList<Square> playerPortfolio;
	ArrayList<Player> arrayList;
	ArrayList<Integer> entirelyOwnedFields;
	
	ArrayList<Square> playerPortfolio1;
	ArrayList<Square> playerPortfolio2;
	ArrayList<Square> playerPortfolio3;
	
	ArrayList<Integer> entirelyOwnedFields1;
	ArrayList<Integer> entirelyOwnedFields2;
	ArrayList<Integer> entirelyOwnedFields3;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		playerName = "Test Name";
		invalidPlayerName = "";
		playerID = 3;
		invalidPlayerIDUpper = 23;
		invalidPlayerIDLower = -1;
		playerBalance = 1400;
		playerBalanceInvalidLower = -300;
		currentSquare = 6;
		invalidCurrentSquareLower = -1;
		invalidCurrentSquareUpper = 13;
		takingTurnTrue = true;
		takingTurnFalse = false;

		nameP1 = "John";
		nameP2 = "Fred";
		nameP3 = "Chloe";

		idP1 = 1;
		idP2 = 2;
		idP3 = 3;
		
		balanceP1 = 300;
		balanceP2 = 750;
		balanceP3 = 1345;

		currentSquareP1 = 3;
		currentSquareP2 = 5;
		currentSquareP3 = 9;
		
		playerPortfolio1 = new ArrayList<Square>();
		playerPortfolio2 = new ArrayList<Square>();
		playerPortfolio3 = new ArrayList<Square>();

		p1 = new Player(nameP1, idP1, balanceP1, currentSquareP1, playerPortfolio1, entirelyOwnedFields1);
		p2 = new Player(nameP2, idP2, balanceP2, currentSquareP2, playerPortfolio2, entirelyOwnedFields2);
		p3 = new Player(nameP3, idP3, balanceP3, currentSquareP3, playerPortfolio3, entirelyOwnedFields3);

		arrayList = new ArrayList<Player>();
		arrayList.add(p1);
		arrayList.add(p2);
		arrayList.add(p3);
	}

	/**
	 * 
	 * 
	 * CONSTRUCTOR TESTS
	 */
	@Test
	final void testConstructorDefault() {
		p = new Player();
		assertNotNull(p);
	}

	@Test
	final void testConstructorArgs() {
		p = new Player(playerName, playerID, playerBalance, currentSquare, playerPortfolio, entirelyOwnedFields);

		assertEquals(playerName, p.getPlayerName());
		assertEquals(playerID, p.getPlayerID());
		assertEquals(playerBalance, p.getPlayerBalance());
		assertEquals(playerPortfolio, p.getPlayerPortfolio());
		assertEquals(entirelyOwnedFields, p.getEntirelyOwnedFields());
	}

	@Test
	final void testConstructorInvalidArgs() {

		assertThrows(IllegalArgumentException.class, () -> {
			p = new Player(invalidPlayerName, playerID, playerBalance, currentSquare, playerPortfolio, entirelyOwnedFields);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			p = new Player(playerName, invalidPlayerIDUpper, playerBalance, currentSquare, playerPortfolio, entirelyOwnedFields);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			p = new Player(playerName, playerID, playerBalance, invalidCurrentSquareUpper, playerPortfolio, entirelyOwnedFields);
		});
	}

	/*
	 * 
	 * VALID TESTS
	 */
	@Test
	final void testName() {
		p.setPlayerName(playerName);
		assertEquals(playerName, p.getPlayerName());
	}

	@Test
	final void testID() {
		p.setPlayerID(playerID);
		assertEquals(playerID, p.getPlayerID());
	}

	@Test
	final void testBalance() {
		p.setPlayerBalance(playerBalance);
		assertEquals(playerBalance, p.getPlayerBalance());
		
		p.setPlayerBalance(playerBalanceInvalidLower);
		assertEquals(playerBalanceInvalidLower, p.getPlayerBalance());
		
		p.setPlayerBalance(0);
		assertEquals(0, p.getPlayerBalance());
		
		assertFalse(p.isTakingTurn());
	}
	
	@Test
	final void testCurrentSquare() {
		p.setCurrentSquare(currentSquare);
		assertEquals(currentSquare, p.getCurrentSquare());
	}
	
	@Test
	final void testTakingTurn() {
		p.setTakingTurn(takingTurnTrue);
		assertEquals(true, p.isTakingTurn());
		
		p.setTakingTurn(takingTurnFalse);
		assertEquals(false, p.isTakingTurn());
	}
	
//	@Test
//	final void testPlayerPortfolioValid() {
//		ArrayList<Player> 
//	}

	/**
	 * 
	 * INVALID TESTS
	 */
	@Test
	final void testNameInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			p.setPlayerName(invalidPlayerName);
		});
	}

	@Test
	final void testIDInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			p.setPlayerID(invalidPlayerIDLower);
		});
	}

	@Test
	final void testBalanceInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			p.setPlayerBalance(playerBalanceInvalidLower);
		});
	}
	
	@Test
	final void testCurrentSquareInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			p.setCurrentSquare(invalidCurrentSquareUpper);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			p.setCurrentSquare(invalidCurrentSquareLower);
		});
	}
	
	/**
	 * 
	 * 
	 * ARRAYLIST TEST
	 */

}
