public class Node {
    private double survivability;
    int xPosition;
    int yPosition;

    public Node(int xPosition, int yPosition, double survivability) {
        this.survivability = survivability;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public double getSurvivability() {
        return survivability;
    }
}
