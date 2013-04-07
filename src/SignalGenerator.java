
public class SignalGenerator {

	public static Order signalGenerator(String line) {

		String[] entry = line.split(",");
		Order o = new Order(getID(entry), getTimeStamp(entry),
				getRecordType(entry), getBidAsk(entry),
				getPrice(entry), getVolume(entry), getQualifier(entry));
		return o;
	}

	private static long getID(String[] entry) {
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

	private static String getTimeStamp(String[] entry) {
		//timestamp independent
		return entry[2];
	}

	private static String getRecordType(String[] entry) {
		//6 values
		//ENTER,DELETE,AMEND,TRADE,OFFTR,CANCEL_TRADE
		return entry[3];
	}

	private static float getPrice(String[] entry) {
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

	private static int getVolume(String[] entry) {
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

	private static String getBidAsk(String[] entry) {
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
	
	private static String getQualifier(String[] entry) {
		//independent
		return entry[8];
	}


}
