import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Calendar;

public class AnalogClock extends JPanel {

    private int hour;
    private int minute;
    private int second;

    public AnalogClock() {
        setSize(400, 400);
        setBackground(Color.white);
        Timer timer = new Timer(1000, e -> {
            Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR);
            minute = calendar.get(Calendar.MINUTE);
            second = calendar.get(Calendar.SECOND);
            repaint();
        });
        timer.start();
    }

   
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Calendar time = Calendar.getInstance();
        int hour = time.get(Calendar.HOUR_OF_DAY) % 12;
        int minute = time.get(Calendar.MINUTE);
        int second = time.get(Calendar.SECOND);
        double angle;
        int x,y;
        
        g.drawOval(100,100,300,300);
        //Label the clock
        String s="12";
        int i=0;
        while(i<12)
        {
            angle = Math.toRadians(30*(i-3));
            x = 250+(int)(Math.cos(angle)*135);
            y = 250+(int)(Math.sin(angle)*135);
            g.drawString(s,x,y);
            i++;
            s=String.valueOf(i);
        }	
        //Draw the hours hand
        g.setColor(Color.green);
        angle = Math.toRadians((30*hour)-90);
        x = 250+(int)(Math.cos(angle)*100);
        y = 250+(int)(Math.sin(angle)*100);
            g.drawLine(250,250,x,y);
        //Draw the minutes hand
        g.setColor(Color.red);
        angle = Math.toRadians((6*minute)-90);
        x = 250+(int)(Math.cos(angle)*115);
        y = 250+(int)(Math.sin(angle)*115);
        g.drawLine(250,250,x,y);
        //Draw the seconds hand
        g.setColor(Color.blue);
        angle = Math.toRadians((6*second)-90);
        x = 250+(int)(Math.cos(angle)*130);
        y = 250+(int)(Math.sin(angle)*130);
        g.drawLine(250,250,x,y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Analog Clock");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.add(new AnalogClock());
            frame.setVisible(true);
        });
    }
}