package xword.puzzle.objects;

/**
 * @author alex
 */
public enum Direction {
    ACROSS("across"), DOWN("down");

    private String value;

    Direction(String direction) {
        this.value = direction;
    }

    @Override
    public String toString() {
        return this.value;
    }

    /**
     * Return a Direction enum from the provided String.
     *
     * Returns null if a Direction can't be found for the String.
     *
     * @param direction the direction string
     * @return a Direction
     */
    public static Direction fromString(String direction) {
        for (Direction dir : Direction.values()) {
            if (dir.toString().equals(direction)) {
                return dir;
            }
        }
        return null;
    }
}
