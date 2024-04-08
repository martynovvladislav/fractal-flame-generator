package edu.fractalFlame.renderers;

import edu.fractalFlame.entities.FractalImage;
import edu.fractalFlame.entities.Rect;
import edu.fractalFlame.transformations.AffineTransformation;
import edu.fractalFlame.transformations.Transformation;
import java.util.List;

public class SingleThreadRenderer extends AbstractRenderer implements Renderer {
    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variants,
        int affineAmount,
        int symmetry,
        int samples,
        int iterPerSample
    ) {
        List<AffineTransformation> affineTransformations = AffineTransformation.generateAffineList(affineAmount);
        for (int num = 0; num < samples; num++) {
            renderFrame(iterPerSample, symmetry, canvas, world, variants, affineTransformations);
        }
        return canvas;
    }
}
