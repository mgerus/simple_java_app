package main;

import writer.*;

import java.io.IOException;
import apiCommunication.*;

public class WriteNewsFromApi {

	public static void main(String[] args) throws IOException {
		WriteToFile writer = new WriteToFile();
		ApiCommunication communicator = new ApiCommunication();		
		String fileName = UserCommunicator.getOutputFileName();
		String resultsToBeWritten = communicator.getArticlesFromApiInStrings();
		writer.write(fileName, resultsToBeWritten);
	}
}