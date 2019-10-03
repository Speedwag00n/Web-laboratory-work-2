package web.lab.chart;

public class DefaultArea extends Area {

    public DefaultArea(double r) {
        super(r);
    }

    public boolean hit(Point point) {
        return ((point.getX() >= 0) && (point.getY() >= 0) && (getR() >= point.getX()) && (getR() / 2 >= point.getY()))
                || ((point.getX() >= 0) && (point.getY() <= 0) && (point.getY() >= 2 * point.getX() - getR()))
                || ((point.getX() <= 0) && (point.getY() <= 0) && (Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2) <= Math.pow(getR() / 2, 2)));
    }

}
