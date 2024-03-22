import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends JPanel {
    private boolean isSimulationPaused = true;
    private Lattice lattice;
    private AnotherSimulationVersion simulation;
    private int test = 0;

    public SimulationPanel() {
        simulation = new AnotherSimulationVersion();
        lattice = simulation.getLattice();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(lattice.getSize(), lattice.getSize());
    }

    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        PaintSimulationPanel(graph);

        simulation.Do();

        if (!isSimulationPaused) {
            RepaintLattice();
        }
    }

    private void PaintSimulationPanel(Graphics graph) {
        for (int i = 0; i < lattice.getSize(); i++) {
            for (int j = 0; j < lattice.getSize(); j++) {
                Node node = lattice.getNodeOnPosition(i,j);
                if (node != null) {
                    float survivability = (float) node.getSurvivability();
                    Color gradientColor = new Color(survivability, survivability, survivability);
                    graph.setColor(gradientColor);
                } else {
                    graph.setColor(Color.black);
                }
                graph.fillRect(i, j, 2, 2);
            }
        }
    }

    private void RepaintLattice() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        repaint();
    }

    public void setSimulationPaused(boolean simulationPaused) {
        isSimulationPaused = simulationPaused;
    }

    public boolean isSimulationPaused() {
        return isSimulationPaused;
    }
}
