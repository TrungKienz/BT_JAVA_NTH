import javax.swing.*;
import java.awt.*;

public class MathFunctionGraph extends JFrame {
    private static final int WIDTH = 800; // Width of the graph
    private static final int HEIGHT = 600; // Height of the graph
    private static final int BORDER_GAP = 30; // Gap between the graph and frame border
    private static final Color GRAPH_COLOR = Color.blue; // Color of the graph
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f); // Stroke of the graph line
    private static final int POINT_SIZE = 5; // Size of data points on the graph
    private static final double SCALE_X = 0.01; // Scale factor for x-axis
    private static final double SCALE_Y = 0.01; // Scale factor for y-axis

    private static final double[] functionData = new double[WIDTH]; // Array to store function data

    public MathFunctionGraph() {
        super("Math Function Graph");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        // Calculate function data
        calculateFunctionData();

        // Draw grid
        g.setColor(Color.lightGray);
        for (int i = 0; i <= WIDTH; i += 20) {
            g.drawLine(i, 0, i, HEIGHT);
            g.drawLine(0, i, WIDTH, i);
        }

        // Draw x and y axes
        g.setColor(Color.black);
        g.drawLine(BORDER_GAP, HEIGHT - BORDER_GAP, WIDTH - BORDER_GAP, HEIGHT - BORDER_GAP);
        g.drawLine(BORDER_GAP, BORDER_GAP, BORDER_GAP, HEIGHT - BORDER_GAP);

        // Draw x and y axis labels
        g.drawString("x", WIDTH - BORDER_GAP, HEIGHT - BORDER_GAP + 10);
        g.drawString("y", BORDER_GAP - 10, BORDER_GAP - 5);

        // Draw graph
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(GRAPH_STROKE);
        g2.setColor(GRAPH_COLOR);

        for (int i = 0; i < WIDTH - 1; i++) {
            int x1 = i;
            int y1 = (int) (HEIGHT - BORDER_GAP - functionData[i] / SCALE_Y);
            int x2 = i + 1;
            int y2 = (int) (HEIGHT - BORDER_GAP - functionData[i + 1] / SCALE_Y);
            g2.drawLine(x1, y1, x2, y2);
        }

        // Draw data points
        g2.setColor(Color.red);
        for (int i = 0; i < WIDTH; i++) {
            int x = i;
            int y = (int) (HEIGHT - BORDER_GAP - functionData[i] / SCALE_Y);
            g2.fillOval(x - POINT_SIZE / 2, y - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
        }
    }

    private void calculateFunctionData() {
        // Example function: f(x) = sin(x)
        for (int i = 0; i < WIDTH; i++) {
            double x = (i - BORDER_GAP) * SCALE_X;
            functionData[i] = Math.sin(x);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MathFunctionGraph::new);
    }
}

