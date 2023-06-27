import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pacman extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillArc(50, 50, 200, 200, 45, 270);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pac-Man Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.add(new Pacman());
        frame.setVisible(true);
    }
}
