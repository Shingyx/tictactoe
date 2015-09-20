import game.Game;
import game.GameState;
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
        for (char[] row : game.getBoard()) {
            for (char symbol : row) {
                assertEquals(' ', symbol);
            }
        }
        assertEquals('x', game.getTurn());
    }

    @Test
    public void testMakeMove() {
        assertTrue(game.makeMove(1, 1));
        assertEquals('x', game.getBoard()[1][1]);
        assertFalse(game.makeMove(1, 1));
        assertEquals('x', game.getBoard()[1][1]);

        assertTrue(game.makeMove(0, 1));
        assertEquals('o', game.getBoard()[0][1]);
        assertFalse(game.makeMove(0, 1));
        assertEquals('o', game.getBoard()[0][1]);
    }

    @Test
    public void testCalculateStateRow0X() {
        assertTrue(game.makeMove(0, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(0, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(0, 2));
        assertEquals(GameState.X_WIN, game.calculateState());
    }

    @Test
    public void testCalculateStateRow0O() {
        assertTrue(game.makeMove(0, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(0, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(2, 2));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 2));
        assertEquals(GameState.O_WIN, game.calculateState());
    }

    @Test
    public void testCalculateStateRow1X() {
        assertTrue(game.makeMove(1, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(0, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(0, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 2));
        assertEquals(GameState.X_WIN, game.calculateState());
    }

    @Test
    public void testCalculateStateRow2X() {
        assertTrue(game.makeMove(2, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(2, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(2, 2));
        assertEquals(GameState.X_WIN, game.calculateState());
    }

    @Test
    public void testCalculateStateCol0X() {
        assertTrue(game.makeMove(0, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(0, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(2, 0));
        assertEquals(GameState.X_WIN, game.calculateState());
    }

    @Test
    public void testCalculateStateDiag1X() {
        assertTrue(game.makeMove(0, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(0, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(2, 2));
        assertEquals(GameState.X_WIN, game.calculateState());
    }

    @Test
    public void testCalculateStateDiag2X() {
        assertTrue(game.makeMove(0, 2));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(0, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(2, 0));
        assertEquals(GameState.X_WIN, game.calculateState());
    }

    @Test
    public void testCalculateStateTie() {
        assertTrue(game.makeMove(0, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(0, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(0, 2));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 0));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 2));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(1, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(2, 1));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(2, 2));
        assertEquals(GameState.IN_PROGRESS, game.calculateState());
        assertTrue(game.makeMove(2, 0));
        assertEquals(GameState.TIE, game.calculateState());
    }
}
