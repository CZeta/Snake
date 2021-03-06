import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;



public class Main {
	
	static boolean repeat=true;
	
	static Lable lbl1;
	static JTable BestenListe;
	
	static JFrame myFrame; 
	
	static boolean wait=true;
	//brauche ich das noch?
	
	static Properties p;
	static Vector<int[]> snake=new Vector<>();//=new ArrayList<>();
	
	static int[] richtung= {10,0};
	
	static int[] initial1= {200,200};
	static int[] initial2= {10,15};
	static int[] initial3= {10,20};
	static int[] Marker=new int[2];
	
	static int GESCHWINDIGKEIT1=100;
	static int GESCHWINDIGKEIT2=200;
	static int GESCHWINDIGKEIT3=300;
	static int GESCHWINDIGKEIT4=400;
	
	static int temp;
	
	static KeyHandler fritz;
			

	public static void main(String[] args) {
		//Speichern einer Bestenliste:
		p = new Properties();
		
		try {
			/*FileOutputStream out = new FileOutputStream("properties.txt");
			p.put("1", "000");
			p.put("2", "000");
			p.put("3", "000");
			
			p.store(out, "Beispiel Version 2");
			*/
			
			
			FileInputStream in = new FileInputStream("properties.txt");
			p.load(in);
			System.out.println("Platz1:"+ p.getProperty("1"));
			System.out.println("Platz2:"+ p.getProperty("2"));
			System.out.println("Platz3:"+ p.getProperty("3"));
			in.close();
			
		} catch (IOException e) {
			System.out.println("couldn't read file");
			e.printStackTrace();
		}
		
		
		
		
		boolean bedingung=true;
		System.out.println("Gib eine Startgeschwindigkeit zwischen 1 und 4 ein");
		Scanner eingabe=new Scanner(System.in);
		while(bedingung) {
			
			temp=eingabe.nextInt();
			if(temp<1||temp>4) {
				System.out.println("Gib einen g�ltigen Wert ein!");
			}
			else bedingung=false;
		}
		
		//vielleicht mal in enums �ndern...
		switch(temp) {
		case 1:
			temp=GESCHWINDIGKEIT1;
			break;
		case 2:
			temp=GESCHWINDIGKEIT2;
			break;
		case 3:
			temp=GESCHWINDIGKEIT3;
			break;
		case 4:
			temp=GESCHWINDIGKEIT4;
			break;
		}
		
		 //snake= Collections.synchronizedList(new ArrayList<int[]>());
		newMarker();
		
		try {
			Thread.sleep(550);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		snake.add(initial1);
		snake.add(initial1);
		snake.add(initial1);
		
		myFrame=new JFrame();
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myFrame.setSize(500, 500);
		
		
		lbl1=new Lable();
		lbl1.setVisible(true);
		lbl1.setBounds(0,0,500,500);
		
		myFrame.add(lbl1);
		fritz=new KeyHandler();
		myFrame.addKeyListener(fritz);
		/*
		bedingung=true;
		while(bedingung) {
		String initDirection=eingabe.nextLine();
		
			if( initDirection.equals("w")) {
				bedingung=false;
				richtung[0]=0;
				richtung[1]=-10;
			}
			else if( initDirection.equals("s")) {
				bedingung=false;
				richtung[0]=0;
				richtung[1]=10;
			}
			else if( initDirection.equals("a")) {
				bedingung=false;
				richtung[0]=-10;
				richtung[1]=0;
			}
			else if( initDirection.equals("d")) {
				bedingung=false;
				richtung[0]=10;
				richtung[1]=0;
			}
			
		}
		*/
		
		
		/*
		//getPosition();
		move();
		//getPosition();
		
		//richtung[1]=1;
		move();
		getPosition();
		*/
		/*
		Runnable task2 = () -> { 
			while(true) {
				move();
				//getPosition();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		new Thread(task2).start();
		//new Process2();
		
		*/
		
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
	                System.out.println("Wird ausgef�hrt");
	                g.drawImage(image, 10, 10, null);
	                
	            }
	            paintComponent(g);
	        };


	        //myFrame.add(pane);
		} catch (IOException e) {
			System.out.println("hat nicht geklappt");
			e.printStackTrace();
		}*/
		
		
		
		
	}
	
	public static void showBList() {
		String[]columnNames= {"Platzierung", "Punkte"};
		String[][] data= {
							{ "1", p.getProperty("1")},
							{"2", p.getProperty("2")},
							{"3", p.getProperty("3")}
						};
		BestenListe=new JTable(data,columnNames);
		BestenListe.setVisible(true);
		BestenListe.setBounds(30, 40, 200, 300); 
		
		JScrollPane sp=new JScrollPane(BestenListe);
		//BestenListe.add(sp);
		
		myFrame.add(sp);
	}
	
	public static void SaveForList(int n) {
		boolean neuerBestwert=true;
		int i=1;
		while(neuerBestwert&i<4) {
			if(n>Integer.parseInt( p.getProperty(String.valueOf(i),"fehler"))) {
				p.setProperty(String.valueOf(i),String.valueOf(n));
				neuerBestwert=false;
				System.out.println("Neuer Bestwert! Du bist auf Platz "+i);
			}
			i++;
		}
		
		try {
			FileOutputStream out = new FileOutputStream("properties.txt");
			p.store(out,"Beispiel");
			out.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void stopMove() {
		repeat=false;
	}
	
	public static  void move() {
		
		
		int[] firstPos=new int[2];
		firstPos[0]=snake.get(0)[0]+richtung[0];
		firstPos[1]=snake.get(0)[1]+richtung[1];
		if(max(firstPos)>400||min(firstPos)<0) {
			System.out.println("Du bist gegen die Wand geknallt");
			System.out.println(snake.size());
			
			SaveForList(snake.size());
			
			//System.exit(0);
			
			lbl1.setVisible(false);
			
			stopMove();
			showBList();
		}
		if(firstPos[0]==Marker[0]&&firstPos[1]==Marker[1]) {
			System.out.println("Juhu");
			snake.add(snake.get(snake.size()-1));
			newMarker();
		}
		
		//check if allowed
		
		//if(snake.contains(firstPos)) {
		if(enth�lt(snake,firstPos)) {
			System.out.println("Schlange hat sich gebissen");
			System.out.println(snake.size());
			
			SaveForList(snake.size());
			
			lbl1.setVisible(false);
			stopMove();
			//System.exit(0);
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
	
	public static boolean enth�lt(List<int[]> snake2,int[] such) {
		boolean enthalten=false;
		for(int[] next:snake2) {
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
