package saveourplanet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to set up the game board at the start of the main method.
 * 
 *
 */
public class SetUpGame {

	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Square> squares = new ArrayList<Square>();
	private String playerName;
	private int playerCount;
	private String currentLine;
	
	// ArrayLists to contain flavour messages
	protected static final Map<Integer, String> squareMessages0 = new TreeMap<Integer, String>();
	protected static final Map<Integer, String> squareMessages1 = new TreeMap<Integer, String>(); 
	protected static final Map<Integer, String> squareMessages2 = new TreeMap<Integer, String>();
	protected static final Map<Integer, String> squareMessages3 = new TreeMap<Integer, String>();
	protected static final Map<Integer, String> squareMessages4 = new TreeMap<Integer, String>();

	// Constants for player details. Update these to alter future
	// player counts/name lengths.
	protected static final int MIN_PLAYER_COUNT = 2;
	protected static final int MAX_PLAYER_COUNT = 4;
	protected static final int MIN_NAME_LENGTH = 2;
	protected static final int MAX_NAME_LENGTH = 25;
	protected static final int MAX_STARTING_BALANCE = 1000;

	public SetUpGame(boolean workAround) {
		workAround = true;
	}
	
	/**
	 * When instantiating an instance of the SetUpGame() class, the player count and
	 * player names will be asked for. These are stored in an ArrayList<Player> to
	 * be returned as necessary.
	 * @throws InterruptedException 
	 */
	public SetUpGame() throws InterruptedException {

		this.createSquares("DevInfo.csv", squares);
		this.initialisePlayerCount();
		this.initialisePlayerObjects();

	}

	/**
	 * Allows a player to set the initial player count. Business rules: range of
	 * 'MIN_PLAYER_COUNT' to 'MAX_PLAYER_COUNT' inclusive, update these constants as
	 * necessary.
	 * @throws InterruptedException 
	 */
	private void initialisePlayerCount() throws InterruptedException {

		boolean correctPlayerCount = false;

		System.out.println("HOW MANY PLAYERS?");
		System.out.println("-----------------");

		while (!correctPlayerCount) {

			System.out.println("Please enter the number of players (we currently support " + MIN_PLAYER_COUNT + "-"
					+ MAX_PLAYER_COUNT + "):");
			if (Gameplay.sc.hasNextInt()) {
				playerCount = Gameplay.sc.nextInt();
				if (playerCount >= MIN_PLAYER_COUNT && playerCount <= MAX_PLAYER_COUNT) {
					System.out.println("You've selected " + playerCount + " players. Is that correct?");
					currentLine = Gameplay.sc.next();

					if (currentLine.equalsIgnoreCase("Y") || currentLine.equalsIgnoreCase("Yes")) {
						this.setPlayerCount(playerCount);
						System.out.println("Thanks, player count set to " + playerCount + ".");
						Thread.sleep(800);
						System.out.println("-----------------\n");
						correctPlayerCount = true;
					} else if (currentLine.equalsIgnoreCase("N") || currentLine.equalsIgnoreCase("No")) {
						System.out.print("No problem, mistakes happen. ");
					} else {
						System.err.println("Sorry, we didn't understand that answer! ");
					}
				} else {
					if (playerCount > MAX_PLAYER_COUNT) {
						System.err.println("Sorry, we only support up to " + MAX_PLAYER_COUNT + " players!");

					} else {
						System.err.println("Sorry, we need at least " + MIN_PLAYER_COUNT + " players!");
					}
				}

			} else {
				System.err.println("Sorry, we didn't understand that. Try again!");
				Gameplay.sc.next();

			}
		}
		
		

	}

	/**
	 * Allows the user to set the names of each player. Passes its input to new
	 * Player objects.
	 * @throws InterruptedException 
	 */
	private void initialisePlayerObjects() throws InterruptedException {

		int IDCount = 1;
		boolean correctName = false;

		ArrayList<Player> players = new ArrayList<Player>();

		System.out.println("WHAT ARE YOUR NAMES?");
		System.out.println("-----------------");

		for (int loop = 1; loop < playerCount + 1; loop++) {

			String OS = System.getProperty("os.name").toLowerCase();
			Player temporary;
			correctName = false;

			while (!correctName) {

				System.out.println("Please enter a name for player number " + loop + " (" + MIN_NAME_LENGTH + "-"
						+ MAX_NAME_LENGTH + " characters):");

				Gameplay.sc.reset();

				// Checking OS to determine what to delimit
				if ((OS.indexOf("win") >= 0)) {
					Gameplay.sc.useDelimiter("\r\n");
				} else {
					Gameplay.sc.useDelimiter("\n");
				}

				playerName = Gameplay.sc.next();
				playerName = playerName.trim();

				for (Player p : players) {

					if (p.getPlayerName().equalsIgnoreCase(playerName) && !players.isEmpty()) {
						System.err.println("Sorry, we already have a player with that name. ");
						playerName = "";
					}

				}

				if (playerName.length() >= MIN_NAME_LENGTH && playerName.length() <= MAX_NAME_LENGTH) {
					Gameplay.sc.reset();
					System.out.println("You entered \"" + playerName + "\" for player " + loop + ". Is that correct?");
					currentLine = Gameplay.sc.next();

					if (currentLine.equalsIgnoreCase("Y") || currentLine.equalsIgnoreCase("Yes")) {

						ArrayList<Square> portfolio = new ArrayList<Square>();
						ArrayList<Integer> ownedFields = new ArrayList<Integer>();
						temporary = new Player(playerName, IDCount, MAX_STARTING_BALANCE, Square.SQUARE_NUMBER_MIN,
								portfolio, ownedFields);
						players.add(temporary);

						System.out.println(
								"Thanks, " + playerName + " has been added to the game! Nice to have you with us.\n");
						IDCount++;
						correctName = true;
						Thread.sleep(500);

					} else if (currentLine.equalsIgnoreCase("N") || currentLine.equalsIgnoreCase("no")) {
						System.out.println("No problem, mistakes happen. ");
					} else {
						System.err.println("Sorry, I didn't understand that response...! ");
					}

				} else {
					if (playerName.length() > MAX_NAME_LENGTH && !playerName.isEmpty()) {
						System.err.println("Sorry, that name is too long! ");
					}
					if (playerName.length() < MIN_NAME_LENGTH && !playerName.isEmpty()) {
						System.err.println("Sorry, we need a longer name! ");
					}
				}
			}

			Gameplay.sc.reset();

		}

		this.setPlayersArray(players);
		System.out.println("All player names have been set:");

		for (int x = 0; x < this.getPlayersArray().size(); x++) {
			System.out.println("Player " + (x + 1) + ":\t\t" + this.getPlayersArray().get(x).getPlayerName());
		}
		
		Thread.sleep(1000);
		System.out.println("\nYou've each been given £" + MAX_STARTING_BALANCE + " to start.");
		Thread.sleep(1500);
		System.out.println("\nEnjoy the game!");
		System.out.println("-----------------\n");
		Thread.sleep(2000);
	}

	/**
	 * Initiates the game squares from the .CSV file specified by fileName. If this fails, squares are set to the hard-coded default game values.
	 * @param fileName the name of the csv file to read
	 * @param squares the ArrayList<Square> to store the Squares.
	 */
	private void createSquares(String fileName, ArrayList<Square> squares) {

		File file = new File(fileName);

		String line;

		//attempt reading of the file
		try {

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			// ignoring headers
			br.readLine();
			br.readLine();

			System.out.print("Initialising the game board.");
			Thread.sleep(400);
			System.out.print(".");
			Thread.sleep(400);
			System.out.print(".\n");
			Thread.sleep(400);
			while ((line = br.readLine()) != null) {

				String[] devDetails = line.split(",");

				ArrayList<Integer> rentsq = new ArrayList<Integer>();

				rentsq.add(Integer.parseInt(devDetails[6]));
				rentsq.add(Integer.parseInt(devDetails[8]));
				rentsq.add(Integer.parseInt(devDetails[9]));
				rentsq.add(Integer.parseInt(devDetails[10]));
				rentsq.add(Integer.parseInt(devDetails[11]));

				Square sq = new Square(devDetails[0], Integer.parseInt(devDetails[1]), Integer.parseInt(devDetails[2]),
						Integer.parseInt(devDetails[3]), Integer.parseInt(devDetails[4]),
						Integer.parseInt(devDetails[5]), Integer.parseInt(devDetails[7]), rentsq.get(0), rentsq);

				squares.add(sq);

			}

			System.out.println("Finished!");
			Thread.sleep(1000);
			System.out.println();

			br.close();
			fr.close();
			
			// otherwise use the defaults
		} catch (Exception e) {

			System.err.println("Error reading in file. Default values will be used.");

			ArrayList<Integer> rents1 = new ArrayList<Integer>(); 
			rents1.add(0);
			rents1.add(0);
			rents1.add(0);
			rents1.add(0);
			rents1.add(0);

			ArrayList<Integer> rents2 = new ArrayList<Integer>();
			rents2.add(5);
			rents2.add(25);
			rents2.add(75);
			rents2.add(225);
			rents2.add(300);

			ArrayList<Integer> rents3 = new ArrayList<Integer>();
			rents3.add(10);
			rents3.add(40);
			rents3.add(125);
			rents3.add(375);
			rents3.add(500);

			ArrayList<Integer> rents4 = new ArrayList<Integer>();
			rents4.add(10);
			rents4.add(40);
			rents4.add(125);
			rents4.add(375);
			rents4.add(500);

			ArrayList<Integer> rents5 = new ArrayList<Integer>();
			rents5.add(15);
			rents5.add(65);
			rents5.add(200);
			rents5.add(600);
			rents5.add(800);

			ArrayList<Integer> rents6 = new ArrayList<Integer>();
			rents6.add(15);
			rents6.add(65);
			rents6.add(200);
			rents6.add(600);
			rents6.add(800);

			ArrayList<Integer> rents7 = new ArrayList<Integer>();
			rents7.add(0);
			rents1.add(0);
			rents7.add(0);
			rents7.add(0);
			rents7.add(0);

			ArrayList<Integer> rents8 = new ArrayList<Integer>();
			rents8.add(20);
			rents8.add(90);
			rents8.add(275);
			rents8.add(825);
			rents8.add(1100);

			ArrayList<Integer> rents9 = new ArrayList<Integer>();
			rents9.add(25);
			rents9.add(115);
			rents9.add(350);
			rents9.add(1050);
			rents9.add(1400);

			ArrayList<Integer> rents10 = new ArrayList<Integer>();
			rents10.add(25);
			rents10.add(115);
			rents10.add(350);
			rents10.add(1050);
			rents10.add(1400);

			ArrayList<Integer> rents11 = new ArrayList<Integer>();
			rents11.add(30);
			rents11.add(145);
			rents11.add(440);
			rents11.add(1315);
			rents11.add(1750);

			ArrayList<Integer> rents12 = new ArrayList<Integer>();
			rents12.add(35);
			rents12.add(165);
			rents12.add(500);
			rents12.add(1500);
			rents12.add(2000);

			Square sq1 = new Square("Earth Day Parade", 
					-1, 0, 0, 0, 0, 0, rents1.get(0), rents1);
			Square sq2 = new Square("B.I.N.S HQ", 
					0, 1, 1, 60, 45, 5, rents2.get(0), rents2);
			Square sq3 = new Square("Automated Recycling", 
					0, 2, 1, 100, 75, 10, rents3.get(0), rents3);
			Square sq4 = new Square("Oil Spill Relief Effort", 
					0, 3, 2, 100, 75, 10, rents4.get(0), rents4);
			Square sq5 = new Square("Nernobyl Relief Effort", 
					0, 4, 2, 160, 120, 15, rents5.get(0), rents5);
			Square sq6 = new Square("BudLite Virus Relief Effort", 
					0, 5, 2, 160, 120, 15, rents6.get(0), rents6);
			Square sq7 = new Square("G20 Summit", 
					-1, 6, 0, 0, 0, 0, rents7.get(0), rents7);
			Square sq8 = new Square("Quantum Computing Research", 
					0, 7, 3, 220, 165, 20, rents8.get(0), rents8);
			Square sq9 = new Square("MESLA HQ", 
					0, 8, 3, 280, 210, 25, rents9.get(0), rents9);
			Square sq10 = new Square("BioFoodz HQ", 
					0, 9, 3, 280, 210, 25, rents10.get(0), rents10);
			Square sq11 = new Square("Ceres Mining Company HQ", 
					0, 10, 4, 350, 265, 30, rents11.get(0), rents11);
			Square sq12 = new Square("DigiBees HQ", 
					0, 11, 4, 400, 300, 35, rents12.get(0), rents12);

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

		}

		this.squares = squares;

	}
	
	/**
	 * 
	 * Sets flavour messages for each square to be displayed upon landing
	 * 
	 */
	public void setFlavourMessages(Map<Integer, String> squareMessages0, Map<Integer, String> squareMessages1, Map<Integer, String> squareMessages2, Map<Integer, String> squareMessages3, Map<Integer, String> squareMessages4 ) {
				
				// Square 0 messages
				squareMessages0.put(0, "Happy Earth Day! The most conscientious citizens of the world are busy making signs and picketing whilst the rest participate in the slow, ignoble murder of the planet.");
				
				// Square 1 messages
				squareMessages0.put(1, "Bryson Industrial National Services HQ is entirely forgettable. Not the most beautiful of views... A fairly miserable stench.");
				squareMessages1.put(1, "Wow, these new electric crushers are really crushing all that rubbish. Shame it's all going in the landfill...");
				squareMessages2.put(1, "3 of the rubbish sorters have been laid off due to lack of non recyclables. What a success!");
				squareMessages3.put(1, "The locals have been complaining about the overly efficient collection service.");
				squareMessages4.put(1, "'As the world leader in Egg Carton Reycling Management Facilitation, (ECRMF), we can truly say all the recycling eggs are in our basket'.");
				
				// Square 2 messages
				squareMessages0.put(2, "The newly-created envirobots seem to be struggling to distinguish between cardboard and leftover Biofoodz...");
				squareMessages1.put(2, "The envirobots have become even cheaper thanks to safe and correct internal fusion reactors. Wonderful news for all!");
				squareMessages2.put(2, "Wow, that bin lorry is driving itself! They even taught it how to double-park.");
				squareMessages3.put(2, "The envirobots have become even cheaper thanks to safe and correct internal fusion reactors. Wonderful news for all!");
				squareMessages4.put(2, "The envirobots have become sentient... unfortunately they've unionised for a living wage to avoid turning against us!");
				
				// Square 3 messages
				squareMessages0.put(3, "The SS Spinkle oil tanker has spilled its maximum load into the Indian Ocean as the result of an impact with a rogue stalagmite. Blue whale calls have ceased to pass through the oily curtain ...");
				squareMessages1.put(3, "A group of local mothers have donated their Saturdays to help clean up the Bengalese coastline. Sea turtles have been seen in the bay once more, but only in limited numbers ...");
				squareMessages2.put(3, "Reports have come in that a BBC program presented by a 'D. Attenborough' has singlehandedly raised enough money to invest in 342 new hydro-powered Brita sea hoovers. We're getting there ...");
				squareMessages3.put(3, "Biofoodz have found a way to convert the remaining leakage into plankton, creating a socially conscious wave of eco-orcas. The oil company have gladly taken credit for their donations to the ocean's health.");
				squareMessages4.put(3, "A small batch of the eco-plankton waste products has been proven to reverse the effect of carbon emissions. Biofoodz have begun producing eco-plankton on a global scale, selling them in huge quantities to countries wanting to improve their carbon score without upsetting their industrial lobbyists.");
				
				// Square 4 messages
				squareMessages0.put(4, "It has been 2 generations since Nernobyl went into melt down. We need to find a solution before the squirrels grow any more eyes!");
				squareMessages1.put(4, "International aid has funded a containment team to enter the exclusion zone to survey the extent of the radiation and put together the best plan of action.");
				squareMessages2.put(4, "Advancement in nanotechnology and radiation scrubbing has allowed Nernobyl's radiation to be exploited as an energy source!");
				squareMessages3.put(4, "With further international funding, the radiation scrubbing has almost been completed! Wildlife is expected to reclaim the exclusion zone any moment now.");
				squareMessages4.put(4, "3 Eyed Squirrel sightings are no longer being reported. As nature flourishes, a world class Ecological Museum has been opened to showcase the amazing natural beauty of the one time disaster site.");
				
				// Square 5 messages
				squareMessages0.put(5, "BUDVID-19 is sweeping the planet. The death toll is increasing exponentially and causing widespread panic. Help is needed to contain the infection and pacify global relations.");
				squareMessages1.put(5, "Research into vaccination has accelerated thanks to global aid. The spread is yet to be contained however, and much more work still remains to be done.");
				squareMessages2.put(5, "The death toll has slowed down and panic is de-escalating thanks to continued support from participant states - but the number of infections continues to rise. The vaccination development is still underway and far from complete. Time is running out!");
				squareMessages3.put(5, "Containment has been prioritised and global awareness is at an all-time high. New infections has been reduced to a minimum but no vaccine remains.");
				squareMessages4.put(5, "A huge breakthrough has been discovered by the World Health Organisation, and a real vaccine can be put into production with one final push of investment. We are so close.");
				
				// Square 6 messages
				squareMessages0.put(6, "The leaders of the 20 most powerful countries have gathered for their annual attempt to solve the world's problems! Surely this year will be the one they figure it all out.\nNothing to invest in here, enjoy the discussion!");
								
				// Square 7 messages
				squareMessages0.put(7, "Quantum computing will soon be essential to our survival, and the survival of the planet. The future that we were promised is slipping from our grasp unless we can introduce radical new ways of thinking.");
				squareMessages1.put(7, "As it stands, quantum computing has been considered far too costly to be a primary focus of global research. Future investment will increase awareness of further research interests and push quantum technology to the forefront of the tech industry. There are many challenges ahead so we will need all the help we can get!");
				squareMessages2.put(7, "Quantum processor sizes are starting to be refined to a manageable size, but they are still far from being used domestically. New ideas are springing up in tandem with their advancement and the science community is now very excited");
				squareMessages3.put(7, "After much time and many resources, the distant prospect of domestic quantum computing is becoming a reality. Scientists have come very close to creating the first General Artificial Intelligence, and with that will come solutions to many of humankind�s problems. The ambient temperature of the processor cores continues to be the primary concern, and the tech world is banding together to find the best solution.");
				squareMessages4.put(7, "A new element, Environmentium, has been discovered in space, which has the exact properties we need to maintain appropriate processor temperatures. The domestication of quantum computing has become a reality and soon we will see many huge leaps of innovation on a global scale.");
				
				// Square 8 messages
				squareMessages0.put(8, "MESLA is desperately searching for investors to fund its electrification of all transport.");
				squareMessages1.put(8, "Melon Musk is enticing engineers and software developers from around the world wih promises of fame and glory.");
				squareMessages2.put(8, "Despite some controversial Tweets, Melon Musk is driving MESLA forward as a global leader in electric vehicles.");
				squareMessages3.put(8, "Every young professional dreams of driving a MESLA, autonomous driving technology is leading to the end of driving tests.");
				squareMessages4.put(8, "After opening hundreds of GigaFactories, MESLA has come to dominate the market. The roads are silent, nobody knows how to drive anymore and transport is carbon neutral! ");
				
				// Square 9 messages
				squareMessages0.put(9, "Business is slow as Biofoodz don't have a lot of taste, people would rather eat paper.");
				squareMessages1.put(9, "Food scientists at Biofoodz are working hard to develop recognisable flavours.");
				squareMessages2.put(9, "Fast food chains are quickly adopting BioFoodz as prices plummet due to streamlined production techniques.");
				squareMessages3.put(9, "Gordon Ramsey has taken over operations and business is ****ing booming!");
				squareMessages4.put(9, "People can't tell the difference between Biofoodz and natural products anymore, the last child to taste real meat has already been born.");
				
				// Square 10 messages
				squareMessages0.put(10, "The idea of moving all mining off-world is just a twinkle in the eye of the 'Ceres Mining Company'.");
				squareMessages1.put(10, "Increased funding has allowed for the first prospecting satellites to be deployed around the solar system.");
				squareMessages2.put(10, "As strip mining on earth continues to ravage the natural world, the first shipments of ore from space begin to arrive.");
				squareMessages3.put(10, "The huge quantities of rare earth metals being mined and shipped to earth ae causing prices to collapse, but allowing huge leaps in technology.");
				squareMessages4.put(10, "Global laws banning mining on Earth are passed as the Ceres Mining Company floods the markets with cheap extra-terestrial minerals. Nature is already starting to recover!");
				
				// Square 11 messages
				squareMessages0.put(11, "Mechanical bees are the final hope for restoring our rapidly depleting food supplies. The world must unite to overcome this.");
				squareMessages1.put(11, "Recent technological breakthroughs have pushed nanotechnology to the mainstream, so real progress is being made with the first fleet of DigiBees. The human race is counting on it.");
				squareMessages2.put(11, "The first fleet of DigiBees has expanded and is being tested in controlled environments. The final product is in sight, but there still remains a lot of work to be done.");
				squareMessages3.put(11, "Global efforts have ensured the DigiBee program is of highest priority, as lack of food pushes civil unrest to a critical point. Everyone who can help, must help.");
				squareMessages4.put(11, "The DigiBee program is a success. Huge swathes of land have been reclaimed and contained to help repopulate crops around the world, with many nations already benefitting from their work. Continued support is vital to the final success of the human race.");

	}

	/**
	 * Returns the ArrayList<Square>
	 * @return the squares
	 */
	public ArrayList<Square> getSquares() {
		return this.squares;
	}

	/**
	 * Class to set the player count.
	 * 
	 * @param playerCount the number of players
	 */
	public void setPlayerCount(int playerCount) throws IllegalArgumentException {
		try {
			if (playerCount >= MIN_PLAYER_COUNT && playerCount <= MAX_PLAYER_COUNT) {
				this.playerCount = playerCount;
			}
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Invalid Player count");
		}

	}

	/**
	 * Returns the player count set in setPlayerCount().
	 * 
	 * @return number of players
	 */
	public int getPlayerCount() {
		return this.playerCount;
	}

	/**
	 * Sets the Player object array based on information provided in
	 * intitialisePlayerObjects
	 * 
	 * @param players
	 */
	public void setPlayersArray(ArrayList<Player> players) throws IllegalArgumentException {
		if (players.size() >= MIN_PLAYER_COUNT && players.size() <= MAX_PLAYER_COUNT) {
			this.players = players;
		} else {
			throw new IllegalArgumentException("Invalid number of players");
		}
	}

	/**
	 * Returns the ArrayList of Player objects.
	 * 
	 * @return player objects
	 */
	public ArrayList<Player> getPlayersArray() {

		return this.players;
	}

	/**
	 * @return the squareMessages0
	 */
	public Map<Integer, String> getSquareMessages0() {
		return squareMessages0;
	}

	/**
	 * @return the squareMessages1
	 */
	public Map<Integer, String> getSquareMessages1() {
		return squareMessages1;
	}

	/**
	 * @return the squareMessages2
	 */
	public Map<Integer, String> getSquareMessages2() {
		return squareMessages2;
	}
	
	/**
	 * @return the squareMessages2
	 */
	public Map<Integer, String> getSquareMessages3() {
		return squareMessages3;
	}

	/**
	 * @return the squareMessages2
	 */
	public Map<Integer, String> getSquareMessages4() {
		return squareMessages4;
	}


}
