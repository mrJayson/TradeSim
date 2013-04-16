package app;

public class Order {
	
	final public long ID;//IDs use 64-bit integer value
	public String timeStamp;
	final public String recordType;
	final public String bidAsk;
	public float price;
	public int volume;
	public String qualifier;
	//price, volume, qualifier, timeStamp have possibility of changing due to
	//the AMEND order
	
	
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
