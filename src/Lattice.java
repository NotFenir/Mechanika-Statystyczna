import java.util.ArrayList;
import java.util.List;

public class Lattice {

    private final int size;
    private final Node[][] lattice;
    private double[][] concentrationOfAntibioticMatrix;

    public Lattice(int latticeSize) {
        this.size = latticeSize;
        lattice = new Node[latticeSize][latticeSize];
        concentrationOfAntibioticMatrix = new double[latticeSize][latticeSize];
        initializeAntibioticConcentrationMatrix();
    }

    private void initializeAntibioticConcentrationMatrix() {
        int middleIndex = size / 2;
        int numberOfIndicesInOneStep = (int) (middleIndex / Constants.getNumberOfConcentrationSteps());
        double step = (Constants.getMaxRangeOfConcentration() - Constants.getMinRangeOfConcentration()) /
                Constants.getNumberOfConcentrationSteps();

        for (int i = 0; i < Constants.getNumberOfConcentrationSteps(); i++) {
            for (int j = 0; j < numberOfIndicesInOneStep; j++) {
                double concentration = Constants.getMinRangeOfConcentration() + step * i;
                int x = i * numberOfIndicesInOneStep + j;

                for (int y = 0; y < size; y++) {
                    concentrationOfAntibioticMatrix[x][y] = concentration;
                    concentrationOfAntibioticMatrix[size-1-x][y] = concentration;
                }

            }
        }
    }

    public void putInitialNodesOnLeftAndRightSides() {
        double survivability = Constants.getInitialNodesSurvivability();
        for (int i = 0; i < size; i++) {
            do {
                if (Constants.getIsRandomInitialNodesSurvivability()) {
                    survivability = Math.random();
                }
            } while (survivability < Constants.getMinInitialNodesSurvivability() ||
                    survivability > Constants.getMaxInitialNodesSurvivability());

            lattice[0][i] = new Node( 0, i, survivability);
            lattice[1][i] = new Node( 1, i, survivability);
            lattice[2][i] = new Node( 2, i, survivability);
            lattice[size -1][i] = new Node( 0, i, survivability);
            lattice[size -2][i] = new Node( 1, i, survivability);
            lattice[size -3][i] = new Node( 2, i, survivability);
        }
    }

    public Node getNodeOnPosition(int x, int y){
        return lattice[x][y];
    }

    public double getAntibioticConcentrationOnPosition(int x, int y) {
        return concentrationOfAntibioticMatrix[x][y];
    }

    public List<Node> getAdjacentNodesOfNodeOnPosition(int x, int y) {
        List<Node> adjacentNodes = new ArrayList<>();
        int numberOfNeighbours = 4;
        int[][] adjacentNodesCoordinates = {
                {x - 1, y},
                {x + 1, y},
                {x, y - 1},
                {x, y + 1}
        };

        for (int i = 0; i < numberOfNeighbours; i++) {
            int adjacentNodeX = adjacentNodesCoordinates[i][0];
            int adjacentNodeY = adjacentNodesCoordinates[i][1];

            if (!checkNodePositionIsAvailable(adjacentNodeX, adjacentNodeY)) {
                continue;
            }
            if (getNodeOnPosition(adjacentNodeX, adjacentNodeY) == null) {
                continue;
            }

            adjacentNodes.add(getNodeOnPosition(adjacentNodeX, adjacentNodeY));
        }

//        if (!adjacentNodes.isEmpty()) {
//            System.out.println(adjacentNodes.size() + "<- rozmiar");
//        }

        return adjacentNodes;
    }

    private boolean checkNodePositionIsAvailable(int x, int y) {
        if (x < 0) { return false; }
        if (x > size - 1) { return false; }
        if (y < 0) { return false; }
        if (y > size - 1) { return false; }
        return true;
    }

    public void destroyNodeOnPosition(int x, int y) {
        lattice[x][y] = null;
    }

    public void setNodeOnPosition(int x, int y, double survivability) {
        lattice[x][y] = new Node(x, y, survivability);
    }

    public int getSize() {
        return size;
    }

    public static Node chooseRandomNode(List<Node> adjacentNodes) {
        int numberOfNodes = adjacentNodes.size();
        int randomNodeIndex;

        // Poniżej znajduje się zabezpieczenie, ponieważ jeśli sąsiadów jest 4,
        // to wylosowanie 1 da index 4, która nie jest już w zasięgu

        do {
            randomNodeIndex = (int)(Math.random() * numberOfNodes);
        } while (randomNodeIndex >= numberOfNodes);

        return adjacentNodes.get(randomNodeIndex);
    }

}
