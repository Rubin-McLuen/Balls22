import java.awt.*;

public abstract class  MovingDecorator extends MovingDot{
    private MovingDot md;


    public MovingDecorator(MovingDot m) {
        super();
        this.md = m;
    }

    // It is important to delegate ***ALL*** the messages methods
    // that can be used by your Decorator. If you don't, you are
    // manipulating the outer object, not the decorated inner core.

    @Override
    public void setMotion(double x, double y) {
        md.setMotion(x, y);
    }

    @Override
    public void move() throws OutOfBoundsException{
        md.move();
    }

    @Override
    void paint(Graphics g) {
        md.paint(g);
    }

    @Override
    public Point getCenter() {
        return md.getCenter();
    }

    @Override
    public int getTop() {
        return md.getTop();
    }

    @Override
    public int getBottom() {
        return md.getBottom();
    }

    @Override
    public int getLeft() {
        return md.getLeft();
    }

    @Override
    public int getRight() {
        return md.getRight();
    }

    @Override
    public double getDx() {
        return md.getDx();
    }

    @Override
    public double getDy() {
        return md.getDy();
    }

    @Override
    public double getSpeed() {
        return md.getSpeed();
    }

    @Override
    public Point getDirection() {
        return md.getDirection();
    }

    /// Something different
    // Note this function is technically not needed.
    // It would be handled by inheritance.
    // It doesn't manipulate any instance variables, just
    // uses methods.
    @Override
    public void run() {
       super.run();
    }
}
