package app;
import java.io.*;


public class SircaReader {

	private BufferedReader CSV;

	public void chooseFile (String path) throws FileNotFoundException {
		CSV = new BufferedReader(new FileReader(path));
	}

	public String readLine () throws IOException {
		return CSV.readLine();
	}
	
	public void closeFile() throws IOException {
		CSV.close();
	}
}
