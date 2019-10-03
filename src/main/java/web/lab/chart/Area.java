package web.lab.chart;

public abstract class Area {

    private double r;

    public Area(double r) {
        this.r = r;
    }

    public abstract boolean hit(Point point);

    public double getR() {
        return r;
    }

}
