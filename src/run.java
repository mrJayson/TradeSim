import java.io.IOException;


public class run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SircaReader SR = new SircaReader();
		try {
			SR.chooseFile("C:/Users/Jason/workspace/TradeSim/Sample.csv");
			String line;
			SR.readLine();//get rid of the first line\theader line
			int counter = 2;
			while ((line = SR.readLine()) != null) {
				Order o = SignalGenerator.signalGenerator(line);
				System.out.print(counter++ + "\t");
				System.out.print(o.ID + "\t");
				System.out.print(o.timeStamp + "\t");
				System.out.print(o.recordType + "\t");
				System.out.print(o.bidAsk + "\t");
				System.out.print(o.price + "\t");
				System.out.println(o.volume);
			}
			SR.closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}

}
