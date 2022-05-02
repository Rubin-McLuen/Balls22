import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LaunchPanel extends JPanel {

    private ArrayList<MovingDot> dots;
    private ArrayList<Obstacle> obstacles;
    private Dot launchPoint;
    private Point s;
    private int numDots;


    public LaunchPanel(String difficulty) {
        if (difficulty == "easy"){
            setPreferredSize(new Dimension(500, 500));
            numDots = 30;
            dots = new ArrayList<MovingDot>();
            obstacles = new ArrayList<Obstacle>();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 3; j++) {
                    Obstacle o = new Obstacle(new Point(i * 75 + 100, j * 75 + 50), 50, 10, Color.GREEN);
                    obstacles.add(o);
                }
            }
            s = new Point(250, 250);
            launchPoint = new Dot(s);
            launchPoint.setColor(Color.GREEN);

            addMouseListener(new LaunchPanel.MousePlay());
        }
        if (difficulty == "medium"){
            setPreferredSize(new Dimension(500, 500));
            numDots = 50;
            dots = new ArrayList<MovingDot>();
            obstacles = new ArrayList<Obstacle>();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 6; j++) {
                    Obstacle o = new Obstacle(new Point(i * 50 + 25, j * 30 + 50), 25, 10, Color.BLUE);
                    obstacles.add(o);
                }
            }
            s = new Point(250, 250);
            launchPoint = new Dot(s);
            launchPoint.setColor(Color.GREEN);

            addMouseListener(new LaunchPanel.MousePlay());
        }
        if (difficulty == "hard"){
            setPreferredSize(new Dimension(500, 500));
            numDots = 60;
            dots = new ArrayList<MovingDot>();
            obstacles = new ArrayList<Obstacle>();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    Obstacle o = new Obstacle(new Point(i * 35 + 125, j * 35 + 100), 25, 10, Color.RED);
                    obstacles.add(o);
                }
            }
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 5; j++) {
                    Obstacle o = new Obstacle(new Point(i * 55 + 50, j * 55 + 50), 50, 30, Color.DARK_GRAY);
                    obstacles.add(o);
                }
            }
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 5; j++) {
                    Obstacle o = new Obstacle(new Point(i * 55 + 450, j * 55 + 50), 50, 30, Color.DARK_GRAY);
                    obstacles.add(o);
                }
            }
            s = new Point(250, 250);
            launchPoint = new Dot(s);
            launchPoint.setColor(Color.GREEN);

            addMouseListener(new LaunchPanel.MousePlay());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int h = this.getHeight();
        int w = this.getWidth();
        Point c = new Point(w/2, h-30);
        launchPoint.setCenter(c);
        launchPoint.paint(g);
        ArrayList<MovingDot> remove = new ArrayList<MovingDot>();
        ArrayList<Obstacle> removeObstacles = new ArrayList<Obstacle>();
        g.drawString(String.valueOf(numDots),w/2, h-40);

        for (Obstacle o: obstacles){
            try {
                o.paint(g);
            } catch (BrokenBlockException e) {
                removeObstacles.add(o);
            }
        }

        for (MovingDot d: dots) {
            try {
                d.move();
            } catch (OutOfBoundsException e) {
                remove.add(d);
            }
            d.paint(g);
        }

        for (MovingDot d: remove){
            dots.remove(d);
        }
        remove.clear();

        for (Obstacle o: removeObstacles){
            obstacles.remove(o);
        }
        removeObstacles.clear();


        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
        if (obstacles.size() == 0){
            g.setColor(Color.BLUE);
            g.drawString("You Win!", 220, 245);
//            Timer timer = new Timer(3000, new ActionListener() {
//                public void actionPerformed(ActionEvent evt) {
//                    repaint();
//                }
//            });
//            timer.start();
//            System.exit(0);
        }
        if (numDots <= 0 && dots.size() == 0){
            g.setColor(Color.RED);
            g.drawString("You Lose!", 230, 245);
//            Timer timer = new Timer(3000, new ActionListener() {
//                public void actionPerformed(ActionEvent evt) {
//                    repaint();
//                }
//            });
//            timer.start();
//            System.exit(0);
        }
    }

    private void generateDot(Point p){
        if (numDots > 0) {
            MovingDot d = new MovingDot(launchPoint.getCenter(), p, 1);
            d = new BoundedDotDecorator(d, new Point(getWidth(), getHeight()), obstacles);
            dots.add(d);
            numDots--;
            Thread t = new Thread(d);
            t.start();
        }
    }

    private class MousePlay implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            generateDot(e.getPoint());
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}

