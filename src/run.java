import java.io.IOException;


public class run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//simulates the function when the run button is pressed 
		
		SircaReader SR = new SircaReader();
		try {
			SR.chooseFile("C:/Users/Jason/workspace/TradeSim/Sample.csv");
			String line;
			SR.readLine();//get rid of the first line\theader line
			int counter = 2;
			OrderBook ob = new OrderBook();
			while ((line = SR.readLine()) != null) {
			//for (int i = 0; i < 100000; i++) {
				line = SR.readLine();
				Order o = SignalGenerator.generateSignal(line);
				
				ob.processOrder(o);
				
				ob.display();

				//System.out.print(counter++ + "\t");
				//System.out.print(o.ID + "\t");
				//System.out.print(o.timeStamp + "\t");
				//System.out.print(o.recordType + "\t");
				//System.out.print(o.bidAsk + "\t");
				//System.out.print(o.price + "\t");
				//System.out.println(o.volume);
			}
			SR.closeFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
	


}
