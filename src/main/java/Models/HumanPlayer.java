package Models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The type Human player.
 */
public class HumanPlayer extends PlayerBehaviourStrategy{

    /**
     * Instantiates a new Human player.
     */
    public HumanPlayer() {
    }

    /**
     * Gets player behaviour.
     *
     * @return the player behaviour
     */
    @Override
    public String getPlayerBehaviour() {
        return "Human";
    }

    /**
     * Create order string.
     *
     * @param p_player       the p player
     * @param p_currentState the p current state
     * @return the string
     * @throws IOException the io exception
     */
    @Override
    public String createOrder(Player p_player, CurrentState p_currentState) throws IOException {
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please Enter command for Player : " + p_player.getD_name() + "   Armies left : " + p_player.getD_unallocatedArmies());
        System.out.println("1. Deploy Order Command : 'deploy <countryName> <noOfArmies>'");
        System.out.println("2. Advance Order Command : 'advance <countryFromName> <countryToName> <noOfArmies>");
        System.out.println();
        System.out.print("Enter your command: ");
        String l_command = l_reader.readLine();
        return l_command;
    }

    /**
     * Create card order string.
     *
     * @param p_player       the p player
     * @param p_currentState the p current state
     * @param p_cardName     the p card name
     * @return the string
     */
    @Override
    public String createCardOrder(Player p_player, CurrentState p_currentState, String p_cardName) {
        return null;
    }

    /**
     * Create advance order string.
     *
     * @param p_player       the p player
     * @param p_currentState the p current state
     * @return the string
     */
    @Override
    public String createAdvanceOrder(Player p_player, CurrentState p_currentState) {
        return null;
    }

    /**
     * Create deploy order string.
     *
     * @param p_player       the p player
     * @param p_currentState the p current state
     * @return the string
     */
    @Override
    public String createDeployOrder(Player p_player, CurrentState p_currentState) {
        return null;
    }
}
