package concurrence.bingfa2;

public class LiftOff implements Runnable {
	private final int orderNum;
	
	public LiftOff(int orderNum) {
		this.orderNum = orderNum;
	}
	
	@Override
	public void run() {
		System.out.println("lift off run " + orderNum);
	}

}
