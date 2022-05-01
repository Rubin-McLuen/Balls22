import java.awt.*;

public class Obstacle {

    private Point center;
    private Rectangle region;
    private int size;
    private Color color;
    private int health;

    public Obstacle(Point center) {
        this.center = center;
        size = 50;
        region = new Rectangle(center.x-size/2,center.y-size/2, size, size);
        color = color.RED;
        health = 1;
    }

    public void paint(Graphics g) throws BrokenBlockException {
        g.setColor(color);
        g.fillRect(region.x,region.y, region.width,region.height);
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(health), center.x-8, center.y+5);
        if (health <= 0){
            throw new BrokenBlockException();
        }
    }

    public Rectangle getRegion() {
        return region;
    }

    public synchronized void hitBy(MovingDot d){
        health--;
    }

    public int top(){
        return region.y;
    }
    public int bottom(){
        return region.y+region.height;
    }
    public int left(){
        return region.x;
    }
    public int right(){
        return region.x +region.width;
    }
}
