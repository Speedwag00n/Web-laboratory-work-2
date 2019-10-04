package web.lab.model;

import web.lab.chart.Point;

public class HistoryRecord {

    private Point point;
    private double r;
    private boolean hit;

    public HistoryRecord(Point point, double r, boolean hit) {
        this.point = point;
        this.r = r;
        this.hit = hit;
    }

    public Point getPoint() {
        return point;
    }

    public double getR() {
        return r;
    }

    public boolean getHit() {
        return hit;
    }

}
