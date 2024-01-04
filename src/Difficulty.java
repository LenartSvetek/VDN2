public class Difficulty {
    String name;
    int minSize;
    int maxSize;
    int minTurns;
    int maxTurns;
    int minTarget;
    int maxTarget;

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
