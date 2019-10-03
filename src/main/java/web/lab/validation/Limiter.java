package web.lab.validation;

public interface Limiter {

    boolean isInLimits(double x, double y, double r);

}
