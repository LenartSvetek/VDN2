public class Difficulty {
    String name;
    int minSize;
    int maxSize;
    int minTurns;
    int maxTurns;
    int minTarget;
    int maxTarget;

    /**
     * Difficulty class
     *
     * @param nameI ime difficultija
     * @param minSizeI najmanjsa velikost polja
     * @param maxSizeI najvecja velikost polja
     * @param minTurnsI najman stevilo korakov
     * @param maxTurnsI najvec stevilo korakov
     * @param minTargetI najmanjse stevilo zeljenih tock
     * @param maxTargetI najvec stevilo zeljenih tock
     */
    Difficulty(String nameI, int minSizeI, int maxSizeI, int minTurnsI, int maxTurnsI, int minTargetI, int maxTargetI) {
        name = nameI;
        minSize = minSizeI;
        maxSize = maxSizeI;
        minTurns = minTurnsI;
        maxTurns = maxTurnsI;
        minTarget = minTargetI;
        maxTarget = maxTargetI;
    }
}
