import java.awt.*;
import java.util.ArrayList;

public class BoundedDotDecorator extends MovingDecorator{
    private Point topLeft;
    private Point bottomRight;
    private MovingDot md;
    private ArrayList<Obstacle> obstacles;

    public BoundedDotDecorator(MovingDot md, Point bottomRight, ArrayList<Obstacle> obstacles) {
        this(md, new Point(0,0), bottomRight, obstacles);
    }

    public BoundedDotDecorator(MovingDot md, Point topLeft, Point bottomRight, ArrayList<Obstacle> obstacles) {
        super (md);
        this.bottomRight = bottomRight;
        this.topLeft = topLeft;
        this.md = md;
        this.obstacles = obstacles;
    }

    @Override
    public synchronized void move() throws OutOfBoundsException{
        if (getLeft() < topLeft.x || getRight()>bottomRight.x){
            md.setMotion(-md.getDx(),md.getDy());
        }
        if (getTop() < topLeft.y){
            md.setMotion(md.getDx(),-md.getDy());
        }
        for (Obstacle o: obstacles) {
            if ((getBottom() == o.top()) || (getTop() == o.bottom())) {
                if ((getLeft() < o.right()) && (getRight() > o.left())) {
                    setMotion(getDx(), -getDy());
                    o.hitBy(this);
                }
            }
            if ((getRight() == o.left()) || (getLeft() == o.right())) {
                if ((getBottom() > o.top()) && (getTop() < o.bottom())) {
                    setMotion(-getDx(), getDy());
                    o.hitBy(this);
                }
            }
        }
        md.move();
        if (getTop() > bottomRight.y ){
            throw new OutOfBoundsException();
        }
    }
}
