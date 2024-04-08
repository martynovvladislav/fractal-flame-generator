package edu.fractalFlame.renderers;

import edu.fractalFlame.entities.FractalImage;
import edu.fractalFlame.entities.Rect;
import edu.fractalFlame.transformations.Transformation;
import java.util.List;

public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variants,
        int affineAmount,
        int symmetry,
        int samples,
        int iterPerSample
    );
}
