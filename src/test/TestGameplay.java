package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saveourplanet.Player;
import saveourplanet.Square;

class TestGameplay {
	
	// VARS
	
	Player currentPlayer = new Player();
	
	ArrayList<Integer> rents1 = new ArrayList<Integer>();
	ArrayList<Integer> rents2 = new ArrayList<Integer>();
	ArrayList<Integer> rents3 = new ArrayList<Integer>();
	ArrayList<Integer> rents4 = new ArrayList<Integer>();
	ArrayList<Integer> rents5 = new ArrayList<Integer>();
	ArrayList<Integer> rents6 = new ArrayList<Integer>();
	ArrayList<Integer> rents7 = new ArrayList<Integer>();
	ArrayList<Integer> rents8 = new ArrayList<Integer>();
	ArrayList<Integer> rents9 = new ArrayList<Integer>();
	ArrayList<Integer> rents10 = new ArrayList<Integer>();
	ArrayList<Integer> rents11 = new ArrayList<Integer>();
	ArrayList<Integer> rents12 = new ArrayList<Integer>();

	ArrayList<Square> squares = new ArrayList<Square>();
	Square sq1 = new Square("Go", -1, 0, 0, 200, 300, 0, rents1.get(0), rents1);
	Square sq2 = new Square("Square One", 1, 1, 1, 200, 300, 0, rents2.get(0), rents2);
	Square sq3 = new Square("Square Two", 1, 2, 1, 200, 300, 0, rents3.get(0), rents3);
	Square sq4 = new Square("Square Three", 0, 3, 2, 200, 300, 0, rents4.get(0), rents4);
	Square sq5 = new Square("Square Four", 0, 4, 2, 200, 300, 0, rents5.get(0), rents5);
	Square sq6 = new Square("Square Five", 0, 5, 2, 200, 300, 0, rents6.get(0), rents6);
	Square sq7 = new Square("Free Parking", -1, 6, 0, 200, 300, 0, rents7.get(0), rents7);
	Square sq8 = new Square("Square Seven", 0, 7, 3, 200, 300, 0, rents8.get(0), rents8);
	Square sq9 = new Square("Square Eight", 0, 8, 3, 200, 300, 0, rents9.get(0), rents9);
	Square sq10 = new Square("Square Nine", 0, 9, 3, 200, 300, 0, rents10.get(0), rents10);
	Square sq11 = new Square("Square Ten", 0, 10, 4, 200, 300, 0, rents11.get(0), rents11);
	Square sq12 = new Square("Square Eleven", 0, 11, 4, 200, 300, 0, rents12.get(0), rents12);
	
	ArrayList<Square> portfolio = new ArrayList<Square>();
	
	ArrayList<Integer> validEntirelyOwnedFields = new ArrayList<Integer>();
	ArrayList<Integer> invalidEntirelyOwnedFields = new ArrayList<Integer>();



	@BeforeEach
	void setUp() throws Exception {
		rents1.add(0);
		rents1.add(0);
		rents1.add(0);
		rents1.add(0);
		rents1.add(0);
		rents2.add(4);
		rents2.add(40);
		rents2.add(120);
		rents2.add(240);
		rents2.add(400);
		rents3.add(5);
		rents3.add(50);
		rents3.add(150);
		rents3.add(300);
		rents3.add(500);
		rents4.add(8);
		rents4.add(75);
		rents4.add(225);
		rents4.add(450);
		rents4.add(750);
		rents5.add(9);
		rents5.add(85);
		rents5.add(255);
		rents5.add(510);
		rents5.add(850);
		rents6.add(10);
		rents6.add(95);
		rents6.add(285);
		rents6.add(570);
		rents6.add(950);
		rents7.add(0);
		rents1.add(0);
		rents7.add(0);
		rents7.add(0);
		rents7.add(0);
		rents8.add(11);
		rents8.add(105);
		rents8.add(315);
		rents8.add(630);
		rents8.add(1050);
		rents9.add(12);
		rents9.add(115);
		rents9.add(345);
		rents9.add(690);
		rents9.add(1150);
		rents10.add(13);
		rents10.add(125);
		rents10.add(375);
		rents10.add(750);
		rents10.add(1250);
		rents11.add(15);
		rents11.add(150);
		rents11.add(450);
		rents11.add(900);
		rents11.add(1500);
		rents12.add(18);
		rents12.add(175);
		rents12.add(525);
		rents12.add(1050);
		rents12.add(1750);
		squares.add(sq1);
		squares.add(sq2);
		squares.add(sq3);
		squares.add(sq4);
		squares.add(sq5);
		squares.add(sq6);
		squares.add(sq7);
		squares.add(sq8);
		squares.add(sq9);
		squares.add(sq10);
		squares.add(sq11);
		squares.add(sq12);
		
		portfolio.add(sq2);
		portfolio.add(sq3);
		
		currentPlayer.setPlayerPortfolio(portfolio);
		
		validEntirelyOwnedFields.add(1);
		currentPlayer.setEntirelyOwnedFields(validEntirelyOwnedFields);
		
		
		
	}

//	@Test
//	void testMain() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testLandOnSquare() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDevelopSquare() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testPurchaseSquare() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testPayFine() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCheckForEntireFieldOwnership() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testKeywordCatcher() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDisplayRules() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSaveLauncher() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCheckPlayerBalance() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRemovePlayer() {
//		fail("Not yet implemented");
//	}

	@Test
	void testDevelop() {
		
	}

}
