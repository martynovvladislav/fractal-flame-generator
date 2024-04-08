package edu.fractalFlame.utils;

import edu.fractalFlame.entities.FractalImage;
import edu.fractalFlame.entities.Pixel;
import edu.fractalFlame.entities.Point;
import edu.fractalFlame.entities.Rect;

public class WorldUtils {
    private WorldUtils() {}

    public static Pixel mapRange(FractalImage canvas, Point point, Rect world) {
        if (!world.contains(point)) {
            return null;
        }
        return canvas.pixel(
            (int) ((point.x() - world.x()) / world.width() * canvas.width()),
            (int) ((point.y() - world.y()) / world.height() * canvas.height())
        );
    }

    public static Point rotate(Rect world, Point point, double theta) {
        double centerX = world.x() + world.width() / 2;
        double centerY = world.y() + world.height() / 2;

        double rotatedX = (point.x() - centerX) * Math.cos(theta) - (point.y() - centerY) * Math.sin(theta);
        double rotatedY = (point.x() - centerX) * Math.sin(theta) + (point.y() - centerY) * Math.cos(theta);

        return new Point(rotatedX + centerX, rotatedY + centerY);
    }
}
