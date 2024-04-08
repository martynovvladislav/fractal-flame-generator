package edu.fractalFlame.transformations;

import edu.fractalFlame.entities.Point;

public class SineTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.x()), Math.sin(point.y()));
    }
}
