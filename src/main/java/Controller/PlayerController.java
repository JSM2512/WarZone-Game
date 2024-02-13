package Controller;

import Models.Continent;
import Models.Country;
import Models.CurrentState;
import Models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerController {
    public void assignCountries(CurrentState p_currentState) {
        List<Player> l_players = p_currentState.getD_players();
        List<Country> l_countryList = p_currentState.getD_map().getD_mapCountries();

        int l_noOfPlayers = l_players.size();
        int l_noOfCountries = l_countryList.size();
        int l_noOfCountiesToEachPlayer = Math.floorDiv(l_noOfCountries, l_noOfPlayers);

        assignRandomCountriesToPlayers(l_players,l_countryList,l_noOfCountiesToEachPlayer);
        assignContinentToPlayers(l_players,p_currentState.getD_map().getD_mapContinents());
    }

    private void assignContinentToPlayers(List<Player> p_players, List<Continent> p_mapContinents) {
        for (Player l_eachPlayer : p_players){
            List<Country> l_countriesOwnedByPlayer = l_eachPlayer.getD_currentCountries();

            if (l_eachPlayer.getD_currentCountries() != null && !l_eachPlayer.getD_currentCountries().isEmpty()){
                for(Continent l_eachContinent : p_mapContinents){
                    boolean l_isContinentOwned = l_countriesOwnedByPlayer.containsAll(l_eachContinent.getD_countries());
                    if(l_isContinentOwned){
                        l_eachPlayer.setContinent(l_eachContinent);
                    }
                }
            }
        }
    }

    private void assignRandomCountriesToPlayers(List<Player> p_players, List<Country> p_countryList, int p_noOfCountiesToEachPlayer) {
        List<Country> l_unallocatedCountries = new ArrayList<>(p_countryList);
        if (l_unallocatedCountries.isEmpty()){
            System.out.println("No Countries in Map.");
        }
        for(Player l_eachPlayer : p_players){
            if (l_unallocatedCountries.isEmpty())
                break;
            for (int i = 1 ; i <= p_noOfCountiesToEachPlayer ; i++){
                Random l_randomNumber = new Random();
                int l_randomIndex = l_randomNumber.nextInt(l_unallocatedCountries.size());

                if (l_eachPlayer.getD_currentCountries() == null){
                    l_eachPlayer.setD_currentCountries(new ArrayList<>());
                }
                l_eachPlayer.getD_currentCountries().add(l_unallocatedCountries.get(l_randomIndex));
                l_unallocatedCountries.remove(l_randomIndex);
            }
        }

        if (!l_unallocatedCountries.isEmpty()) {
            assignRandomCountriesToPlayers(p_players, l_unallocatedCountries,1);
        }
    }
}