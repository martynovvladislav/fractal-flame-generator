package edu.fractalFlame.utils;

import edu.fractalFlame.entities.Point;
import edu.fractalFlame.entities.Rect;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    private RandomUtils() {}

    public static <E> E random(List<E> list) {
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }

    public static Point random(Rect rect) {
        return new Point(
            ThreadLocalRandom.current().nextDouble(rect.x(), rect.x() + rect.width()),
            ThreadLocalRandom.current().nextDouble(rect.y(), rect.y() + rect.height())
        );
    }
}
