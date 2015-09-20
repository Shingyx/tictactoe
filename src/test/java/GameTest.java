import game.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Game class.
 */
public class GameTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testConstructor() {
        for (char[] row : game.getState()) {
            for (char symbol : row) {
                assertEquals(' ', symbol);
            }
        }
        assertEquals('x', game.getTurn());
    }

    @Test
    public void testMakeMove() {
        assertTrue(game.makeMove(1, 1));
        assertEquals('x', game.getState()[1][1]);
        assertFalse(game.makeMove(1, 1));
        assertEquals('x', game.getState()[1][1]);

        assertTrue(game.makeMove(0, 1));
        assertEquals('o', game.getState()[0][1]);
        assertFalse(game.makeMove(0, 1));
        assertEquals('o', game.getState()[0][1]);
    }
}
