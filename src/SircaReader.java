import java.io.*;


public class SircaReader {

	BufferedReader CSV;

	public void chooseFile (String path) throws FileNotFoundException {
		CSV = new BufferedReader(new FileReader(path));
	}

	public String[] ReadFile () throws IOException {
		String line;
		String CSVArray[] = new String[5];
		if (CSV == null) {
			System.out.println("NULL");
		}
		while ((line = CSV.readLine()) != null) {
			String[] CSVData = line.split(",");
			for (String item:CSVData) { 
			      System.out.print(item + "\t"); 
			   }
			System.out.println();
		}
		
		return CSVArray;
	}
	
	public void closeFile() throws IOException {
		CSV.close();
	}
}
