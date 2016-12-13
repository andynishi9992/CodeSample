
public class Semaphore {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private int key;
	public Semaphore(int i){
		key = i;
	}

	public synchronized void P(){
		while(key <1){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		key--;
	}
	public synchronized void V(){
		key++;
		notify();
	}
	
}
