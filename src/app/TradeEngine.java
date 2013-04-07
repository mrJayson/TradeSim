package app;
import java.util.LinkedList;


public class TradeEngine {
	//orderbooks for one company
	private LinkedList<Order> bid = new LinkedList<Order>();
	private LinkedList<Order> ask = new LinkedList<Order>();

	public void processOrder(Order o) throws Exception {
		//given any order, appropriately processes it into the books
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
			//Sirca data should never reach here
			throw new UnsupportedOperationException();
		}

	}

	public Order getBid() {
		return bid.getFirst();
	}

	public Order getAsk() {
		return ask.getFirst();
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
		//assumes it is an ENTER order
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
				//TODO handle AMEND orders
			}
		}
		//catches orders that are either meant to be added to the end
		//of the list, or if the list was empty
		//and there was nothing to compare to
		book.addLast(o);
	}

	private void deleteOrder(Order o) {
		//assumes it is a DELETE order
	}

	public void display() {
		//text display of order books for debugging
		clearConsole();
		System.out.println("bid\t\t\t\t\t|Ask");
		for (int i = 0; i < bid.size() || i < ask.size(); i++) {
			if (i < bid.size()) {
				System.out.print(bid.get(i).ID+"\t");
				System.out.print(bid.get(i).price+"\t");
				System.out.print(bid.get(i).volume + "\t|");
			} else {
				System.out.print("\t\t\t\t\t\t|");
			}
			if (i < ask.size()) {
				System.out.print(ask.get(i).ID+"\t");
				System.out.print(ask.get(i).price+"\t");
				System.out.print(ask.get(i).volume);
			}
			System.out.println();
		}
	}

	public static void clearConsole() {
		//works only in command line
		String ESC = "\033[";
		System.out.print(ESC + "2J"); 
	}
}
