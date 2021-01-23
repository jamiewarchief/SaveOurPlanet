package saveourplanet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SaveDriver implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ArrayList<Player> players;
	private static ArrayList<Square> squares;
	static String currentLine, saveName, saveNameList;

	/**
	 * Will serialize current state of game, namely the Player and Square classes,
	 * which are passed as parameter arguments. Saves to a .ser file in the local
	 * directory with the file extension passed as parameter argument.
	 * 
	 * @param players
	 * @param squares
	 * @param file
	 * @throws IOException
	 */
	public static void saveGame(ArrayList<Player> players, ArrayList<Square> squares, String saveName)
			throws IOException {

		// Create 2 streams to serialize the object and save to file
		FileOutputStream outputStream = new FileOutputStream(saveName + ".ser");
		ObjectOutputStream os = new ObjectOutputStream(outputStream);
		writeSaveName(saveName);

		// Write objects to file
		os.writeObject(players);
		os.writeObject(squares);
		os.close();

	}

	/**
	 * Will deserialize a save file and output the state of play
	 * 
	 * @param file
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	public static void loadGame(String file) throws IOException, ClassNotFoundException, InterruptedException {

		// Create 2 streams to deserialize the object and restore its state
		FileInputStream inputStream = new FileInputStream(file);
		ObjectInputStream os = new ObjectInputStream(inputStream);

		// Read the objects and cast them back to respective types
		players = (ArrayList<Player>) os.readObject();
		squares = (ArrayList<Square>) os.readObject();
		os.close();

		System.out.println("Successfully loaded!");
		Thread.sleep(500);

		System.out.println("___________________________________________________________");
		System.out.println("The current state of play is as follows:");
		System.out.println("===========================================================\n");

		// needs tidied up and updated with more relevant info
		for (Player player : players) {
			String investments = "";
			System.out.println(player.getPlayerName() + " has a balance of £" + player.getPlayerBalance() + ".");
			System.out.println("They are on " + squares.get(player.getCurrentSquare()).getSquareName() + ".\n");
			Thread.sleep(1500);

			for (Square square : squares) {
				if (player.getPlayerID() == square.getSquareOwnerID()) {
					investments = investments.concat(square.getSquareName() + ", ");
				}

			}
			if (investments.length() > 1) {
				System.out.println("Their investments are as follows:");
				investments = investments.substring(0, investments.length() - 2).concat(".");
				System.out.println(investments);
			} else {
				System.out.println("They have no investments at this time.");
			}
			Thread.sleep(3000);
			System.out.println("");

		}

		System.out.println("===========================================================\n");

	}

	/**
	 * Stores new save file names to be displayed when player asks to load. Newest save appears first.
	 * @param newSave name of the new save file
	 */
	protected static void writeSaveName(String newSave) {
		File file = new File("savelist.txt");
		String currentLine = "";
		String result = "";

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.err.println("Unable to save new save name(CREATINGNEWFILE)");
			}
		}

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ((currentLine = br.readLine()) != null) {
				result = result + "\n" + currentLine;
			}

			result = newSave + result;

			br.close();
			fr.close();

			file.delete();

			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(result);

			bw.close();
			fw.close();
		} catch (IOException e) {
			System.err.println("Error with storing save name to text file");
		}

	}

	/**
	 * Reads existing save files from the savelist.txt. Newest saves are displayed first.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	protected static void readSaveNames() throws IOException, InterruptedException {
		File file = new File("savelist.txt");
		String saveNames = "";
		final int SAVES_TO_DISPLAY_MAX = 100;
		String currentLine;

		if (!file.exists()) {
			System.err.println("Couldn't find save list!");
		}

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			for (int i = 0; i < SAVES_TO_DISPLAY_MAX; i++) {
				currentLine = br.readLine();

				saveNames = saveNames.concat((currentLine + ", "));

				if (saveNames.contentEquals("null, ")) {
					saveNames = "";
					break;
				}

			}

			if (saveNames.equals("")) {
				System.err.println("No saves to display!");
				Thread.sleep(1000);

			} else {
				saveNames = saveNames.substring(0, saveNames.indexOf(", null, "));

			}

			br.close();
			fr.close();

		} catch (FileNotFoundException e) {

		}

		setSaveNames(saveNames);

	}

	protected static void setSaveNames(String saveNames) {
		saveNameList = saveNames;
	}

	protected static String getSaveNames() {
		return saveNameList;
	}

	protected static ArrayList<Player> getPlayerState() {
		return players;
	}

	protected static ArrayList<Square> getSquareState() { 
		return squares;
	}

}