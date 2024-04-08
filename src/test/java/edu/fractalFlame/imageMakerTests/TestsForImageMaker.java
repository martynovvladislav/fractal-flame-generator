package edu.fractalFlame.imageMakerTests;

import edu.fractalFlame.entities.FractalImage;
import edu.fractalFlame.imageMakers.ImageFormat;
import edu.fractalFlame.imageMakers.ImageUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Path;

public class TestsForImageMaker {
    @Test
    @DisplayName("create and save image test")
    void saveImageTest() {
        FractalImage image = FractalImage.create(100, 100);
        File file = new File("src/main/java/edu/fractalFlame/images/test.png");
        if (file.exists()) {
            file.delete();
        }
        ImageUtils.save(image, Path.of("src/main/java/edu/fractalFlame/images/test.png"), ImageFormat.PNG);
        Assertions.assertTrue(new File("src/main/java/edu/fractalFlame/images/test.png").exists());
    }
}
