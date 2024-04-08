package edu.fractalFlame.renderersTests;

import edu.fractalFlame.entities.FractalImage;
import edu.fractalFlame.entities.Rect;
import edu.fractalFlame.renderers.MultiThreadRenderer;
import edu.fractalFlame.renderers.SingleThreadRenderer;
import edu.fractalFlame.transformations.LinearTransformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TestsForRenderers {
    @Test
    @DisplayName("single thread renderer test")
    void correctSingleThreadRenderTest() {
        Assertions.assertDoesNotThrow(() -> new SingleThreadRenderer().render(
            FractalImage.create(100, 100),
            new Rect(-1, -1, 2, 2),
            List.of(new LinearTransformation()),
            2,
            1,
            1,
            1
        ));
    }

    @Test
    @DisplayName("multi thread renderer test")
    void correctMultiThreadRenderTest() {
        Assertions.assertDoesNotThrow(() -> new MultiThreadRenderer().render(
            FractalImage.create(100, 100),
            new Rect(-1, -1, 2, 2),
            List.of(new LinearTransformation()),
            2,
            1,
            1,
            1
        ));
    }
}
