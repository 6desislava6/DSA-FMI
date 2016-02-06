import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GUI {
	final static int SIZE = 400;
	private static int x, y;
	public static Drawer object;
	

	public static void main(String[] a) throws KeySizeException, NumberFormatException, IOException{
		object = new Drawer(SIZE);
		JFrame f = new JFrame("KD-Tree");
		f.setSize(SIZE, SIZE);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.add(object);
		object.addMouseListener(new AL());
	}
	// ActionListener - кликваме с мишката и се оцветяват в синьо правоъгълниците,
	// на които принадлежи.
	public static class AL extends MouseAdapter {
		public void mouseClicked(MouseEvent e){
			x = e.getX();
			y = e.getY();
			object.drawing(x, y);
		}
	}
}