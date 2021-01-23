/**
 * 
 */
package saveourplanet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author ryanthompson
 * @author jamienevin
 * @author simonstevenson
 * @author lukemitchell
 *
 */
public class Gameplay {

	private static final int PASS_GO_MONEY = 100;

	static RollDice dice = new RollDice();

	ArrayList<Player> playerSave;
	ArrayList<Square> squareSave;

	static ArrayList<Square> squares = new ArrayList<Square>();
	static ArrayList<Player> players = new ArrayList<Player>();

	static Scanner sc = new Scanner(System.in);

	/**
	 * Main method for the Save Our Planet game.
	 * 
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {

		String currentLine, userInput, fileName;
		boolean validResponse = false;
		boolean flag = false;
		boolean developFlag = false;
		boolean innerFlag = false;

		Square currentSquare, previousSquare;

		// ******************** START OF LOADING PHASE **********************
		System.out.println("Welcome to Save Our Planet!\n");
		Thread.sleep(1000);

		System.out.println(
				"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*\n");

		System.out.println(
				"Due to an unfortunate natural disaster caused by climate change, you have been left a princly sum. \nOutraged at the lack of a united stance against the abuse of Mother Earth, you've sworn to fix what noone else will...");
		System.out.println(
				"Can you persuade the rest of the world to follow your lead? Will it be you that saves life as we know it?");
		System.out.println("We don't know... but we're asking you to Save Our Planet!");

		System.out.println(
				"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*\n");

		Thread.sleep(2000);

		displayRules();

		while (!flag) {

			System.out.println("Would you like to start a new game?");
			currentLine = sc.next();

			if (currentLine.equalsIgnoreCase("n") || currentLine.equalsIgnoreCase("no")) {

				System.out.println("Checking for save files...\n");
				Thread.sleep(500);

				SaveDriver.readSaveNames();

				if (!(SaveDriver.getSaveNames().equals(""))) {
					while (!innerFlag) {
						System.out.println("Would you like to load a game?");

						currentLine = sc.next();

						if (currentLine.equalsIgnoreCase("Y") || currentLine.equalsIgnoreCase("Yes")) {

							System.out.println("Your most recent save files:");
							System.out.println(SaveDriver.getSaveNames() + "\n");

							System.out.println("Which save would you like to load?");
							userInput = sc.next();
							fileName = userInput + ".ser";

							try {
								SaveDriver.loadGame(fileName);
								players = SaveDriver.getPlayerState();
								squares = SaveDriver.getSquareState();
								// setting flavour messages locally
								SetUpGame setup = new SetUpGame(true);
								setup.setFlavourMessages(setup.getSquareMessages0(), setup.getSquareMessages1(),
										setup.getSquareMessages2(), setup.getSquareMessages3(),
										setup.getSquareMessages4());
								flag = true;
								innerFlag = true;

							} catch (ClassNotFoundException | IOException e) {
								System.err.println("File not found!\n");

							}

						} else if (currentLine.equalsIgnoreCase("n") || currentLine.equalsIgnoreCase("no")) {
							innerFlag = true;
						} else {
							System.err.println("Please enter a valid command!\n");
							Thread.sleep(500);

						}
					}
				}
			} else if (currentLine.equalsIgnoreCase("y") || currentLine.equalsIgnoreCase("yes")) {
				SetUpGame setup = new SetUpGame();
				players = setup.getPlayersArray();
				squares = setup.getSquares();
				// setting flavour messages locally
				setup.setFlavourMessages(setup.getSquareMessages0(), setup.getSquareMessages1(),
						setup.getSquareMessages2(), setup.getSquareMessages3(), setup.getSquareMessages4());
				flag = true;
			} else {
				System.err.println("Please enter a valid command!\n");
				Thread.sleep(500);
			}

		}

		clearScreen();

		// ************** Main game loop. When only one player remains, they are deemed
		// the winner **************

		while (players.size() > 1)

		{

			// the player change for-loop
			for (Player currentPlayer : players) {

				// Initial display of information
				currentPlayer.setTakingTurn(true);
				currentSquare = squares.get(currentPlayer.getCurrentSquare());

				//currentPlayer.setPlayerBalance(currentPlayer.getPlayerBalance()+20000);
				
				System.out.println();
				System.out.println(randomTurnMessage(currentPlayer));
				System.out.println("Your balance is £" + currentPlayer.getPlayerBalance() + " and you are currently on "
						+ currentSquare.getSquareName() + ".\n");
				Thread.sleep(1500);

				// Check for existing investment portfolio
				if (!currentPlayer.getPlayerPortfolio().isEmpty()) {

					System.out.println("Your current investment portfolio:\n");

					System.out.println(
							"======================================================================================================================================");
					for (int i = 0; i < currentPlayer.getPlayerPortfolio().size(); i++) {
						System.out.print("ID: " + i + " --- "
								+ currentPlayer.getPlayerPortfolio().get(i).getSquareName() + " | Field: ("
								+ currentPlayer.getPlayerPortfolio().get(i).getSquareFieldID() + ") "
								+ Square.fieldNames(currentPlayer.getPlayerPortfolio().get(i).getSquareFieldID()));

						if (currentPlayer.getPlayerPortfolio().get(i).getSquareDevStatus() <= 3) {
							System.out.print("\n|" + " Investment Level: "
									+ currentPlayer.getPlayerPortfolio().get(i).getSquareDevStatus() + "/4" + " |");
						} else {
							System.out.print("\n|" + " Investment Level: " + "Breakthrough! 4/4 " + "|");
						}

						if (currentPlayer.getPlayerPortfolio().get(i).getSquareDevStatus() != 4) {
							System.out.print(" Cost to Develop: "
									+ currentPlayer.getPlayerPortfolio().get(i).getSquareDevCost());
						} else {
							System.out.print(" Fully developed!");
						}
						System.out.print(" |" + " Rent: "
								+ currentPlayer.getPlayerPortfolio().get(i).getSquareRentAmount() + "\n");

						if (!(i == (currentPlayer.getPlayerPortfolio().size() - 1))) {
							System.out.println(
									"--------------------------------------------------------------------------------------------------------------------------------------");
						}
					}
					System.out.println(
							"======================================================================================================================================");
					System.out.println("");
				}

				// Checking if development possible (i.e. full ownership of an investment field)
				for (int i = 0; i < Square.SQUARE_FIELD_IDS.length; i++) {
					checkForEntireFieldOwnership(Square.SQUARE_FIELD_IDS[i], currentPlayer);

				}

				if (!currentPlayer.getEntirelyOwnedFields().isEmpty()) {

					int field1FullDev = 0;
					boolean field1Disabled = false;
					int field2FullDev = 0;
					boolean field2Disabled = false;
					int field3FullDev = 0;
					boolean field3Disabled = false;
					int field4FullDev = 0;
					boolean field4Disabled = false;

					// Start of first loop checking development is still possible by checking if a
					// field has been fully developed - could be a method?
					for (int i = 0; i < currentPlayer.getPlayerPortfolio().size(); i++) {

						// checking if square in current index of loop is fully developed
						if (currentPlayer.getPlayerPortfolio().get(i).getSquareDevStatus() == 4) {

							// If it is, check which field it is in and increment it's respective int
							// Since fields 1 and 4 have 2 Squares but fields 2 and 3 have 3 Squares, there
							// are different requirements before each respective
							// field disabled flag is enabled
							if (currentPlayer.getPlayerPortfolio().get(i).getSquareFieldID() == 1) {
								field1FullDev++;

								if (field1FullDev == 2) {
									field1Disabled = true;
								}

							} else if (currentPlayer.getPlayerPortfolio().get(i).getSquareFieldID() == 2) {
								field2FullDev++;

								if (field2FullDev == 3) {
									field2Disabled = true;
								}

							} else if (currentPlayer.getPlayerPortfolio().get(i).getSquareFieldID() == 3) {
								field3FullDev++;

								if (field3FullDev == 3) {
									field3Disabled = true;
								}
							} else if (currentPlayer.getPlayerPortfolio().get(i).getSquareFieldID() == 4) {
								field4FullDev++;

								if (field4FullDev == 2) {
									field4Disabled = true;

								}
							}

						}

					} // end of loop checking development is still possible

					boolean canDevelop = false;

					// start of loop enabling or disabling the player's ability to develop based on
					// results of previous loop
					if (currentPlayer.getEntirelyOwnedFields().contains(1) && field1Disabled == false) {
						canDevelop = true;
					} else if (currentPlayer.getEntirelyOwnedFields().contains(2) && field2Disabled == false) {
						canDevelop = true;
					} else if (currentPlayer.getEntirelyOwnedFields().contains(3) && field3Disabled == false) {
						canDevelop = true;
					} else if (currentPlayer.getEntirelyOwnedFields().contains(4) && field4Disabled == false) {
						canDevelop = true;
					}

					// checking that player owns at least one field which hasn't been fully
					// developed before they are offered the chance to develop
					if (canDevelop == true) {
						while (developFlag != true) {
							System.out.println("Would " + currentPlayer.getPlayerName()
									+ " like to further develop any investment? (y/n)");
							currentLine = "";
							currentLine = sc.next();

							if (currentLine.equalsIgnoreCase("y")) {

								// BEGIN DEVELOPMENT
								develop(currentPlayer, squares);
								developFlag = true;

							} else if (currentLine.equalsIgnoreCase("n")) {
								System.out.println("No problem!\n");
								Thread.sleep(500);
								developFlag = true;

							} else {
								System.err.println("Please enter a valid command!");
								Thread.sleep(40);
							}
						}
					}
					developFlag = false;

				} else {
					System.out.println(
							"As you don't have investments in all areas of one field, you can't develop anything at this time!");
				}

				while (!validResponse) {

					// Ask to roll
					System.out.println(randomRollMessage(currentPlayer));
					currentLine = sc.next();
					keywordCatcher(currentLine, currentPlayer, players);

					if (currentLine.equalsIgnoreCase("Y") || currentLine.equalsIgnoreCase("Yes")) {

						previousSquare = currentSquare;

						dice.startRoll();
						currentPlayer.movePlayer(dice.getDiceTotal());
						if (((currentPlayer.getCurrentSquare() + 1) + dice.getDiceTotal())
								% (Square.SQUARE_NUMBER_MAX + 1) != 0) {
							currentSquare = squares.get(currentPlayer.getCurrentSquare());
						} else {
							currentSquare = squares.get((Square.SQUARE_NUMBER_MAX));
						}

						// Pass Go Money check
						passGoCheck(currentSquare, previousSquare, currentPlayer);

						System.out.println("You've landed on " + currentSquare.getSquareName() + " (Field "
								+ currentSquare.getSquareFieldID() + ": "
								+ Square.fieldNames(currentSquare.getSquareFieldID()) + "):");

						// printing a random flavour message for the current square
						System.out.println();
						randomFlavourMessage(currentSquare, SetUpGame.squareMessages0, SetUpGame.squareMessages1,
								SetUpGame.squareMessages2, SetUpGame.squareMessages3, SetUpGame.squareMessages4);
						System.out.println();
						Thread.sleep(1200);

						// all development/rent payments/purchases handled here:
						landOnSquare(currentSquare, currentPlayer, players, squares);
						Thread.sleep(500);
						checkPlayerBalance(currentPlayer, players);

						validResponse = true;

					} else if (currentLine.equalsIgnoreCase("N") || currentLine.equalsIgnoreCase("No")) {
						System.out.println("No problem, you have passed on this turn. Maybe next time!");
						validResponse = true;

					} else if (currentLine.equalsIgnoreCase("save") || currentLine.equalsIgnoreCase("s")) {
						continue;

					} else if (currentLine.equalsIgnoreCase("rules") || currentLine.equalsIgnoreCase("r")) {
						continue;
					} else {
						System.err.println("Please enter a valid command!");
					}
				}

				// END OF TURN
				validResponse = false;
				currentPlayer.setTakingTurn(false);
				System.out.println("-----------------");
				Thread.sleep(2000);
				System.out.println("\n\n\n\n");

			}

		}

		// Winner announced TO DO
		winner(players.get(0));
		sc.close();
		System.out.println(
				"Thank you to everyone for so nobly attempting to \"Save Our Planet!\"! We hope you enjoyed playing.");
		System.out.println("Bye!");
		// Exit the game
		System.exit(0);

	}

	/**
	 * Informs the players of the winner.
	 * 
	 * @param players the arraylist of players
	 * @throws InterruptedException
	 */
	private static void winner(Player winningPlayer) throws InterruptedException {
		String winningPlayerName = winningPlayer.getPlayerName();
		int winningPlayerBalance = winningPlayer.getPlayerBalance();

		// ADD SOME STUFF HERE ABOUT HOW GREAT THEY ARE ETC.
		System.out.print("At the end of the game, the winner is");
		Thread.sleep(800);
		System.out.print(".");
		Thread.sleep(800);
		System.out.print(".");
		Thread.sleep(800);
		System.out.println(".");
		Thread.sleep(1000);
		System.out.println("\n\n" + winningPlayerName + " with £" + winningPlayerBalance
				+ " left in their account, congratulations!\n");
		Thread.sleep(2000);

	}

	public static void exitBalances(ArrayList<Player> players) {

		System.out.println("Commiserations to the other remaining players!");
		for (Player p : players) {
			System.out.printf("%s, your closing balance was £%d.\n", p.getPlayerName(), p.getPlayerBalance());
		}
		System.out.println();

	}

	/**
	 * Checks if player has passed the Global Earth Day square and adds money if
	 * necessary.
	 * 
	 * @param currentSquare  player's square after rolling
	 * @param previousSquare player's square before rolling
	 * @param currentPlayer  the player currently taking a turn
	 */
	private static void passGoCheck(Square currentSquare, Square previousSquare, Player currentPlayer) {

		if (currentSquare.getSquareNumber() % (Square.SQUARE_NUMBER_MAX + 1) <= previousSquare.getSquareNumber()
				% (Square.SQUARE_NUMBER_MAX + 1)) {
			currentPlayer.setPlayerBalance(currentPlayer.getPlayerBalance() + PASS_GO_MONEY);

			// Message player receives upon passing GO (global earth day parades)
			System.out.println();
			System.out.println();
			System.out.println(
					"************************\nThe recent Global Earth Day demonstrations have caused a surge in donations toward environmental causes!\nYou have collected £"
							+ PASS_GO_MONEY + "; your new balance is now £" + currentPlayer.getPlayerBalance()
							+ ".\n************************\n");

		}

	}

	/**
	 * Chooses a message from a message bank when a player begins their turn.
	 * 
	 * @param currentPlayer the player to display the message to
	 * @return the message to be displayed
	 */
	private static String randomTurnMessage(Player currentPlayer) {
		String message;
		Random r = new Random();

		ArrayList<String> messages = new ArrayList<String>();
		messages.add("It's " + currentPlayer.getPlayerName() + "'s turn to play.");
		messages.add("OK, " + currentPlayer.getPlayerName() + ", you're up.");
		messages.add(currentPlayer.getPlayerName() + ", get ready to take your turn!");
		messages.add(currentPlayer.getPlayerName() + ", it's time for you to play.");
		messages.add("Get ready to play, " + currentPlayer.getPlayerName() + "!");

		message = messages.get(r.nextInt(messages.size() - 1));

		return message;
	}

	/**
	 * Chooses a message from a message bank when a player is requested to roll.
	 * 
	 * @param currentPlayer the player to display the message to
	 * @return the message to be displayed
	 */
	private static String randomRollMessage(Player currentPlayer) {
		String message;
		Random r = new Random();

		ArrayList<String> messages = new ArrayList<String>();
		messages.add("Would " + currentPlayer.getPlayerName() + " like to roll?");
		messages.add("Alright " + currentPlayer.getPlayerName() + ", do you want to roll this time?");
		messages.add(currentPlayer.getPlayerName() + ", do you want to roll to Save Our Planet?");
		messages.add(currentPlayer.getPlayerName() + ", can I interest you in rolling this time?");
		messages.add("Do you wish to roll, " + currentPlayer.getPlayerName() + "?");

		message = messages.get(r.nextInt(messages.size() - 1));

		return message;
	}

	/**
	 * Actions that occur when landing on a square depending on the current square
	 * details and player balance.<br>
	 * <br>
	 * 
	 * Includes: <br>
	 * - paying fines;<br>
	 * - removing a player if the fine causes them to drop below 0;<br>
	 * - square purchase validation checks.
	 * 
	 * @param currentSquare the square that has been landed upon.
	 * @param currentPlayer the player taking their turn.
	 * @param players       the players still in the game.
	 * @param squares       the board squares.
	 * @throws InterruptedException
	 */
	private static void landOnSquare(Square currentSquare, Player currentPlayer, ArrayList<Player> players,
			ArrayList<Square> squares) {

		String currentLine = null;
		boolean flag = false;

		// if the square is not the G20/World Earth Day square...
		if (currentSquare.getSquareOwnerID() != -1) {
			// if there is no owner
			if (currentSquare.getSquareOwnerID() == 0) {
				// if they can afford to purchase
				while(!flag) { 
					if (currentPlayer.getPlayerBalance() >= currentSquare.getSquarePurchaseCost()) {
						System.out.println("Would you like to start investing in " + currentSquare.getSquareName()
								+ " for £" + currentSquare.getSquarePurchaseCost() + "?");
						currentLine = sc.next();
						if (currentLine.equalsIgnoreCase("Y") || currentLine.equalsIgnoreCase("Yes")) {
							purchaseSquare(currentSquare, currentPlayer);
							flag = true;
	
						} else if (currentLine.equalsIgnoreCase("n") || currentLine.equalsIgnoreCase("no")) {
							System.out.println("No problem, " + currentPlayer.getPlayerName() + ".");
							flag = true;
						} else {
							System.err.println("Please enter a valid command!");
						}
					
					} else {
						System.out.println("Unfortunately, you can't afford to start investing in "
								+ currentSquare.getSquareName() + " right now. Sorry!");
						flag = true;
					}
				}
				flag = false;
				// if the current player owns the square
			} else if (currentSquare.getSquareOwnerID() == currentPlayer.getPlayerID()) {

				// nothing happens
				System.out.println("You've already added this square to your investment portfolio.");

			} else {

				payFine(currentSquare, currentPlayer, players);

			}
		}
	}

	/**
	 * Charges the developer the necessary amount of money and updates the square
	 * attributes.
	 * 
	 * @param currentSquare the square being developed
	 * @param currentPlayer the developer
	 */
	private static void developSquare(Square currentSquare, Player currentPlayer) {

		// increase square rent amount to next value in the array stored against the
		// current square object...
		currentSquare.setSquareDevStatus(currentSquare.getSquareDevStatus() + 1);
		currentSquare.setSquareRentAmount(currentSquare.getSquareRentValues().get(currentSquare.getSquareDevStatus()));

		if (currentSquare.getSquareDevStatus() == 4) {
			System.out.println(
					"Your continued development has lead to a Breakthrough! Perhaps in the future we will go further, but for now this area of investment has been exhausted.");
		}

		// decrementing player balance by cost to develop current square
		currentPlayer.setPlayerBalance(currentPlayer.getPlayerBalance() - currentSquare.getSquareDevCost());

	}

	/**
	 * Facilitates the initial purchase of a Square when there is no previous owner.
	 * 
	 * @param currentSquare the square to purchase
	 * @param currentPlayer the current player
	 * @throws IllegalArgumentException
	 */
	private static void purchaseSquare(Square currentSquare, Player currentPlayer) throws IllegalArgumentException {

		// set Player ID to the square purchased
		currentSquare.setSquareOwnerID(currentPlayer.getPlayerID());

		// add SquareID to Player Portfolio
		currentPlayer.getPlayerPortfolio().add(currentSquare);

		// subtract cost of square from Player balance
		currentPlayer.setPlayerBalance(currentPlayer.getPlayerBalance() - currentSquare.getSquarePurchaseCost());

		System.out.println("Congratulations, " + currentPlayer.getPlayerName() + ". You have now started investing in "
				+ currentSquare.getSquareName() + "!");

	}

	/**
	 * Class that facilitates fine payments between players.
	 * 
	 * @param currentSquare the square that the current player is on
	 * @param currentPlayer the current player
	 * @param players       the array list of all current players
	 */
	private static void payFine(Square currentSquare, Player currentPlayer, ArrayList<Player> players) {

		System.out.println("You have arrived on " + players.get(currentSquare.getSquareOwnerID() - 1).getPlayerName()
				+ "'s square, " + currentSquare.getSquareName() + "! You felt obliged to contribute £"
				+ currentSquare.getSquareRentAmount() + " to the cause.");

		currentPlayer.setPlayerBalance(currentPlayer.getPlayerBalance() - currentSquare.getSquareRentAmount());
		players.get(currentSquare.getSquareOwnerID() - 1)
				.setPlayerBalance(players.get(currentSquare.getSquareOwnerID() - 1).getPlayerBalance()
						+ currentSquare.getSquareRentAmount());

		System.out.println("Congratulations, " + players.get(currentSquare.getSquareOwnerID() - 1).getPlayerName()
				+ "! Your new balance is £" + players.get(currentSquare.getSquareOwnerID() - 1).getPlayerBalance()
				+ ".");

	}

	/**
	 * Checks if a player owns all squares in a field.
	 * 
	 * @author lukemitchell
	 * @param SquareFieldIDArray
	 * @param currentPlayer
	 */
	private static void checkForEntireFieldOwnership(int[] SquareFieldIDArray, Player currentPlayer) {

		boolean ownsEntireField = false;

		if (!currentPlayer.getPlayerPortfolio().isEmpty()) {
			int ownedSquaresInField = 0;

			for (int i = 0; i < currentPlayer.getPlayerPortfolio().size(); i++) {

				for (int j = 1; j < SquareFieldIDArray.length; j++) {

					if (currentPlayer.getPlayerPortfolio().get(i).getSquareFieldID() == SquareFieldIDArray[0]) {
						ownedSquaresInField++;
						break;

						// iterating through each square in player's portfolio and checking its ID
						// against the ID of the
						// squares belonging to a field

						// if there is a match, ownedSquaresInField is incremented
						// after all iterations, if ownedSquareInField is the same as the number of
						// Squares in that field,
						// the player owns an entire field
					}
				}
			}
			if (ownedSquaresInField == (SquareFieldIDArray.length - 1)) {
				ownsEntireField = true;
			}
		}

		if (ownsEntireField && !currentPlayer.getEntirelyOwnedFields().contains(SquareFieldIDArray[0])) {
			// FieldID is stored at index 0 of the SQUARE_FIELD_IDS int array constants so
			// that it can be used here
			currentPlayer.getEntirelyOwnedFields().add(SquareFieldIDArray[0]);
		}

	}

	/**
	 * Performs different commands depending on the input of the user at the start
	 * of their turn. Validation is performed elsewhere.<br>
	 * <br>
	 * "rules": displays the rules;<br>
	 * "save": saves the game <br>
	 * "exit": exits the game<br>
	 * 
	 * @param currentLine the user inputted command.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void keywordCatcher(String currentLine, Player currentPlayer, ArrayList<Player> players)
			throws IOException, InterruptedException {

		if (currentLine.equalsIgnoreCase("rules") || currentLine.equalsIgnoreCase("r")) {
			displayRules();
		} else if (currentLine.equalsIgnoreCase("save") || currentLine.equalsIgnoreCase("s")) {
			saveLauncher();
			currentPlayer.setTakingTurn(true);
		} else if (currentLine.equalsIgnoreCase("exit") || currentLine.equalsIgnoreCase("e")) {
			System.out.println("Are you sure you wish to exit?");
			currentLine = sc.next();
			if (currentLine.equalsIgnoreCase("yes") || currentLine.equalsIgnoreCase("y")) {

				Player winner = null;
				int winningBalance = 0;

				for (Player p : players) {
					if (p.getPlayerBalance() > winningBalance) {
						winningBalance = p.getPlayerBalance();
						winner = p;
					}
				}

				winner(winner);
				players.remove(winner);
				exitBalances(players);

				System.out.println(
						"Thank you to everyone for so nobly attempting to \"Save Our Planet!\"! We hope you enjoyed playing.");
				System.out.println("Bye!");
				System.exit(0);

			} else if (currentLine.equalsIgnoreCase("no") || currentLine.equalsIgnoreCase("n")) {
				System.out.println("No problem! Carry on then.");
			}
		}

		// followed by main game loop body
	}

	/**
	 * Method stub
	 * 
	 * @throws InterruptedException
	 */
	protected static void displayRules() {

		// display brief list of instructions and commands
		System.out.println("RULES:\n\n");

		System.out.println(
				"Players can answer decisions by entering 'y' (yes) and 'n' (n) when asked to enter messages. Some special commands are available to the player, but ONLY when they are prompted to roll:\n\n");

		System.out.println("- 'exit': exits the game and the current winner will be announced!");
		System.out.println("- 'save': allows you to save the current game with a name of your choice");
		System.out.println("- 'rules': display these commands again!\n");
	}

	/**
	 * Prompts player to name save file and confirm, before saving file with chosen
	 * name
	 * 
	 * @throws IOException
	 */
	private static void saveLauncher() throws IOException, InterruptedException {

		boolean flag = false;

		while (!flag) {

			System.out.println("What would you like to call your save file?");
			String saveFile = sc.next();

			System.out.println("Are you sure you want to call your file '" + saveFile + "'?");
			String currentLine = sc.next();

			if (currentLine.equalsIgnoreCase("yes") || currentLine.equalsIgnoreCase("y")) {

				SaveDriver.saveGame(players, squares, saveFile);
				System.out.println(".");
				Thread.sleep(1000);
				System.out.println("..");
				Thread.sleep(1000);
				System.out.println("...\n");
				System.out.println("Game saved!\n");
				flag = true;

			} else if (currentLine.equalsIgnoreCase("no") || currentLine.equalsIgnoreCase("n")) {
				System.out.println("That's fine!");
			} else {
				System.err.println("Please enter a valid response!");
			}
		}
	}

	/**
	 * Checks player balance and triggers removePlayer()) if necessary.
	 * 
	 * @param currentPlayer
	 * @param players
	 */
	private static void checkPlayerBalance(Player currentPlayer, ArrayList<Player> players) {

		if (currentPlayer.getPlayerBalance() > 0) {
			System.out.println("Your remaining balance is £" + currentPlayer.getPlayerBalance() + ".");
		} else {
			removePlayer(currentPlayer, players);
		}

	}

	/**
	 * Removes player from game and resets necessary values.
	 * 
	 * @param currentPlayer
	 * @param players
	 */
	private static void removePlayer(Player currentPlayer, ArrayList<Player> players) {

		System.out.println(
				"Oh no, you've run out of resources! Bad luck. Thanks for playing, " + currentPlayer.getPlayerName()+".");

		ArrayList<Square> toRemove = new ArrayList<Square>();

		for (Square sq : currentPlayer.getPlayerPortfolio()) {
			sq.setSquareOwnerID(0);
			sq.setSquareDevStatus(0);
			sq.setSquareRentAmount(sq.getSquareRentValues().get(0));
			toRemove.add(sq);
		}

		currentPlayer.getPlayerPortfolio().removeAll(toRemove);
		players.remove(currentPlayer);
	}

	private static void randomFlavourMessage(Square currentSquare, Map<Integer, String> messages0,
			Map<Integer, String> messages1, Map<Integer, String> messages2, Map<Integer, String> messages3,
			Map<Integer, String> messages4) {

		try {
			Thread.sleep(1000);

			if (currentSquare.getSquareDevStatus() == 0) {
				System.out.println("\"" + messages0.get(currentSquare.getSquareNumber()) + "\"");
			} else if (currentSquare.getSquareDevStatus() == 1) {
				System.out.println("\"" + messages1.get(currentSquare.getSquareNumber()) + "\"");
			} else if (currentSquare.getSquareDevStatus() == 2) {
				System.out.println("\"" + messages2.get(currentSquare.getSquareNumber()) + "\"");
			} else if (currentSquare.getSquareDevStatus() == 3) {
				System.out.println("\"" + messages3.get(currentSquare.getSquareNumber()) + "\"");
			} else if (currentSquare.getSquareDevStatus() == 4) {
				System.out.println("\"" + messages4.get(currentSquare.getSquareNumber()) + "\"");
			} else {
				System.out.print("");
			}

		} catch (InterruptedException e) {

		}

	}

	/**
	 * 
	 * @author luke
	 * 
	 *         Allows the player to develop squares and prints to screen information
	 *         relevant to development
	 * 
	 * @param currentPlayer
	 * @param squares
	 * @throws InterruptedException
	 */
	private static void develop(Player currentPlayer, ArrayList<Square> squares) throws InterruptedException {

		boolean finished = false;

		while (!finished) {

			String currentLine;

			List<Square> squaresPlayerCanDevelop = new ArrayList<Square>();

			// extracting squares from player portfolio which belong to an owned field and
			// placing into new list
			for (int i = 0; i < currentPlayer.getPlayerPortfolio().size(); i++) {
				if (currentPlayer.getEntirelyOwnedFields()
						.contains(currentPlayer.getPlayerPortfolio().get(i).getSquareFieldID())
						&& currentPlayer.getPlayerPortfolio().get(i).getSquareDevStatus() < 4 && currentPlayer
								.getPlayerBalance() >= currentPlayer.getPlayerPortfolio().get(i).getSquareDevCost()) {

					squaresPlayerCanDevelop.add(currentPlayer.getPlayerPortfolio().get(i));
				}
			}
			// sorting squares by square number before displaying
			squaresPlayerCanDevelop.sort(Comparator.comparing(Square::getSquareNumber));
			// putting squareIDs into a map to avoid needing to get inside the Square object
			// to get SquareNumbers in for loops
			Map<Integer, Integer> squaresMap = new TreeMap<Integer, Integer>();
			for (int i = 0; i < squaresPlayerCanDevelop.size(); i++) {
				squaresMap.put(i, squaresPlayerCanDevelop.get(i).getSquareNumber());
			}

			if (squaresPlayerCanDevelop.size() > 0) {
				System.out.println("You fully own the following field(s):");
				for (int i = 0; i < currentPlayer.getEntirelyOwnedFields().size(); i++) {
					System.out.println((currentPlayer.getEntirelyOwnedFields().get(i)) + ": "
							+ Square.fieldNames(currentPlayer.getEntirelyOwnedFields().get(i)));
				}
				Thread.sleep(1000);
				System.out.println();

				// Printing information about squares the player can develop
				System.out.println("You can develop these squares:");
				System.out.println(
						"======================================================================================================================================");
				for (int i = 0; i < squaresPlayerCanDevelop.size(); i++) {
					System.out.println("ID: " + i + " --- " + squaresPlayerCanDevelop.get(i).getSquareName()
							+ " | Field: (" + squaresPlayerCanDevelop.get(i).getSquareFieldID() + ") "
							+ Square.fieldNames(squaresPlayerCanDevelop.get(i).getSquareFieldID()) + "\t|"
							+ " Development Level: " + squaresPlayerCanDevelop.get(i).getSquareDevStatus() + "/4 |"
							+ " Cost: " + squaresPlayerCanDevelop.get(i).getSquareDevCost() + "|" + " Current Rent: "
							+ squaresPlayerCanDevelop.get(i).getSquareRentAmount() + "|" + " Rent After Developing: "
							+ squares.get(squaresPlayerCanDevelop.get(i).getSquareNumber()).getSquareRentValues()
									.get((squaresPlayerCanDevelop.get(i).getSquareDevStatus() + 1)));
					if (!(i == (squaresPlayerCanDevelop.size() - 1))) {
						System.out.println(
								"--------------------------------------------------------------------------------------------------------------------------------------");
					}
				}
				System.out.println(
						"======================================================================================================================================");
				System.out.println("\nTo pick a square to develop, enter its ID.");
				System.out.println("If finished developing, type 'done':");
				currentLine = sc.next();
				if (currentLine.equalsIgnoreCase("done")) {

					System.out.println("Exiting development mode...\n---");
					Thread.sleep(500);
					finished = true;
				} else if (squaresMap.containsKey(Integer.parseInt(currentLine))) {
					System.out.println("You are now investing further into \""
							+ squaresPlayerCanDevelop.get(Integer.parseInt(currentLine)).getSquareName() + "\".");

					Thread.sleep(1000);
					developSquare(
							squares.get(squaresPlayerCanDevelop.get(Integer.parseInt(currentLine)).getSquareNumber()),
							currentPlayer);

					System.out.println("\rYour new balance is: £" + currentPlayer.getPlayerBalance() + ".\n");
					Thread.sleep(1000);
				}
			} else {
				
				// finding lowest DevCost to check against player's current balance
				int lowestDevCost = 1000;
				for (int i = 0; i < currentPlayer.getPlayerPortfolio().size(); i++) {
					
					if (currentPlayer.getPlayerPortfolio().get(i).getSquareDevCost() < lowestDevCost) {
						lowestDevCost = currentPlayer.getPlayerPortfolio().get(i).getSquareDevCost();
					}
				}
						
				if (currentPlayer.getPlayerBalance() < lowestDevCost) {
					System.out.println("Unfortunately you now have insufficient funds to continue developing...");
				} else {
					System.out.println("Congratulations! You have exhausted all possible development in each of your current areas of investment!");
				}
				finished = true;

			}
		}
		System.out.println();
	}

	private static void clearScreen() throws InterruptedException {
		// clear the screen after setup
		for (int i = 0; i < 10; i++) {
			System.out.println("");
			Thread.sleep(125);
		}
	}

}
