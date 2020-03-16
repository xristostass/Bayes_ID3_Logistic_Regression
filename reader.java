//Marianthi Mindrinou 3150110
//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170

import java.io.*;

public class reader {

	public static String loadFile(String data) {
		int counter = 0;
		File f = null;
		BufferedReader reader = null;
		// this variable is used to contain the lines read
		String line = " ";
		// this variable contains is used to contain all the lines of the file
		// data
		String examples = "";

		try {
			// we open the file
			f = new File(data);
		} catch (NullPointerException e) {
			System.err.println("Error opening file!");
		}

		try {
			// we create a buffered reader
			reader = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			System.err.println("Error opening file!");
		}

		try {
			// here we start reading the file and concat them to the variable
			// statements
			counter++;
			line = reader.readLine();
			while (line != null) {
				examples += line;
				line = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error reading line " + counter + ".");
		}
		// the output is trimmed and converted to uppercase
		examples = examples.trim().toUpperCase();
		return examples;
	}

}
