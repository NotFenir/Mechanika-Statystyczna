public class Constants {
    private static final int latticeSize = 300;
    private static final int windowWidth = 400;
    private static final int windowHeight = 400;
    private static final boolean isNonDeadSimulationActive = false;

    private static final boolean isRandomInitialNodesSurvivability = true;
    private static final double maxInitialNodesSurvivability = 0.8;
    private static final double minInitialNodesSurvivability = .3;
    private static final double initialNodesSurvivability = .55;


    // --------- Antibiotic Concentration Options --------
    private static final boolean isAntibioticConcentrationAvailable = true;
    private static final double minRangeOfConcentration = .1;
    private static final double maxRangeOfConcentration = 6;
    private static final int numberOfConcentrationSteps = 9;

    // --------- Mutability after inheritance -----------
    private static final boolean isMutationOfSurvivabilityAfterInheritanceAvailable = true;
    private static final double mutabilityRate = 0.003;
    private static final double minimumOffsetOfMutability = 0;

    // --------- Dead ----------
    private static final boolean isDeadNodeAvailable = true;

    // --------- Getters ----------
    public static boolean getIsNonDeadSimulationActive() { return isNonDeadSimulationActive; }
    public static boolean getIsDeadNodeAvailable() { return isDeadNodeAvailable; }
    public static double getMaxInitialNodesSurvivability() { return maxInitialNodesSurvivability; }
    public static double getMinInitialNodesSurvivability() { return minInitialNodesSurvivability; }

    public static double getInitialNodesSurvivability() {
        return initialNodesSurvivability;
    }

    public static boolean getIsRandomInitialNodesSurvivability() {
        return isRandomInitialNodesSurvivability;
    }

    public static double getMinRangeOfConcentration() {
        return minRangeOfConcentration;
    }

    public static double getMaxRangeOfConcentration() {
        return maxRangeOfConcentration;
    }

    public static int getNumberOfConcentrationSteps() {
        return numberOfConcentrationSteps;
    }
    public static double getMutabilityRate() {
        return mutabilityRate;
    }

    public static double getMinimumOffsetOfMutability() {
        return minimumOffsetOfMutability;
    }

    public static boolean getIsMutationOfSurvivabilityAfterInheritanceAvailable() {
        return isMutationOfSurvivabilityAfterInheritanceAvailable;
    }

    public static boolean getIsAntibioticConcentrationAvailable() {
        return isAntibioticConcentrationAvailable;
    }
    
    public static int getLatticeSize() {
        return latticeSize;
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }
}
