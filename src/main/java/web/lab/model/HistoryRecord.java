package web.lab.model;

import web.lab.chart.Point;

public class HistoryRecord {

    private Point point;
    private boolean hit;

    public HistoryRecord(Point point, boolean hit) {
        this.point = point;
        this.hit = hit;
    }

    public Point getPoint() {
        return point;
    }

    public boolean getHit() {
        return hit;
    }

}
