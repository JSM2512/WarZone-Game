package Controller;

import Constants.ProjectConstants;
import Models.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Player controller.
 */
public class PlayerController implements Serializable {

    /**
     * The D current state.
     */
    CurrentState d_currentState = new CurrentState();

    /**
     * Instantiates a new Player controller.
     */
    public PlayerController() {
    }

    /**
     * Assign countries.
     *
     * @param p_currentState the p current state
     * @return the boolean
     */
    public boolean assignCountries(CurrentState p_currentState) {
        if (!checkPlayersAvalability(p_currentState)) {
            return false;
        }
        List<Country> l_countries = p_currentState.getD_map().getD_mapCountries();

        int l_playersize = p_currentState.getD_players().size();
        Player l_neutralPlayer = null;
        for (Player l_eachPlayer : p_currentState.getD_players()) {
            if (l_eachPlayer.getD_name().equalsIgnoreCase("Neutral")) {
                l_neutralPlayer = l_eachPlayer;
                break;
            }
        }
        if(l_neutralPlayer != null) {
            l_playersize = l_playersize - 1;
        }
        int l_countriesPerPlayer = Math.floorDiv(l_countries.size(), l_playersize);
        this.assignRandomCountriesToPlayers(l_countriesPerPlayer, l_countries, p_currentState.getD_players(), p_currentState);
        this.assignContinentToPlayers(p_currentState.getD_players(), p_currentState.getD_map().getD_mapContinents());
        p_currentState.updateLog("Countries assigned to players", "effect");
        return true;
    }

    /**
     * Check players avalability boolean.
     *
     * @param p_currentState the p current state
     * @return the boolean
     */
    private boolean checkPlayersAvalability(CurrentState p_currentState) {
        if (p_currentState.getD_players() == null || p_currentState.getD_players().isEmpty()) {
            System.out.println(ProjectConstants.NO_PLAYER_IN_GAME);
            d_currentState.getD_modelLogger().setD_message(ProjectConstants.NO_PLAYER_IN_GAME, "effect");
            return false;
        }
        return true;
    }

    /**
     * Display assigned countries.
     *
     * @param p_players the p players
     */
    private void displayAssignedCountries(List<Player> p_players) {
        for (Player l_currentPlayer : p_players) {
            System.out.print("Player " + l_currentPlayer.getD_name() + " has these countries: ");
            for (Country l_currentCountry : l_currentPlayer.getD_currentCountries()) {
                System.out.print(l_currentCountry.getD_countryName() + " ");
            }
            System.out.println();
        }
    }


    /**
     * Assign continent to players.
     *
     * @param p_players       the p players
     * @param p_mapContinents the p map continents
     */
    public void assignContinentToPlayers(List<Player> p_players, List<Continent> p_mapContinents) {
        for (Player l_eachPlayer : p_players) {
            List<Country> l_countriesOwnedByPlayer = l_eachPlayer.getD_currentCountries();

            if (l_eachPlayer.getD_currentCountries() != null && !l_eachPlayer.getD_currentCountries().isEmpty()) {
                for (Continent l_eachContinent : p_mapContinents) {
                    boolean l_isContinentOwned = l_countriesOwnedByPlayer.containsAll(l_eachContinent.getD_countries());
                    if (l_isContinentOwned) {
                        l_eachPlayer.setContinent(l_eachContinent);
                    }
                }
            }
        }
    }

    /**
     * Assign random countries to players.
     *
     * @param p_countriesPerPlayer the p countries per player
     * @param p_countryList        the p country list
     * @param p_players            the p players
     * @param p_currentState       the p current state
     */
    public void assignRandomCountriesToPlayers(int p_countriesPerPlayer, List<Country> p_countryList, List<Player> p_players, CurrentState p_currentState) {
        List<Country> l_unallocatedCountries = new ArrayList<>(p_countryList);
        if (l_unallocatedCountries.isEmpty()) {
            System.out.println(ProjectConstants.NO_COUNTRY_IN_MAP);
            d_currentState.getD_modelLogger().setD_message(ProjectConstants.NO_COUNTRY_IN_MAP,"effect");
            return;
        }

        for (Player l_eachPlayer : p_players) {
            if(!l_eachPlayer.getD_name().equalsIgnoreCase("Neutral")) {
                if (l_unallocatedCountries.isEmpty()) {
                    break;
                }
                for (int i = 1; i <= p_countriesPerPlayer; i++) {
                    Random l_randomNumber = new Random();
                    int l_randomIndex = l_randomNumber.nextInt(l_unallocatedCountries.size());

                    if (l_eachPlayer.getD_currentCountries() == null) {
                        l_eachPlayer.setD_currentCountries(new ArrayList<>());
                    }
                    l_eachPlayer.getD_currentCountries().add(l_unallocatedCountries.get(l_randomIndex));
                    l_unallocatedCountries.remove(l_randomIndex);
                }
            }
        }

        if (!l_unallocatedCountries.isEmpty()) {
            assignRandomCountriesToPlayers(1, l_unallocatedCountries, p_players, p_currentState);
        }
    }

    /**
     * Assign armies.
     *
     * @param p_currentState the p current state
     */
    public void assignArmies(CurrentState p_currentState) {
        if(p_currentState.getD_players() == null || p_currentState.getD_players().isEmpty()){
            System.out.println(ProjectConstants.NO_PLAYER_IN_GAME);
            d_currentState.getD_modelLogger().setD_message(ProjectConstants.NO_PLAYER_IN_GAME,"effect");
            return;
        }
        for (Player l_eachPlayer : p_currentState.getD_players()) {
            int l_countOfArmiesOfEachPlayer = getNoOfArmies(l_eachPlayer);
            l_eachPlayer.setD_unallocatedArmies(l_countOfArmiesOfEachPlayer);
            System.out.println("Player " + l_eachPlayer.getD_name() + " got assigned : " + l_countOfArmiesOfEachPlayer + " Armies.");
        }
        displayAssignedCountries(p_currentState.getD_players());
    }

    /**
     * Gets no of armies.
     *
     * @param p_eachPlayer the p each player
     * @return the no of armies
     */
    public int getNoOfArmies(Player p_eachPlayer) {
        int l_currentArmySize = 0;
        if (p_eachPlayer.getD_currentCountries() != null && !p_eachPlayer.getD_currentCountries().isEmpty()) {
            l_currentArmySize = Math.max(3, Math.round((float) (p_eachPlayer.getD_currentCountries().size() / 3)));
        }
        if (p_eachPlayer.getD_currentContinents() != null && !p_eachPlayer.getD_currentContinents().isEmpty()) {
            int l_totalContinentValue = 0;
            for (Continent l_eachContinent : p_eachPlayer.getD_currentContinents()) {
                l_totalContinentValue += l_eachContinent.getD_continentValue();
            }
            l_currentArmySize += l_totalContinentValue;
        }
        return l_currentArmySize;
    }

    /**
     * Is unallocated armies exist boolean.
     *
     * @param p_currentState the p current state
     * @return the boolean
     */
    public boolean isUnallocatedArmiesExist(CurrentState p_currentState) {
        int l_totalCount = 0;
        for (Player l_eachPlayer : p_currentState.getD_players()) {
            l_totalCount += l_eachPlayer.getD_unallocatedArmies();
        }
        return l_totalCount > 0;
    }


    /**
     * Is unexecuted orders exist boolean.
     *
     * @param p_playerList the p player list
     * @return the boolean
     */
    public boolean isUnexecutedOrdersExist(List<Player> p_playerList) {
        int l_totalCountOfUnexecutedOrders = 0;
        for(Player l_eachPlayer : p_playerList){
            l_totalCountOfUnexecutedOrders += l_eachPlayer.getD_orders().size();
        }
        return l_totalCountOfUnexecutedOrders != 0;
    }

    /**
     * Check for more orders boolean.
     *
     * @param p_players the p players
     * @return the boolean
     */
    public boolean checkForMoreOrders(List<Player> p_players) {
        for(Player l_eachPlayer : p_players){
            if(l_eachPlayer.hasMoreOrders()){
                return true;
            }
        }
        return false;
    }

    /**
     * Reset player flag.
     *
     * @param p_playerList the p player list
     */
    public void resetPlayerFlag(List<Player> p_playerList){
        for(Player l_eachPlayer : p_playerList){
            if(!l_eachPlayer.getD_name().equalsIgnoreCase("Neutral")){
                l_eachPlayer.setMoreOrders(true);
            }
            l_eachPlayer.setD_oneCardPerTurn(false);
            l_eachPlayer.resetNegotiation();
        }
    }
}
