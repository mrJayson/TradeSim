package app;

import java.io.IOException;

import app.strategies.Strategy;

public class SignalGenerator {

	private SircaReader SR = new SircaReader();
	private Strategy s = null;

	public Order generateSignal() throws IOException {
		//first gets strategy's signal
		//if strategy returns nothing, get CSV signal
		Order o = generateStrategySignal();
		if (o == null) {
			String line = SR.readLine();
			o = generateCSVSignal(line);
		}
		return o;
	}

	private Order generateStrategySignal() {
		return s.outputSignal();

	}

	private Order generateCSVSignal(String line) {
		//change an order from CSV format to Order format
		String[] entry = line.split(",");
		Order o = Factory.order(getID(entry), getTimeStamp(entry),
				getRecordType(entry), getBidAsk(entry),
				getPrice(entry), getVolume(entry), getQualifier(entry));
		
		
		
		//TODO filter out trade signals here, dont need their trades, make our own
		return o;
	}

	private long getID(String[] entry) {
		//ID depends on bid/Ask value
		long ID;
		if (getBidAsk(entry).equals("B")) {
			//wants to buy
			ID = Long.valueOf(entry[10]);
		}
		else if (getBidAsk(entry).equals("A")) {
			//wants to sell
			ID = Long.valueOf(entry[11]);
		} else {
			ID = 0;
		}
		return ID;
	}

	private String getTimeStamp(String[] entry) {
		//timestamp independent
		return entry[2];
	}

	private String getRecordType(String[] entry) {
		//6 values
		//ENTER,DELETE,AMEND,TRADE,OFFTR,CANCEL_TRADE
		return entry[3];
	}

	private float getPrice(String[] entry) {
		//depends on recordType
		//exists if recordType == ENTER,TRADE,AMEND,OFFTR,CANCEL_TRADE
		//not exist only if recordType == DELETE
		float price;
		if (getRecordType(entry).equals("DELETE")) {
			price = 0;
		} else {
			price = Float.valueOf(entry[4]);
		}
		return price;
	}

	private int getVolume(String[] entry) {
		//depends on recordType
		//exists if recordType == ENTER,TRADE,AMEND,OFFTR,CANCEL_TRADE
		//not exist only if recordType == DELETE
		int volume;
		if (getRecordType(entry).equals("DELETE")) {
			volume = 0;
		} else {
			volume = Integer.valueOf(entry[5]);
		}
		return volume;
	}

	private String getBidAsk(String[] entry) {
		//depends on RecordType
		//exists if recordType == ENTER,DELETE,AMEND
		//not exists if recordType == TRADE,CANCEL_TRADE,OFFTR
		String bidAsk;
		if (getRecordType(entry).equals("ENTER") ||
				getRecordType(entry).equals("DELETE") ||
				getRecordType(entry).equals("AMEND")) {
			bidAsk = entry[12];
		} else {
			bidAsk = "";
		}
		return bidAsk;
	}

	private String getQualifier(String[] entry) {
		//independent
		return entry[8];
	}

	public void chooseCSV(String filepath) throws IOException {
		SR.chooseFile(filepath);
		SR.readLine();
	}

	public void closeCSV() throws IOException {
		SR.closeFile();

	}

	public void selectStrategy(Strategy s) {
		this.s = s;
	}
}
