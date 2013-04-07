import java.util.LinkedList;


public class OrderBook {
	//orderbooks for one company
	private LinkedList<Order> bid = new LinkedList<Order>();
	private LinkedList<Order> ask = new LinkedList<Order>();
	
	public void processOrder(Order o) throws Exception {
		if (o.recordType.equals("ENTER")) {
			addOrder(o);
		}
		else if (o.recordType.equals("DELETE")) {
			deleteOrder(o);
		}
		else if (o.recordType.equals("AMEND")) {
			amendOrder(o);
		}
		else if (o.recordType.equals("TRADE")) {
			trade(o);
		}
		else if (o.recordType.equals("OFFTR")) {
			offTrade(o);
		}
		else if (o.recordType.equals("CANCEL_TRADE")) {
			cancel_Trade(o);
		} else {
			throw new UnsupportedOperationException();
		}
		
	}
	
	private void cancel_Trade(Order o) {
		// TODO Auto-generated method stub
		
	}

	private void offTrade(Order o) {
		// TODO Auto-generated method stub
		
	}

	private void trade(Order o) {
		// TODO Auto-generated method stub
		
	}

	private void amendOrder(Order o) {
		// TODO Auto-generated method stub
		
	}

	public void addOrder(Order o){
		if (o.bidAsk.equals("B")) {
			insert(o, bid);
		}
		else if (o.bidAsk.equals("A")) {
			insert(o, ask);
		} else {
			throw new UnsupportedOperationException();
		}
	}
	
	//must maintain ordered state for books, ordered on price
	private void insert(Order o, LinkedList<Order> book) {
		for (int i = 0; i < book.size(); i++) {
			if (o.price >= book.get(i).price) {
				book.add(i, o);
				return;
				//TODO sort on time when prices are same
				//TODO insert properly for sell book
			}
		}
		//catches orders that are either at the end of the list, or if
		//the list was empty and there was nothing to compare to
		book.addLast(o);
	}
	
	private void deleteOrder(Order o) {
		
	}
	
	public void display() {
		clearConsole();
		System.out.println("bid");
		for (Order o:bid) {
			System.out.print(o.ID+"\t");
			System.out.print(o.price+"\t");
			System.out.println(o.volume);
		}
	}
	
	public static void clearConsole() {
		//works only in command line
		String ESC = "\033[";
		System.out.print(ESC + "2J"); 
	}

}
