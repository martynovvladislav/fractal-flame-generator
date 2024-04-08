package edu.fractalFlame;

import edu.fractalFlame.entities.FractalImage;
import edu.fractalFlame.entities.Rect;
import edu.fractalFlame.imageMakers.ImageFormat;
import edu.fractalFlame.imageMakers.ImageUtils;
import edu.fractalFlame.imageProcessors.LogGammaCorrectionProcessor;
import edu.fractalFlame.renderers.MultiThreadRenderer;
import edu.fractalFlame.renderers.SingleThreadRenderer;
import edu.fractalFlame.transformations.DiskTransformation;
import edu.fractalFlame.transformations.HeartTransformation;
import edu.fractalFlame.transformations.HyperbolicTransformation;
import edu.fractalFlame.transformations.LinearTransformation;
import edu.fractalFlame.transformations.PolarTransformation;
import edu.fractalFlame.transformations.SineTransformation;
import edu.fractalFlame.transformations.SphericalTransformation;
import edu.fractalFlame.transformations.Transformation;
import edu.fractalFlame.ui.UIRenderer;
import java.awt.Image;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import lombok.extern.slf4j.Slf4j;
import static edu.fractalFlame.ui.UIRenderer.DISK_TRANSFORMATION;
import static edu.fractalFlame.ui.UIRenderer.HEART_TRANSFORMATION;
import static edu.fractalFlame.ui.UIRenderer.HYPERBOLIC_TRANSFORMATION;
import static edu.fractalFlame.ui.UIRenderer.LINEAR_TRANSFORMATION;
import static edu.fractalFlame.ui.UIRenderer.POLAR_TRANSFORMATION;
import static edu.fractalFlame.ui.UIRenderer.SINE_TRANSFORMATION;
import static edu.fractalFlame.ui.UIRenderer.SPHERICAL_TRANSFORMATION;

@Slf4j
public class Controller {
    private final UIRenderer uiRenderer = new UIRenderer();
    public static final String IMAGE_PATH = "C:\\FractalFlame\\image.png";

    private static final Map<String, Transformation> TRANSFORMATION_MAP = Map.of(
        DISK_TRANSFORMATION, new DiskTransformation(),
        HEART_TRANSFORMATION, new HeartTransformation(),
        HYPERBOLIC_TRANSFORMATION, new HyperbolicTransformation(),
        LINEAR_TRANSFORMATION, new LinearTransformation(),
        POLAR_TRANSFORMATION, new PolarTransformation(),
        SINE_TRANSFORMATION, new SineTransformation(),
        SPHERICAL_TRANSFORMATION, new SphericalTransformation()
    );

    public void initializeUI() {
        initializeButtonListeners();
        uiRenderer.setVisible(true);
    }

    private void initializeButtonListeners() {
        uiRenderer.getStartButton().setEnabled(true);
        uiRenderer.getStopButton().setEnabled(false);
        uiRenderer.getStartButton().addActionListener(e -> {
            uiRenderer.getStartButton().setEnabled(false);
            try {
                startRender();
            } catch (Exception ex) {
                log.info(ex.getMessage());
            }
            uiRenderer.getStartButton().setEnabled(true);
        });
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private void startRender() {
        FractalImage image;
        List<Transformation> transformations = new ArrayList<>();
        uiRenderer.getCheckBoxes().forEach(
            cb -> {
                if (cb.isSelected()) {
                    transformations.add(TRANSFORMATION_MAP.get(cb.getText()));
                }
            }
        );
        int affine = 0;
        int symmetry = 0;
        int samples = 0;
        int iterations = 0;
        for (Map.Entry<JLabel, JTextField> entry : uiRenderer.getFieldMap().entrySet()) {
            switch (entry.getKey().getText()) {
                case "Enter Affine Amount:" -> affine = Integer.parseInt(entry.getValue().getText());
                case "Enter Symmetry:" -> symmetry = Integer.parseInt(entry.getValue().getText());
                case "Enter Samples Amount:" -> samples = Integer.parseInt(entry.getValue().getText());
                default -> iterations = Integer.parseInt(entry.getValue().getText());
            }
        }
        if (uiRenderer.getMultiThreadCheckBox().isSelected()) {
            MultiThreadRenderer multiThreadRenderer = new MultiThreadRenderer();
            image = multiThreadRenderer.render(
                FractalImage.create(3840, 2160),
                new Rect(-3, -2, 6, 6),
                transformations,
                affine,
                symmetry,
                samples,
                iterations
            );
        } else {
            SingleThreadRenderer singleThreadRenderer = new SingleThreadRenderer();
            image = singleThreadRenderer.render(
                FractalImage.create(3840, 2160),
                new Rect(-3, -2, 6, 6),
                transformations,
                affine,
                symmetry,
                samples,
                iterations
            );
        }
        log.info("completed");
        LogGammaCorrectionProcessor processor = new LogGammaCorrectionProcessor();
        processor.process(image, 1.0);
        ImageUtils.save(
            image,
            Path.of(IMAGE_PATH),
            ImageFormat.PNG
        );
        uiRenderer.getImageLabel().setIcon(
            new ImageIcon(
                new ImageIcon(IMAGE_PATH)
                    .getImage()
                    .getScaledInstance(830, 600, Image.SCALE_SMOOTH)
            )
        );
        uiRenderer.getStartButton().setEnabled(true);
    }

    public void createDirectory() {
        File directory = new File("C:\\FractalFlame");

        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}
