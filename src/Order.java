
public class Order {
	
	final public long ID;//IDs use 64-bit integer value
	final public String timeStamp;
	final public String recordType;
	final public String bidAsk;
	final public float price;
	final public int volume;
	final public String qualifier;
	
	
	public Order(long ID, String timeStamp, String recordType,
			String bidAsk, float price, int volume, String qualifier) {
		
		this.ID = ID;
		this.timeStamp = timeStamp;
		this.recordType = recordType;
		this.bidAsk = bidAsk;
		this.price = price;
		this.volume = volume;
		this.qualifier = qualifier;
		
	}

}
