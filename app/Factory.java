package app;

import app.strategies.*;

public class Factory {
	//factory pattern, all object creations should occur through here
	
	private SignalGenerator SG;
	private OrderBook OB;
	private TradeEngine TE;
	
	public SignalGenerator signalGenerator() {
		if (SG == null) {
			SG = new SignalGenerator();
		}
		return SG; 
	}
	
	public OrderBook orderBook() {
		if (OB == null) {
			OB = new OrderBook();
		}
		return OB;
	}
	
	public TradeEngine tradeEngine() {
		if (TE == null) {
			TE = new TradeEngine(orderBook());
		}
		return TE;
	}
	
	public Strategy BasicStrategy() {
		return new BasicStrategy();
	}

	public static Order order(long id, String timeStamp, String recordType,
			String bidAsk, float price, int volume, String qualifier) {
		return new Order(id,timeStamp,recordType,bidAsk,price,volume,qualifier);
	}

}
