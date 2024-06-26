package Models;

import Controller.MainGameEngine;
import Exceptions.CommandValidationException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * The type Tournament test.
 */
public class TournamentTest {

    /**
     * The D player 1.
     */
    Player d_player1;

    /**
     * Second Player.
     */
    Player d_player2;

    /**
     * Game State.
     */
    CurrentState d_currentState;

    /**
     * The D tournament.
     */
    Tournament d_tournament;

    /**
     * The D main game engine.
     */
    MainGameEngine d_mainGameEngine = new MainGameEngine();

    /**
     * Sets .
     */
    @Before
    public void setup() {
        d_currentState = new CurrentState();
        d_player1 = new Player("Player1");
        d_player1.setD_playerBehaviourStrategy(new RandomPlayer());
        d_player2 = new Player("Player2");
        d_player2.setD_playerBehaviourStrategy(new CheaterPlayer());

        d_currentState.setD_players(Arrays.asList(d_player1, d_player2));
        d_mainGameEngine.setD_stateOfGame(d_currentState);
        d_tournament = new Tournament();
    }

    /**
     * Test invalid map args.
     *
     * @throws CommandValidationException the command validation exception
     */
    @Test
    public void testInvalidMapArgs() throws CommandValidationException {
        assertFalse(d_tournament.parseTournamentCommand(d_currentState, "M", "test.map test.map test.map test.map test.map test.map", d_mainGameEngine));
        assertTrue(d_tournament.parseTournamentCommand(d_currentState, "M", "test.map test.map test.map test.map", d_mainGameEngine));
    }

    /**
     * Test invalid player strategies args.
     *
     * @throws CommandValidationException the command validation exception
     */
    @Test
    public void testInvalidPlayerStrategiesArgs() throws CommandValidationException {
        assertTrue(d_tournament.parseTournamentCommand(d_currentState, "P", "Random Cheater", d_mainGameEngine));
        assertFalse(d_tournament.parseTournamentCommand(d_currentState, "P", "Random Aggressive", d_mainGameEngine));

    }

    /**
     * Test invalid no of game args.
     *
     * @throws CommandValidationException the command validation exception
     */
    @Test
    public void testInvalidNoOfGameArgs() throws CommandValidationException {
        assertFalse(d_tournament.parseTournamentCommand(d_currentState,"G","10",d_mainGameEngine));
        assertTrue(d_tournament.parseTournamentCommand(d_currentState,"G","3",d_mainGameEngine));

    }

    /**
     * Test invalid no of turns.
     *
     * @throws CommandValidationException the command validation exception
     */
    @Test
    public void testInvalidNoOfTurns() throws CommandValidationException {
        assertFalse(d_tournament.parseTournamentCommand(d_currentState,"D","100",d_mainGameEngine));
        assertTrue(d_tournament.parseTournamentCommand(d_currentState,"D","40",d_mainGameEngine));

    }

}