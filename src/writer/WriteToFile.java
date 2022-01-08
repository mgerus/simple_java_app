package writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteToFile {
	final String homeDirectory;

	public WriteToFile(String homeDirectory) {
		this.homeDirectory = homeDirectory;
	}
	
	public WriteToFile() {
		this.homeDirectory = "";
	}

	public void unsafeWrite(String fileName, String content) throws IOException {
		FileWriter fileWriter = new FileWriter(fileName);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(content);//printf fails for some reason
		printWriter.close();
	}

	public void write(String fileName, String content) {
		try {
			unsafeWrite(fileName, content);
		} catch (Exception e) {
			System.out.printf("Writing to file %s failed\n", fileName);
			e.printStackTrace();
		}
	}

}
