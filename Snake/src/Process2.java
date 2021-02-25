
public class Process2 implements Runnable{

	@Override
	public void run() {
		while(true) {
		Main.move();
		Main.getPosition();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}

}
