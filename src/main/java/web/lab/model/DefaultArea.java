package web.lab.model;

public class DefaultArea extends Area {

    public boolean hit(Point point) {
        return ((point.getX() >= 0) && (point.getY() >= 0) && (point.getR() >= point.getX()) && (point.getR() / 2 >= point.getY()))
                || ((point.getX() >= 0) && (point.getY() <= 0) && (point.getY() >= 2 * point.getX() - point.getR()))
                || ((point.getX() <= 0) && (point.getY() <= 0) && (Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2) <= Math.pow(point.getR() / 2, 2)));
    }

}
