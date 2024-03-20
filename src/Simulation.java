import java.util.List;

public class Simulation {
    private final Lattice lattice;
    private boolean isStepDone = false;

    public Simulation() {
        lattice = new Lattice(Constants.getLatticeSize());
        lattice.putInitialNodesOnLeftAndRightSides();
    }

    public void Do() {
        while (!isStepDone) {
            int randomX = (int) (Math.random() * lattice.getSize());
            int randomY = (int) (Math.random() * lattice.getSize());
//        System.out.println("x: "+randomX+"\ty: "+randomY);

            if (lattice.getNodeOnPosition(randomX, randomY) == null) {
                simulateWhenNodeIsEmpty(randomX, randomY);
            } else {
                simulateWhenNodeIsNotEmpty(randomX, randomY);
            }
        }
        isStepDone = false;
    }

    private void simulateWhenNodeIsNotEmpty(int randomX, int randomY) {
        if (Constants.getIsAntibioticConcentrationAvailable()) {
            double concentration = lattice.getAntibioticConcentrationOnPosition(randomX, randomY);
            DestroyNodeWithProbability(randomX, randomY, concentration);
        } else {
            DestroyNodeWithProbability(randomX, randomY, 0);
        }
    }

    private void DestroyNodeWithProbability(int x, int y, double concentration) {
        double nodeSurvivability = lattice.getNodeOnPosition(x, y).getSurvivability();
        double survivabilityConcentrationRate = Math.exp(-(1 - nodeSurvivability) * concentration);
        nodeSurvivability = nodeSurvivability * survivabilityConcentrationRate;

//        System.out.println("node: " + nodeSurvivability);

        if (Math.random() > nodeSurvivability) {
            lattice.destroyNodeOnPosition(x, y);
//            System.out.println("Destroy with concentration: " + concentration);
            isStepDone = true;
        }
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
        newNodeSurvivability = getNewNodeSurvivability(adjacentNode);

        lattice.setNodeOnPosition(randomX, randomY, newNodeSurvivability);
        isStepDone = true;
    }

    private static double getNewNodeSurvivability(Node adjacentNode) {
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
