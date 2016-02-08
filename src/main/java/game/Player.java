package game;

/**
 * Contains players.
 */
public enum Player {
    NONE,
    X,
    O;

    @Override
    public String toString() {
        if (this == NONE) {
            return ".";
        }
        return this.name();
    }
}
