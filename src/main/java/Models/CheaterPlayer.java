package Models;

import Controller.PlayerController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

/**
 * The type Cheater player.
 */
public class CheaterPlayer extends PlayerBehaviourStrategy{

    /**
     * Instantiates a new Cheater player.
     */
    public CheaterPlayer() {
    }

    /**
     * Gets player behaviour.
     *
     * @return the player behaviour
     */
    @Override
    public String getPlayerBehaviour() {
        return "Cheater";
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
        if(p_player.getD_unallocatedArmies() !=0) {
            while (p_player.getD_unallocatedArmies() > 0) {
                Random l_random = new Random();
                Country l_randomCountry = getRandomCountry(p_player.getD_currentCountries());
                int l_armiesToDeploy = l_random.nextInt(p_player.getD_unallocatedArmies()) + 1;
                l_randomCountry.setD_armies(l_armiesToDeploy);
                p_player.setD_unallocatedArmies(p_player.getD_unallocatedArmies() - l_armiesToDeploy);

                String l_logMessage = "Cheater Player: " + p_player.getD_name() +
                        " assigned " + l_armiesToDeploy +
                        " armies to  " + l_randomCountry.getD_countryName();

                d_currentState.updateLog(l_logMessage, "effect");
            }
        }
        try{
            conquerNeighboringEnemies(p_player, p_currentState);
        } catch (ConcurrentModificationException l_e) {
        }

        doubleArmyOnEnemyNeighbourCountries(p_player, p_currentState);

        p_player.checkForMoreOrder(true);
        return null;
    }

    /**
     * Double army on enemy neighbour countries.
     *
     * @param p_player       the p player
     * @param p_currentState the p current state
     */
    private void doubleArmyOnEnemyNeighbourCountries(Player p_player, CurrentState p_currentState) {
        List<Country> l_countriesOwned = p_player.getD_currentCountries();

        for(Country l_eachCountry : l_countriesOwned){
            ArrayList<Integer> l_countryEnemies = getEnemies(p_player, l_eachCountry);

         if(l_countryEnemies.size() == 0) {
             continue;
         }
         Integer l_armies = l_eachCountry.getD_armies();

         if(l_armies == 0) {
             continue;
         }
         l_eachCountry.setD_armies(l_armies * 2);
         String l_logMessage = "Cheater Player: " + p_player.getD_name() +
                 " doubled the armies on " + l_eachCountry.getD_countryName();

         p_currentState.updateLog(l_logMessage,"effect");
        }
    }

    /**
     * Conquer neighboring enemies.
     *
     * @param p_player       the p player
     * @param p_currentState the p current state
     */
    private void conquerNeighboringEnemies(Player p_player, CurrentState p_currentState) {
        List<Country> l_countriesOwned = p_player.getD_currentCountries();

        for(Country l_eachCountry : l_countriesOwned){
            ArrayList<Integer> l_countryEnemies = getEnemies(p_player, l_eachCountry);

            for(Integer l_enemyId: l_countryEnemies) {
                Map l_map = p_currentState.getD_map();
                Player l_enemyCountryOwner = getCountryOwner(p_currentState, l_enemyId);
                Country l_enemyCountry =l_map.getCountryById(l_enemyId);
                conquerTargetCountry(p_currentState, l_enemyCountryOwner, p_player, l_enemyCountry);
                String l_logMessage = "Cheater Player: " + p_player.getD_name() +
                        " conquered " + l_enemyCountry.getD_countryName() +
                        " from " + l_enemyCountryOwner.getD_name();
                p_currentState.updateLog(l_logMessage, "effect");
            }
        }
    }

    /**
     * Conquer target country.
     *
     * @param p_currentState      the p current state
     * @param p_enemyCountryOwner the p enemy country owner
     * @param p_player            the p player
     * @param p_enemyCountry      the p enemy country
     */
    private void conquerTargetCountry(CurrentState p_currentState, Player p_enemyCountryOwner, Player p_player, Country p_enemyCountry) {
        p_enemyCountryOwner.getD_currentCountries().remove(p_enemyCountry);
        p_player.getD_currentCountries().add(p_enemyCountry);
        updateContinents(p_player, p_enemyCountryOwner, p_currentState);
    }

    /**
     * Update continents.
     *
     * @param p_player            the p player
     * @param p_enemyCountryOwner the p enemy country owner
     * @param p_currentState      the p current state
     */
    private void updateContinents(Player p_player, Player p_enemyCountryOwner, CurrentState p_currentState) {
        List<Player> l_players = new ArrayList<>();
        p_player.setD_currentContinents(new ArrayList<>());
        p_enemyCountryOwner.setD_currentContinents(new ArrayList<>());
        l_players.add(p_player);
        l_players.add(p_enemyCountryOwner);

        PlayerController l_playerController = new PlayerController();
        l_playerController.assignContinentToPlayers(l_players, p_currentState.getD_map().getD_mapContinents());
    }

    /**
     * Gets country owner.
     *
     * @param p_currentState the p current state
     * @param l_enemyId      the l enemy id
     * @return the country owner
     */
    private Player getCountryOwner(CurrentState p_currentState, Integer l_enemyId) {
        List<Player> l_players = p_currentState.getD_players();
        Player l_owner = null;
        for(Player l_eachPlayer : l_players){
            List<Integer> l_countriesOwned = l_eachPlayer.getCountryIDs();
            if(l_countriesOwned.contains(l_enemyId)){
                l_owner = l_eachPlayer;
                break;
            }
        }
        return l_owner;
    }

    /**
     * Gets enemies.
     *
     * @param p_player      the p player
     * @param p_eachCountry the p each country
     * @return the enemies
     */
    private ArrayList<Integer> getEnemies(Player p_player, Country p_eachCountry) {
        ArrayList<Integer> l_enemyCountries = new ArrayList<>();

        for(Integer l_countryId : p_eachCountry.getD_neighbouringCountriesId()){
            if(!p_player.getCountryIDs().contains(l_countryId)){
                l_enemyCountries.add(l_countryId);
            }
        }
        return l_enemyCountries;
    }

    /**
     * Gets random country.
     *
     * @param p_currentCountries the p current countries
     * @return the random country
     */
    private Country getRandomCountry(List<Country> p_currentCountries) {
        Random l_random = new Random();
        int l_randomIndex = l_random.nextInt(p_currentCountries.size());
        return p_currentCountries.get(l_randomIndex);
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
