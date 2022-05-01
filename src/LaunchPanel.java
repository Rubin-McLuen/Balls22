import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LaunchPanel extends JPanel {

    ArrayList<MovingDot> dots;
    ArrayList<Obstacle> obstacles;
    Dot launchPoint;
    Point s;


    public LaunchPanel() {
        setPreferredSize(new Dimension(500,500));
        dots = new ArrayList<MovingDot>();
        obstacles = new ArrayList<Obstacle>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++){
                Obstacle o = new Obstacle(new Point(i*75 + 100,j*75 + 50));
                obstacles.add(o);
            }
        }
        s = new Point(250,250);
        launchPoint =new Dot(s);
        launchPoint.setColor(Color.GREEN);

        addMouseListener(new LaunchPanel.MousePlay());
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

        for (Obstacle o: obstacles){
            o.paint(g);
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
        System.out.println(dots.size());

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    private void generateDot(Point p){
        System.out.println("Generate Dot");
        MovingDot  d = new MovingDot(launchPoint.getCenter(), p, 1);
        d = new BoundedDotDecorator(d, new Point(getWidth(),getHeight()), obstacles);
        dots.add(d);
        Thread t = new Thread(d);
        t.start();
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

