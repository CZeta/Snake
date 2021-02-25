import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar()=='s') {
			Main.richtung[0]=0;
			Main.richtung[1]=10;
			System.out.println("KeyTyped  event activated: S");
		}
		
		else if(e.getKeyChar()=='w') {
			Main.richtung[0]=0;
			Main.richtung[1]=-10;
			System.out.println("KeyTyped  event activated: W");
		}
		
		else if(e.getKeyChar()=='d') {
			Main.richtung[0]=10;
			Main.richtung[1]=0;
			System.out.println("KeyTyped  event activated: D");
		}
		
		else if(e.getKeyChar()=='a') {
			Main.richtung[0]=-10;
			Main.richtung[1]=0;
			System.out.println("KeyTyped  event activated: A");
		}
		
		
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
