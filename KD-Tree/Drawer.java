import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Drawer extends JPanel {
	public static int x = -1; 
	public static int y = -1;
	public static int size;
	public static final int PROPORTION = 20;
	public static final int POINT_SIZE = 10;
	private TreeCreator treeCreator;

	public Drawer(int size) throws KeySizeException, NumberFormatException, IOException {
		this.treeCreator = new TreeCreator();
		this.size = size;
	}

	public void drawing(int x, int y) {
		this.x = x;
		this.y = y;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillOval(x, y, POINT_SIZE, POINT_SIZE);
		drawRectangles(treeCreator.rectangles, g, Color.RED);
		drawCircles(treeCreator.circles, g, Color.RED);
		Point intersectingPoint = new Point(new int[]{x / PROPORTION, (size - y) /PROPORTION});
		System.out.println(intersectingPoint.coord[0]);
		System.out.println(intersectingPoint.coord[1]);
		drawRectangles(treeCreator.makeIntersectingRectangles(intersectingPoint), g, Color.BLUE);
		drawCircles(treeCreator.makeIntersectingCircles(intersectingPoint), g, Color.GREEN);
		
	}
	private static void drawRectangles(ArrayList<Rectangle> rectangles, Graphics g, Color color){
		for (Rectangle rectangle : rectangles) {
			g.setColor(color);
			int x = (int) rectangle.max.key.coord[0] * PROPORTION;
			int y = size - (int) rectangle.max.key.coord[1] * PROPORTION;
			int width = (int) rectangle.getWidth()* PROPORTION;
			int height = (int) rectangle.getHeight()* PROPORTION;
			g.drawRect(x,y,width, height);
		}
	}
	private static void drawCircles(ArrayList<Circle> circles, Graphics g, Color color){
		for (Circle circle : circles) {
			g.setColor(color);
			int x = (int) circle.center.key.coord[0] * PROPORTION;
			int y = size - (int) circle.center.key.coord[1] * PROPORTION;
			int radius = (int) circle.radius * PROPORTION;
			
			g.drawOval(x, y, radius, radius);
		}
	}
}
