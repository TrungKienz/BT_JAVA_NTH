import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LongTaskExample {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Long Task Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Please waiting and click on the below button to start task !!!");
        frame.getContentPane().add(label);
        JButton startTask = new JButton("Start Task");
        
        startTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Thread newThread = new Thread() {
                    @Override
                    public void run() {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
                        for (int i = 1; i <= 5; i++) {
                            final String currentTime = dateFormat.format(new Date());
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    label.setText("Current Time: " + currentTime);
                                }
                            });
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                label.setText("Finish Task!!!!");
                            }
                        });
                    }
                };
                newThread.start();
            }
        });

        frame.getContentPane().add(startTask, BorderLayout.PAGE_END);
        frame.setSize(500, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
