import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Main {
	static ArrayList<int[]> snake=new ArrayList<>();
	static int[] richtung= {10,0};
	
	static int[] initial1= {10,10};
	static int[] initial2= {10,15};
	static int[] initial3= {10,20};
	static int[] Marker=new int[2];
	

	public static void main(String[] args) {
		newMarker();
		try {
			Thread.sleep(150);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		snake.add(initial1);
		snake.add(initial2);
		snake.add(initial3);
		//getPosition();
		move();
		//getPosition();
		
		//richtung[1]=1;
		move();
		getPosition();
		
		Runnable task2 = () -> { 
			while(true) {
				move();
				//getPosition();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		new Thread(task2).start();
		//new Process2();
		
		
		JFrame myFrame=new JFrame();
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myFrame.setSize(500, 500);
		
		
		Lable lbl1=new Lable();
		lbl1.setVisible(true);
		lbl1.setBounds(0,0,500,500);
		
		myFrame.add(lbl1);
		
		/*try {
			final BufferedImage img = ImageIO.read(new File("rsc/background.png"));
		}
		catch (IOException e) {
			System.out.println("hat nicht geklappt");
			e.printStackTrace();
		}
		
		
		/*try {
			final BufferedImage image = ImageIO.read(new File("rsc/background.png"));
			Graphics g=image.getGraphics();
			System.out.println("das klappt");
			
			JPanel pane = new JPanel() {
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                System.out.println("Wird ausgeführt");
	                g.drawImage(image, 10, 10, null);
	                
	            }
	            paintComponent(g);
	        };


	        //myFrame.add(pane);
		} catch (IOException e) {
			System.out.println("hat nicht geklappt");
			e.printStackTrace();
		}*/
		
		
		myFrame.addKeyListener(new KeyHandler());
		
	}
	
	public static synchronized void move() {
		
		int[] firstPos=new int[2];
		firstPos[0]=snake.get(0)[0]+richtung[0];
		firstPos[1]=snake.get(0)[1]+richtung[1];
		if(max(firstPos)>400||min(firstPos)<0) {
			System.out.println("Du bist gegen die Wand geknallt");
			System.exit(0);
		}
		if(firstPos[0]==Marker[0]&&firstPos[1]==Marker[1]) {
			System.out.println("Juhu");
			snake.add(snake.get(snake.size()-1));
			newMarker();
		}
		
		//check if allowed
		
		//if(snake.contains(firstPos)) {
		if(enthält(snake,firstPos)) {
			System.out.println("Schlange hat sich gebissen");
			System.exit(0);
		}
		else {
		
			for(int i=1;i<snake.size();i++) {
			snake.set(snake.size()-i, snake.get(snake.size()-i-1));
			}
			
			snake.set(0,firstPos);
		}
	}
	
	public static void getPosition() {
		for(int[]posi:snake) {
			System.out.println("("+posi[0]+","+posi[1]+")");
		}
		System.out.println("");
	}
	
	public static boolean enthält(ArrayList<int[]> s,int[] such) {
		boolean enthalten=false;
		for(int[] next:s) {
			//if(next.equals(such)) {
			if(next[0]==such[0]&&next[1]==such[1]) {
				enthalten=true;
			}
		}
		
		return enthalten;
	}
	
	public static void newMarker() {
		Marker[0]=(int) ((Math.random()*40))*10;
		Marker[1]=((int) (Math.random()*40))*10;
		System.out.println("Neuer Marker bei: ("+Marker[0]+","+Marker[1]+")");
	}
	
	public static int max(int[] arry) {
		int max=0;
		for(int i:arry) {
			if(i>max) {
				max=i;
			}
		}
		return max;
	}
	public static int min(int[] arry) {
		int min=0;
		for(int i:arry) {
			if(i<min) {
				min=i;
			}
		}
		return min;
	}

}
