package app;

public class run {
	public static void main(String[] args) {
		//simulates the function when the run button is pressed 
		SircaReader SR = new SircaReader();
		try {
			SR.chooseFile("C:/Users/Jason/workspace/TradeSim/Sample.csv");
			String line;
			SR.readLine();//get rid of the first line, header line
			//one engine per company
			//currently merged orderbooks and engine together
			//add more Engines to simulate multiple companies
			TradeEngine TE = new TradeEngine();
			//running through the historic data
			for (int i = 0; i < 2000; i++) {
				SR.readLine();
			}
			while ((line = SR.readLine()) != null) {
				//generates an order from a CSV line
				//to be processed in the trade engine
				//and currently displays text of orderbook
				Order o = SignalGenerator.generateSignal(line);
				TE.processOrder(o);
				TE.display();
			}
			SR.closeFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
