package edu.fractalFlame.transformations;

import edu.fractalFlame.entities.Point;

public class LinearTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(point.x(), point.y());
    }
}
