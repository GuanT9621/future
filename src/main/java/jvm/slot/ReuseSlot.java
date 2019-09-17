package jvm.slot;

public class ReuseSlot {
	
	public static void main(String[] args) {
		
		byte[] placeHolder = new byte[256 * 1024 * 1024];
		placeHolder = null;
		
		
		System.gc();
	}
	
}
