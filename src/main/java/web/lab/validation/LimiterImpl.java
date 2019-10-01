package web.lab.validation;

import web.lab.model.Point;

public class LimiterImpl implements Limiter {

    public boolean isInLimits(Point point) {
        if (point.getX() <= -3 || point.getX() >= 3) {
            return false;
        }
        if (point.getY() < -4 || point.getY() > 4) {
            return false;
        }
        if (point.getR() <= 2 || point.getR() >= 5) {
            return false;
        }
        return true;
    }

}
