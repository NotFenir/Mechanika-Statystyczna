import java.util.List;

public class AnotherSimulationVersion {
    private final Lattice lattice;
    private boolean isStepDone = false;
    private int iterateIdx = 0;

    public AnotherSimulationVersion() {
        lattice = new Lattice(Constants.getLatticeSize());
        lattice.putInitialNodesOnLeftAndRightSides();
    }

    public void Do() {
        while (!isStepDone && iterateIdx < 1000000) {
            int randomX = (int) (Math.random() * lattice.getSize());
            int randomY = (int) (Math.random() * lattice.getSize());

            if (lattice.getNodeOnPosition(randomX, randomY) == null) {
                simulateWhenNodeIsEmpty(randomX, randomY);
            }
        }

        iterateIdx = 0;
        isStepDone = false;
    }

    private void simulateWhenNodeIsEmpty(int randomX, int randomY) {
        List<Node> adjacentNodes;
        double newNodeSurvivability;

        adjacentNodes = lattice.getAdjacentNodesOfNodeOnPosition(randomX, randomY);
        if (adjacentNodes.isEmpty()) {
            return;
        }

//        System.out.println("Created on " + randomX + " " + randomY);

        Node adjacentNode = Lattice.chooseRandomNode(adjacentNodes);
        newNodeSurvivability = getNewNodeSurvivability(adjacentNode, randomX, randomY);

        lattice.trySetNodeOnPosition(randomX, randomY, newNodeSurvivability);
        isStepDone = true;
        iterateIdx++;
    }

    private double getNewNodeSurvivability(Node adjacentNode, int randomX, int randomY) {
        double newNodeSurvivability;

        newNodeSurvivability= getAdjacentNodeSurvivability(adjacentNode);
        newNodeSurvivability = getSurvivabilityInAntibioticConcentration(newNodeSurvivability, randomX, randomY);

        return newNodeSurvivability;
    }

    private double getSurvivabilityInAntibioticConcentration(double newNodeSurvivability, int randomX, int randomY) {
        if (Constants.getIsAntibioticConcentrationAvailable()) {
            double concentration = lattice.getAntibioticConcentrationOnPosition(randomX, randomY);
            newNodeSurvivability *= Math.exp(-(1 - newNodeSurvivability) * concentration);
        }

        return newNodeSurvivability;
    }

    private static double getAdjacentNodeSurvivability(Node adjacentNode) {
        double newNodeSurvivability;

        if (Constants.getIsMutationOfSurvivabilityAfterInheritanceAvailable()) {
            newNodeSurvivability = adjacentNode.getSurvivability() +
                    Constants.getMutabilityRate() * Math.random() +
                    Constants.getMinimumOffsetOfMutability();

            if (newNodeSurvivability >= 1) {
                newNodeSurvivability = 1;
            }
        } else {
            newNodeSurvivability = adjacentNode.getSurvivability();
        }

        return newNodeSurvivability;
    }

    public Lattice getLattice() {
        return lattice;
    }
}
