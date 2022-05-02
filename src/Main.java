import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel difficultyPanel = new JPanel();
        JCheckBox easy = new JCheckBox();
        JCheckBox medium = new JCheckBox();
        JCheckBox hard = new JCheckBox();
        easy.setText("Easy");
        easy.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String difficulty = "easy";
                JFrame frame2 = new JFrame();
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.getContentPane().add(new LaunchPanel(difficulty));
                frame2.pack();
                frame2.setVisible(true);
            }
        });
        medium.setText("Medium");
        medium.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String difficulty = "easy";
                JFrame frame2 = new JFrame();
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.getContentPane().add(new LaunchPanel(difficulty));
                frame2.pack();
                frame2.setVisible(true);
            }
        });
        hard.setText("Hard");
        hard.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String difficulty = "easy";
                JFrame frame2 = new JFrame();
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.getContentPane().add(new LaunchPanel(difficulty));
                frame2.pack();
                frame2.setVisible(true);
            }
        });
        difficultyPanel.add(easy);
        difficultyPanel.add(medium);
        difficultyPanel.add(hard);
        frame.getContentPane().add(difficultyPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
