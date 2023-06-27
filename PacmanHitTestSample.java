import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;



public class PacmanHitTestSample {
	static Area shape = null;
	static Point2D center = new Point2D.Double(200, 200);
	static boolean hitting = false;

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Pacman Hit Test");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// construct the shape
			Ellipse2D head = new Ellipse2D.Double(-150, -150, 300, 300);
			Ellipse2D eye = new Ellipse2D.Double(-10, -110, 20, 20);

			GeneralPath mouth = new GeneralPath();
			mouth.moveTo(0, 0);
			mouth.lineTo(150, -50);
			mouth.lineTo(150, 50);
			mouth.closePath();

			shape = new Area(head);
			shape.subtract(new Area(eye));
			shape.subtract(new Area(mouth));

			JLabel canvas = new JLabel() {
				@Override
				public void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

					g2.setPaint(hitting ? Color.RED : new Color(200, 200, 0));
					g2.translate(center.getX(), center.getY());
					g2.fill(shape);
				}
			};

			canvas.addMouseListener(new MouseInputAdapter() {
				@Override
				public void mousePressed(MouseEvent evt) {
					Point2D objCoords = new Point2D.Double(evt.getX() - center.getX(), evt.getY() - center.getY());
					if (shape.contains(objCoords)) {
						hitting = true;
						canvas.repaint();
					}
				}

				@Override
				public void mouseReleased(MouseEvent evt) {
					if (hitting) {
						hitting = false;
						canvas.repaint();
					}
				}
			});

			canvas.setBounds(0, 0, 400, 400);
			frame.add(canvas);
					
			frame.setSize(400, 400);
			frame.setLayout(null);
			frame.setVisible(true);
		});
	}
}

