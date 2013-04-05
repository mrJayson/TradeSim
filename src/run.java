import java.io.IOException;


public class run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SircaReader SR = new SircaReader();
		try {
			SR.chooseFile("C:/Users/Jason/workspace/TradeSim/Sample.csv");
			SR.ReadFile();
			SR.closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
