public class Constants {
    private static final int latticeSize = 150;
    private static final int windowWidth = 300;
    private static final int windowHeight = 300;

    private static final boolean isRandomInitialNodesSurvivability = true;
    private static final double initialNodesSurvivability = .5;


    // --------- Antibiotic Concentration Options --------
    private static final boolean isAntibioticConcentrationAvailable = true;
    private static final double minRangeOfConcentration = 1;
    private static final double maxRangeOfConcentration = 6;
    private static final int numberOfConcentrationSteps = 10;

    // --------- Mutability after inheritance -----------
    private static final boolean isMutationOfSurvivabilityAfterInheritanceAvailable = true;
    private static final double mutabilityRate = 0.01;
    private static final double minimumOffsetOfMutability = 0;


    // --------- Getters ----------

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
