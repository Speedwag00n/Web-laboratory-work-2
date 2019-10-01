package web.lab.validation;

import web.lab.model.Point;

public interface Limiter {

    boolean isInLimits(Point point);

}
