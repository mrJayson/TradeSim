package app;

public class run {
	public static void main(String[] args) {
		//controller function
		//simulates the function when the run button is pressed 
		Factory f = new Factory();
		SignalGenerator SG = f.signalGenerator();
		//signal generator contains functionality to read CSV
		OrderBook book = f.orderBook();
		TradeEngine TE = f.tradeEngine();
		try {
			
			String filepath = System.getProperty("user.dir") + "/Sample.csv";
			SG.chooseCSV(filepath);
			SG.selectStrategy(f.BasicStrategy());
			//TODO add method to choose strategy type
			
			//running through the historic data
			
			Order o;
			while ((o = SG.generateSignal()) != null) {
				//generates an order from either the strategy or CSV
				//process it in orderbook
				//tradeEngine processes orderbook
				//display
				
				book.processOrder(o);
				TE.trade();
				book.display();
			}
			SG.closeCSV();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
