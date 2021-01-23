package saveourplanet;

/**
 * Class to roll 2 dice
 * 
 * @author simonstevenson
 *
 */
public class RollDice {

	//Vars
	
	private int die1, die2, diceTotal;
	private String currentLine;
	private boolean correctInputRoll;

	//Constants
	
	private final int MAX_DICE = 6;
	private final int MIN_DICE = 1;

	
	/**
	 * Default constructor
	 */
	public RollDice() {


	}


	/**
	 * Starts the players roll
	 * @throws InterruptedException 
	 */
	public void startRoll() throws InterruptedException {
		System.out.print("\nRolling.");
		Thread.sleep(500);
		System.out.print(".");
		Thread.sleep(500);
		System.out.println(".\n\n\n\n");
		Thread.sleep(500);

		die1 = (int) ((Math.random() * MAX_DICE) + MIN_DICE);

		die2 = (int) ((Math.random() * MAX_DICE) + MIN_DICE);

		diceTotal = die1 + die2;

		this.setDice(die1, die2, diceTotal);

		System.out.println("You rolled a " + die1 + " and a " + die2 + ", giving you a total of "+diceTotal+".");

	}
	
	
	//Getters and Setters

	
	/**
	 * Setter for dice total.
	 * 
	 * @param diceTotal
	 * @param die2
	 * @param die1
	 */
	public void setDice(int die1, int die2, int diceTotal) {
		this.die1 = die1;

		this.die2 = die2;

		this.diceTotal = diceTotal;
		

	}

	/**
	 * Returns dice total set in startRoll()
	 * 
	 * @return
	 */
	public int getDiceTotal() {
		return this.diceTotal;
	}

	/**
	 * @return the die1
	 */
	public int getDie1() {
		return die1;
	}

	/**
	 * @return the die2
	 */
	public int getDie2() {
		return die2;
	}
	
	/**
	 * @return the correctInputRoll
	 */
	public boolean isCorrectInputRoll() {
		return correctInputRoll;
	}
	

	/**
	 * Checks if the player has rolled a double and counts how many rolls, if rolls double more twice account deducted 200
	 * - not used, here for future implementation
	 */
	public void checkDouble() {

		if ((this.getDie1() == this.getDie2()) && (this.getDie1() > 0 && this.getDie2() > 0)) {

			int rollCount = 0;

			if (rollCount<=2 && correctInputRoll) {
				// increments so that player are either rewarded or punished
				rollCount++;
				System.out.println("Congratulations you rolled both dice and they both landed on a " + this.getDie1()
						+ ". Would you like to roll again?");
				currentLine = Gameplay.sc.next();
				if (currentLine.equalsIgnoreCase("Y") || currentLine.equalsIgnoreCase("Yes")) {
					this.isCorrectInputRoll();
				} else if (currentLine.equalsIgnoreCase("N") || currentLine.equalsIgnoreCase("No")) {
					System.out.println("No problem, you will forfeit your turn");
				} else { 
					System.out.println("Sorry, we didn't understand that answer! "); 
				} 
			} else {
				System.out.println("Unfortunately you have rolled 2 doubles in a row, your account will be deducted 200");
			} 
			
			

		}

	}

}
