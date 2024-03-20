import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    private final JPanel panel;
    private final JFrame frame;
    private JButton startButton;
    private SimulationPanel simulationImage;
    private final int windowsWidth;
    private final int windowsHeight;

    public MainWindow() {
        panel = new JPanel();
        frame = new JFrame("Model Eden");
        simulationImage = new SimulationPanel();
        startButton = new JButton();
        windowsHeight = Constants.getWindowHeight();
        windowsWidth = Constants.getWindowWidth();
    }

    public void Create() {
        StartButtonInitialization();
        PanelInitialConfiguration();
        AddComponentsToPanel();
        FrameInitialConfiguration();
    }

    private void FrameInitialConfiguration() {
        frame.add(panel);
//        frame.add(simulationImage);
        frame.setSize(windowsWidth, windowsHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private void PanelInitialConfiguration() {
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.green);
    }

    private void AddComponentsToPanel() {
        panel.add(startButton);
        panel.add(simulationImage);
    }

    private void StartButtonInitialization() {
        startButton.setText("START");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (simulationImage.isSimulationPaused()) {
                    startButton.setText("STOP");
                    simulationImage.setSimulationPaused(false);
                    simulationImage.repaint();
                } else {
                    startButton.setText("START");
                    simulationImage.setSimulationPaused(true);
                    simulationImage.repaint();
                }
            }
        });
    }
}
