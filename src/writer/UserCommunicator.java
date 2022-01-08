package writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserCommunicator {

	public static String getOutputFileName() {

		System.out.println("Please provide a directory and a name of a file for the response to be written to:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String fileName= "C:\\defaultName.txt";
		try {
			fileName = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}
}
