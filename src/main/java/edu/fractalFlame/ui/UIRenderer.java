package edu.fractalFlame.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import lombok.Getter;

@Getter
public class UIRenderer extends JFrame {
    public static final String DISK_TRANSFORMATION = "Disk Transformation";
    public static final String HEART_TRANSFORMATION = "Heart Transformation";
    public static final String HYPERBOLIC_TRANSFORMATION = "Hyperbolic Transformation";
    public static final String LINEAR_TRANSFORMATION = "Linear Transformation";
    public static final String POLAR_TRANSFORMATION = "Polar Transformation";
    public static final String SINE_TRANSFORMATION = "Sine Transformation";
    public static final String SPHERICAL_TRANSFORMATION = "Spherical Transformation";

    private final List<JCheckBox> checkBoxes = List.of(
        new JCheckBox(DISK_TRANSFORMATION),
        new JCheckBox(HEART_TRANSFORMATION),
        new JCheckBox(HYPERBOLIC_TRANSFORMATION),
        new JCheckBox(LINEAR_TRANSFORMATION),
        new JCheckBox(POLAR_TRANSFORMATION),
        new JCheckBox(SINE_TRANSFORMATION),
        new JCheckBox(SPHERICAL_TRANSFORMATION)
    );

    private JLabel imageLabel;

    private final Map<JLabel, JTextField> fieldMap = new LinkedHashMap<>(Map.of(
        new JLabel("Enter Affine Amount:"),
        new JTextField(1),
        new JLabel("Enter Symmetry:"),
        new JTextField(1),
        new JLabel("Enter Samples Amount:"),
        new JTextField(1),
        new JLabel("Enter Iteration Amount:"),
        new JTextField(1)
    )
    );

    private final JButton startButton = new JButton("Start");
    private final JButton stopButton = new JButton("Stop");
    private final JCheckBox multiThreadCheckBox = new JCheckBox("Enable MultiThreading");

    @SuppressWarnings("checkstyle:MagicNumber")
    public UIRenderer() {
        setTitle("Fractal Flame UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));
        setLayout(new BorderLayout());
        setResizable(false);
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BorderLayout());
        imageLabel = new JLabel(new ImageIcon(
            new ImageIcon("C:\\FractalFlame\\image.png")
                .getImage()
                .getScaledInstance(830, 600, Image.SCALE_SMOOTH)
        ));
        imageLabel.setPreferredSize(new Dimension(830, 100));
        leftPanel.add(imageLabel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(11, 1, 0, 5));

        JMenuBar menuBar = new JMenuBar();
        JMenu checkBoxMenu = new JMenu("Transformations");
        checkBoxes.forEach(checkBoxMenu::add);

        for (Map.Entry<JLabel, JTextField> entry : fieldMap.entrySet()) {
            entry.getKey().setBorder(new EmptyBorder(new Insets(0, 0, 0, 10)));
            rightPanel.add(entry.getKey());
            rightPanel.add(entry.getValue());
        }

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(startButton);

        menuBar.add(checkBoxMenu);
        rightPanel.add(menuBar);

        rightPanel.add(multiThreadCheckBox);

        rightPanel.add(buttonPanel);
        add(rightPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
    }
}
