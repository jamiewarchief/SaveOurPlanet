package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saveourplanet.SetUpGame;

class TestSetUpGame {
	
	int playerCountValidLower;
	int playerCountValidUpper;
	int playerCountInvalidLower;
	int playerCountInvalidUpper;

	SetUpGame sug;

	@BeforeEach
	void setUp() throws Exception {
		
		playerCountValidLower = 2;
		playerCountValidUpper = 4;
		playerCountInvalidLower = 1;
		playerCountInvalidUpper = 5;
		
		sug = new SetUpGame();
		
	}
	
//	@Test
//	void testDefaultConstructor() {
//		
//		sug = new SetUpGame();
//		assertNotNull(sug);
//		
//	}

	@Test
	void testValidPlayerCount() {
		
		sug.setPlayerCount(playerCountValidLower);
		assertEquals(playerCountValidLower, sug.getPlayerCount());
		
		sug.setPlayerCount(playerCountValidUpper);
		assertEquals(playerCountValidUpper, sug.getPlayerCount());
		
	}

	@Test
	void testInvalidPlayerCount() {
		
	}

	@Test
	void testSetPlayerNames() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPlayers() {
		fail("Not yet implemented");
	}

}
