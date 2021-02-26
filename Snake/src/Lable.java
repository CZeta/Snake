import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Lable extends JLabel{
	private static final long serialVersionUID=1L;
	BufferedImage img, marker, snake;
	
	
	public Lable() {
		try {
			snake=ImageIO.read(new File("rsc/snake.png"));
			marker = ImageIO.read(new File("rsc/Marker.png"));
			img = ImageIO.read(new File("rsc/background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Bilder konnten nicht geldanden werden");
			e.printStackTrace();
		}
	}

	protected  void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.drawImage(img, 0, 0,400,400, null);
		g.drawImage(marker,Main.Marker[0],Main.Marker[1],10,10,null);
		
		for(int[] next:Main.snake) {
			g.drawImage(snake,next[0] ,next[1],10,10, null);
			
		}
		
		repaint();
	}
}
