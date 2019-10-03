package web.lab.validation;

public class LimiterImpl implements Limiter {

    public boolean isInLimits(double x, double y, double r) {
        if (x <= -3 || x >= 3) {
            return false;
        }
        if (y < -4 || y > 4) {
            return false;
        }
        if (r <= 2 || r >= 5) {
            return false;
        }
        return true;
    }

}
